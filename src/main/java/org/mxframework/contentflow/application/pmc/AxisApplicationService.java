package org.mxframework.contentflow.application.pmc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mxframework.contentflow.application.ccp.BlogApplicationService;
import org.mxframework.contentflow.application.iaa.IdentityApplicationService;
import org.mxframework.contentflow.constant.ccp.BlogConstant;
import org.mxframework.contentflow.constant.ccp.BlogExceptionEnum;
import org.mxframework.contentflow.constant.iaa.UserExceptionEnum;
import org.mxframework.contentflow.constant.pmc.AxisConstant;
import org.mxframework.contentflow.constant.pmc.ProjectConstant;
import org.mxframework.contentflow.constant.pmc.ProjectExceptionEnum;
import org.mxframework.contentflow.domain.model.ccp.product.ProductType;
import org.mxframework.contentflow.domain.model.pmc.axis.Axis;
import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;
import org.mxframework.contentflow.domain.model.pmc.product.Product;
import org.mxframework.contentflow.domain.model.pmc.project.*;
import org.mxframework.contentflow.domain.model.pmc.project.section.Section;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.Subject;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.exception.BlogException;
import org.mxframework.contentflow.exception.MxException;
import org.mxframework.contentflow.exception.ProjectException;
import org.mxframework.contentflow.exception.UserException;
import org.mxframework.contentflow.representation.ccp.blog.dto.BlogCardDTO;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogCardVO;
import org.mxframework.contentflow.representation.pmc.ProductAtAxisVO;
import org.mxframework.contentflow.representation.pmc.axis.query.AxisQueryVO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectAtBlogVO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectItemVO;
import org.mxframework.contentflow.service.pmc.axis.AxisService;
import org.mxframework.contentflow.service.pmc.axis.BlogTranslator;
import org.mxframework.contentflow.service.pmc.project.ProjectService;
import org.mxframework.contentflow.service.pmc.project.SectionService;
import org.mxframework.contentflow.service.pmc.project.SubjectService;
import org.mxframework.contentflow.service.pmc.project.VersionService;
import org.mxframework.contentflow.service.pmc.translator.ProjectTranslator;
import org.mxframework.contentflow.util.AxisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class AxisApplicationService {

    @Autowired
    private BlogApplicationService blogApplicationService;
    @Autowired
    private IdentityApplicationService identityApplicationService;

    @Autowired
    private AxisService axisService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private VersionService versionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private BlogTranslator blogTranslator;
    @Autowired
    private ProjectTranslator projectTranslator;

    public List<BlogCardVO> listBlogCardByAxisQueryInFuzzy(AxisQueryVO axisQueryVo) {
        // 1. 处理数据：项目非空
        // 1.1. 项目查询，版本，专题和类型为空；
        // 1.2. 坐标查询，版本非空；
        // 项目ID不能为空
        String versionId = axisQueryVo.getVersionId();
        // 2. 查询数据，精准查询
        List<Axis> axisList = new ArrayList<>();
        // 坐标查询
        String productType = axisQueryVo.getProductType();
        if (versionId != null) {
            // 数据处理
            String subjectId = AxisUtil.nullReturnDefault(axisQueryVo.getSubjectId());
            String sectionId = AxisUtil.nullReturnDefault(axisQueryVo.getSectionId());
            // 模糊查询方式实现
            // 条件判断
            // 1） 版本级别：专题和类型默认
            boolean isVersionLevel = AxisConstant.AXIS_DEFAULT_ID.equals(subjectId) && AxisConstant.AXIS_DEFAULT_ID.equals(sectionId);
            // 2) 版本专题级别：专题不为空，类型默认
            boolean isVsubLevel = !AxisConstant.AXIS_DEFAULT_ID.equals(subjectId) && AxisConstant.AXIS_DEFAULT_ID.equals(sectionId);
            // 3) 版本类型级别：专题默认，类型不为空
            boolean isVsecLevel = AxisConstant.AXIS_DEFAULT_ID.equals(subjectId) && !AxisConstant.AXIS_DEFAULT_ID.equals(sectionId);
            // 4） 版本专题类型级别：专题和类型不为空
            boolean isVssLevel = !AxisConstant.AXIS_DEFAULT_ID.equals(subjectId) && !AxisConstant.AXIS_DEFAULT_ID.equals(sectionId);
            // 数据查询
            if (isVersionLevel) {
                axisList = axisService.listByProductTypeAndVersionId(productType, new VersionId(versionId));
            }
            if (isVsubLevel) {
                axisList = axisService.listByProductTypeAndVersionIdAndSubjectId(productType
                        , new SubjectId(subjectId), new VersionId(versionId));
            }
            if (isVsecLevel) {
                axisList = axisService.listByProductTypeAndVersionIdAndSectionId(productType
                        , new SectionId(sectionId), new VersionId(versionId));
            }
            if (isVssLevel) {
                axisList = axisService.listByProductTypeAndVersionIdAndSubjectIdAndSectionId(productType
                        , new SectionId(sectionId)
                        , new SubjectId(subjectId)
                        , new VersionId(versionId));
            }
        }
        // 项目查询
        else {
            String projectId = axisQueryVo.getProjectId();
            axisList = axisService.listByProductTypeAndProjectId(productType, new ProjectId(projectId));
        }
        // 3. 封装数据
        // 3.1. 产品处理
        List<BlogCardDTO> blogCardDtoList = new ArrayList<>();
        // 博客处理
        if (ProductType.PRODUCT_BLOG.name().equalsIgnoreCase(productType)) {
            // 产品ID
            List<String> blogIds = new ArrayList<>();
            if (axisList != null && axisList.size() > 0) {
                axisList.forEach(axis -> blogIds.add(axis.product().id()));
            }
            blogCardDtoList = blogApplicationService.listCardDtoByBlogIdList(blogIds);
        }
        return blogTranslator.convertToCardVo(blogCardDtoList);
    }

    public List<BlogCardVO> listBlogCardVoOnContribute(String projectId) {
        // 1. 项目是否拥有允许被投稿；[Y]
        if (projectService.contributionNotAllowed(projectId)) {
            throw new ProjectException(ProjectExceptionEnum.PROJECT_NOT_ALLOW_CONTRIBUTE);
        }
        // 2.获取关于用户的创建的所有博客
        List<BlogCardDTO> blogDtoList = blogApplicationService.listCardDtoByIdentity(identityApplicationService.identity());
        if (blogDtoList == null || blogDtoList.size() < 1) {
            throw new UserException(UserExceptionEnum.USER_NOT_CREATE_ANY_BLOGS);
        }
        // 2.2 移除已投稿项目的博客
        else {
            blogDtoList.removeIf(blogDto -> axisService.getByProductAndProjectId(new Product(blogDto.getBlogId()
                    , ProductType.PRODUCT_BLOG.name()), new ProjectId(projectId)) != null);
        }
        return blogTranslator.convertToCardVo(blogDtoList);
    }

    public List<ProjectItemVO> listProjectItemVoByBlogId(String blogId) {
        List<Axis> axisList = axisService.listByProduct(new Product(blogId, ProductType.PRODUCT_BLOG.name()));
        List<Project> projectList = new ArrayList<>(axisList.size());
        axisList.forEach(axis -> projectList.add(projectService.getByProjectId(axis.projectId())));
        return projectTranslator.convertToItemVo(projectList);
    }

    public List<ProjectAtBlogVO> listProjectAtBlogOnCollect(String blogId) {
        // 1. 博客是否允许收录；[Y]
        if (blogApplicationService.collectionNotAllow(blogId)) {
            throw new BlogException(BlogExceptionEnum.BLOG_NOT_ALLOW_COLLECT);
        }
        // 2. 用户是否创建了项目
        List<Project> projectList = projectService.listByCreator(new Creator(identityApplicationService.identity()));
        if (!projectList.isEmpty()) {
            // 3. 博客是否被项目收录
            List<ProjectAtBlogVO> projectAtBlogVoList = projectTranslator.convertToProjectAtBlogVo(projectList);
            for (ProjectAtBlogVO projectAtBlogVo : projectAtBlogVoList) {
                Axis axis = axisService.getByProductAndProjectId(new Product(blogId, ProductType.PRODUCT_BLOG.name()), new ProjectId(projectAtBlogVo.getProjectId()));
                if (axis != null) {
                    projectAtBlogVo.setCollected(ProjectConstant.PROJECT_COLLECTED_YES);
                } else {
                    projectAtBlogVo.setCollected(ProjectConstant.PROJECT_COLLECTED_NOT);
                }
            }
            return projectAtBlogVoList;
        } else {
            throw new UserException(UserExceptionEnum.USER_NOT_CREATE_ANY_PROJECTS);
        }
    }

    public List<ProductAtAxisVO> exportProductsByAxisQueryVo(AxisQueryVO axisQuery) {
        List<Axis> axes = axisService.listByAxisQuery(axisQuery);
        // 3. 封装数据
        // 3.1. 博客内容处理
        if (ProductType.PRODUCT_BLOG.name().equals(axisQuery.getProductType())) {
            // 内容ID
            List<String> blogIds = new ArrayList<>();
            if (axes.size() > 0) {
                axes.forEach(axis -> blogIds.add(axis.product().id()));
            }
            List<BlogCardDTO> blogCardDtos = blogApplicationService.listCardByBlogIdList(blogIds);
            return blogTranslator.convertToProductAtAxisVo(blogCardDtos);
        } else {
            return null;
        }
    }

    public List<BlogCardVO> importProductsByAxisQuery(AxisQueryVO axisQueryVo) {
        Section section = !"".equals(axisQueryVo.getSectionId())
                ? sectionService.getBySectionId(new SectionId(axisQueryVo.getSectionId()))
                : null;
        Subject subject = !"".equals(axisQueryVo.getSubjectId())
                ? subjectService.getBySubjectId(new SubjectId(axisQueryVo.getSubjectId()))
                : null;
        Version version = versionService.getByVersionId(new VersionId(axisQueryVo.getVersionId()));
        // 版本投稿：已属于某个项目 -> 项目自定义版本（版本，主题或类型）
        List<Axis> resultAxis = new ArrayList<>();
        // 投稿存储类型
        // 1. 投稿：版本【主题_类型】 versionId-subjectId-sectionId
        boolean vss = subject != null && section != null;
        // 2. 投稿：版本【主题_*】 versionId-subjectId-null
        boolean vSubNull = subject != null && section == null;
        // 3. 投稿：版本【类型_*】 versionId-null-sectionId
        boolean vNullSec = subject == null && section != null;
        // 4. 投稿：版本【*_*】 versionId-null-null （versionId默认版本，自定义版本）
        boolean vnn = subject == null && section == null;
        // 投稿来源类型
        if (vnn) {
            List<Axis> axisList = axisService.listByProjectId(version.projectId());
            if (axisList != null && axisList.size() > 0) {
                // 排除已投稿当前版本
                axisList.removeIf(axis -> axis.versionId().id().equals(version.versionId().id()));
            }
            resultAxis = axisList;
        }

        if (vSubNull || vNullSec) {
            // 2. versionId-null-null
            resultAxis = axisService.listByVersionIdAndSubjectIdAndSectionId(new SectionId(AxisConstant.AXIS_DEFAULT_ID)
                    , new SubjectId(AxisConstant.AXIS_DEFAULT_ID)
                    , version.versionId());
        }

        if (vss) {
            // 3. versionId-subjectId-null
            List<Axis> axisListBySubject = axisService.listByVersionIdAndSubjectIdAndSectionId(new SectionId(AxisConstant.AXIS_DEFAULT_ID)
                    , subject.subjectId()
                    , version.versionId());
            // 4. versionId-null-sectionId
            List<Axis> axisListBySection = axisService.listByVersionIdAndSubjectIdAndSectionId(
                    section.sectionId()
                    , new SubjectId(AxisConstant.AXIS_DEFAULT_ID)
                    , version.versionId());
            resultAxis.addAll(axisListBySubject);
            resultAxis.addAll(axisListBySection);
        }
        // 获取博客卡片集合
        List<String> blogIds = separate(resultAxis, ProductType.PRODUCT_BLOG.name());
        if (blogIds != null && blogIds.size() > 0) {
            List<BlogCardDTO> blogCardDtoList = blogApplicationService.listCardByBlogIdList(blogIds);
            return blogTranslator.convertToCardVo(blogCardDtoList);
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public Axis postByProductIdAndProductId(String productId, String projectId) {
        Axis axis = new Axis(new Product(productId, ProductType.PRODUCT_BLOG.name()), new ProjectId(projectId));
        axisService.insert(axis);
        return axis;
    }

    @Transactional(rollbackFor = {Exception.class})
    public List<Axis> postAxesOfProjectAndProducts(String projectId, String products) {
        Project project = projectService.getByProjectId(new ProjectId(projectId));
        // JSON对象转化
        List<Product> productList = JSONObject.parseArray(products, Product.class);
        List<String> productIdList = new ArrayList<>(productList.size());
        productList.forEach(product -> productIdList.add(product.id()));
        // 更新博客配置，增加归档次数
        blogApplicationService.changeArchivedByBlogId(productIdList, BlogConstant.BLOG_ARCHIVED_FACTOR_PLUS_AUGEND);
        List<Axis> axisList = new ArrayList<>(productList.size());
        productList.forEach(product -> axisList.add(new Axis(product, project.projectId())));
        axisService.insertAll(axisList);
        return axisList;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void putAxisOfProducts(String axis, String products, String pattern) {
        // JSON处理，获取项目对象axis
        AxisQueryVO axisQueryVo = JSON.parseObject(axis, AxisQueryVO.class);
        // TODO 数据字段校验
        // 确认项目 axisQueryVo.projectId 不可以为空
        String projectId = axisQueryVo.getProjectId();
        // 确认版本 axisQueryVo.versionId 不可以为空
        Version version = versionService.getByVersionId(new VersionId(axisQueryVo.getVersionId()));
        // JSON处理，获取博客列表blog.id
        List<Product> productList = JSON.parseArray(products, Product.class);
        String sectionId = AxisUtil.nullReturnDefault(axisQueryVo.getSectionId());
        String subjectId = AxisUtil.nullReturnDefault(axisQueryVo.getSubjectId());
        Axis targetAxis = new Axis(new ProjectId(projectId), version.versionId(), new SubjectId(subjectId), new SectionId(sectionId));
        // 投稿保存
        if (BlogConstant.BLOG_AXIS_PATTERN_INSERT.equals(pattern)) {
            axisService.insertAll(fillAxisList(targetAxis, productList));
        }
        List<Axis> axisList = new ArrayList<>(productList.size());
        List<String> idsOfblog = new ArrayList<>(productList.size());
        for (Product product : productList) {
            axisList.add(axisService.getByProductAndProjectId(product, targetAxis.projectId()));
            // 博客
            if (ProductType.PRODUCT_BLOG.name().equals(product.type())) {
                idsOfblog.add(product.id());
            }
        }
        // 移除保存
        if (BlogConstant.BLOG_AXIS_PATTERN_REMOVE.equals(pattern)) {
            // 获取移除坐标
            Axis removeAxis = getRemoved(targetAxis);
            // 如果移除坐标不为空，则进行添加
            if (removeAxis != null) {
                List<Axis> axisListFilled = fillAxisList(removeAxis, productList);
                axisService.insertAll(axisListFilled);
            } else {
                blogApplicationService.changeArchivedByBlogId(idsOfblog, BlogConstant.BLOG_ARCHIVED_FACTOR_MINUS_MINUEND);
                axisService.deleteAll(axisList);
            }
        }
        // 删除保存
        if (BlogConstant.BLOG_AXIS_PATTERN_DELETE.equals(pattern)) {
            blogApplicationService.changeArchivedByBlogId(idsOfblog, BlogConstant.BLOG_ARCHIVED_FACTOR_MINUS_MINUEND);
            axisService.deleteAll(axisList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteByProductIdAndProjectId(String productId, String projectId) {
        Axis axis = axisService.getByProductAndProjectId(new Product(productId, ProductType.PRODUCT_BLOG.name())
                , new ProjectId(projectId));
        if (axis != null) {
            axisService.delete(axis);
        } else {
            throw new MxException("产品已被取消收录");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByProjectId(String projectId) {
        axisService.deleteAll(axisService.listByProjectId(new ProjectId(projectId)));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByVersionId(String versionId) {
        axisService.deleteAll(axisService.listByVersionId(new VersionId(versionId)));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByVersionIds(List<String> versionIds) {
        List<Axis> axisList = new ArrayList<>(versionIds.size());
        versionIds.forEach(versionId -> axisList.addAll(axisService.listByVersionId(new VersionId(versionId))));
        axisService.deleteAll(axisList);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllBySubjectId(String subjectId) {
        axisService.deleteAll(axisService.listBySubjectId(new SubjectId(subjectId)));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllBySubjectIds(List<String> subjectIds) {
        List<Axis> axisList = new ArrayList<>(subjectIds.size());
        subjectIds.forEach(subjectId -> axisList.addAll(axisService.listBySubjectId(new SubjectId(subjectId))));
        axisService.deleteAll(axisList);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllBySectionId(String sectionId) {
        axisService.deleteAll(axisService.listBySectionId(new SectionId(sectionId)));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllBySectionIds(List<String> sectionIds) {
        List<Axis> axisList = new ArrayList<>(sectionIds.size());
        sectionIds.forEach(sectionId -> axisList.addAll(axisService.listBySectionId(new SectionId(sectionId))));
        axisService.deleteAll(axisList);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteOnProjectLevel(String projectId, String products) {
        // JSON对象转化
        List<Product> productList = JSONObject.parseArray(products, Product.class);
        if (productList != null && productList.size() > 0) {
            List<Axis> axisList = new ArrayList<>(productList.size());
            for (Product product : productList) {
                Axis axis = axisService.getByProductAndProjectId(product, new ProjectId(projectId));
                axisList.add(axis);
            }
            axisService.deleteAll(axisList);
        }
    }

    private List<Axis> fillAxisList(Axis axisAndProductIsNull, List<Product> productList) {
        List<Axis> axisList = new ArrayList<>(productList.size());
        for (Product product : productList) {
            Axis byProductAndProjectId = axisService.getByProductAndProjectId(product, axisAndProductIsNull.projectId());
            byProductAndProjectId.setVersionId(axisAndProductIsNull.versionId());
            byProductAndProjectId.setSubjectId(axisAndProductIsNull.subjectId());
            byProductAndProjectId.setSectionId(axisAndProductIsNull.sectionId());
            axisList.add(byProductAndProjectId);
        }
        return axisList;
    }

    /**
     * @param axisList    坐标集合
     * @param productType 内容类型
     * @return 产品ID集合
     */
    private static List<String> separate(List<Axis> axisList, String productType) {
        if (axisList != null) {
            axisList.removeIf(axis -> !productType.equals(axis.product().type()));
            List<String> idList = new ArrayList<>(axisList.size());
            axisList.forEach(axis -> idList.add(axis.product().id()));
            return idList;
        } else {
            return null;
        }

    }

    /**
     * 获取移除坐标，通过目标坐标，获取移除坐标
     *
     * @param targetAxis 目标坐标
     * @return 移除坐标
     */
    private Axis getRemoved(Axis targetAxis) {
        // 获取数据，项目ID不为空
        ProjectId projectId = targetAxis.projectId();
        VersionId versionId = targetAxis.versionId();
        SubjectId subjectId = targetAxis.subjectId();
        SectionId sectionId = targetAxis.sectionId();
        Axis removedAxis = null;
        // 坐标级别->专题级别
        // 如果专题和篇章都不为空，即当前为空专题篇章级别，降级为专题级别
        if (subjectId != null && sectionId != null) {
            removedAxis = new Axis(projectId, versionId, subjectId);
        }
        // 专题级别 || 类型级别 -> 版本级别
        // 如果专题和篇章有且仅有一个不为空，即当前为，专题级别，或者篇章级别，降级为版本级别
        boolean subjectOrSectionNotNull = (subjectId != null && sectionId == null) || (subjectId == null && sectionId != null);
        if (subjectOrSectionNotNull) {
            removedAxis = new Axis(projectId, versionId);
        }

        // 版本级别 -> 项目级别
        boolean subjectAndSectionNull = (subjectId == null) && (sectionId == null);
        if (subjectAndSectionNull) {
            removedAxis = new Axis(projectId);
        }
        // 项目级别 -> 移除产品
        return removedAxis;
    }

}
