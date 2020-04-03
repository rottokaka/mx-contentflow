package org.mxframework.contentflow.service.pmc.axis;

import org.mxframework.contentflow.domain.model.pmc.axis.Axis;
import org.mxframework.contentflow.domain.model.pmc.axis.AxisRepository;
import org.mxframework.contentflow.domain.model.pmc.product.Product;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;
import org.mxframework.contentflow.repository.pmc.axis.AxisJpaRepository;
import org.mxframework.contentflow.representation.pmc.axis.query.AxisQueryVO;
import org.mxframework.contentflow.util.AxisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AxisService: 坐标接口
 *
 * @author mx
 */
@Service
public class AxisService {
    private final static Logger logger = LoggerFactory.getLogger(AxisService.class);

    @Autowired
    private AxisRepository axisRepository;
    @Autowired
    private AxisJpaRepository axisJpaRepository;

    public Axis getByProductAndProjectId(Product product, ProjectId projectId) {
        return axisRepository.axisOfProductAndProjectId(product, projectId);
    }

    public List<Axis> listByProduct(Product product) {
        return (List<Axis>) axisRepository.axesOfProduct(product);
    }

    public List<Axis> listByProductTypeAndProjectId(String productType, ProjectId projectId) {
        return axisJpaRepository.findAllByProduct_TypeAndProjectId(productType, projectId);
    }

    public List<Axis> listByProductTypeAndVersionId(String productType, VersionId versionId) {
        return axisJpaRepository.findAllByProduct_TypeAndVersionId(productType, versionId);
    }

    public List<Axis> listByProductTypeAndVersionIdAndSubjectId(String productType, SubjectId subjectId, VersionId versionId) {
        return axisJpaRepository.findAllByProduct_TypeAndSubjectIdAndVersionId(productType, subjectId, versionId);
    }

    public List<Axis> listByProductTypeAndVersionIdAndSubjectIdAndSectionId(String productType, SectionId sectionId, SubjectId subjectId, VersionId versionId) {
        return axisJpaRepository.findAllByProduct_TypeAndSectionIdAndSubjectIdAndVersionId(productType, sectionId, subjectId, versionId);
    }

    public List<Axis> listByProductTypeAndVersionIdAndSectionId(String productType, SectionId sectionId, VersionId versionId) {
        return axisJpaRepository.findAllByProduct_TypeAndSectionIdAndVersionId(productType, sectionId, versionId);
    }

    public List<Axis> listByProjectId(ProjectId projectId) {
        return (List<Axis>) axisRepository.axesOfProjectId(projectId);
    }

    public List<Axis> listByVersionId(VersionId versionId) {
        return (List<Axis>) axisRepository.axesOfVersionId(versionId);
    }

    public List<Axis> listByVersionIdAndSubjectIdAndSectionId(SectionId sectionId, SubjectId subjectId, VersionId versionId) {
        return (List<Axis>) axisRepository.axesOfVersionIdAndSubjectIdAndSectionId(versionId, subjectId, sectionId);
    }

    public List<Axis> listBySubjectId(SubjectId subjectId) {
        return (List<Axis>) axisRepository.axesOfSubjectId(subjectId);
    }

    public List<Axis> listBySectionId(SectionId sectionId) {
        return (List<Axis>) axisRepository.axesOfSectionId(sectionId);
    }

    public List<Axis> listByAxisQuery(AxisQueryVO axisQueryVo) {
        // 1. 处理数据：版本非空，专题和篇章，如果为空，则默认处理
        String versionId = axisQueryVo.getVersionId();
        String subjectId = AxisUtil.nullReturnDefault(axisQueryVo.getSubjectId());
        String sectionId = AxisUtil.nullReturnDefault(axisQueryVo.getSectionId());
        // 2. 查询数据，精准查询
        return (List<Axis>) axisJpaRepository.findAllByProduct_TypeAndSectionIdAndSubjectIdAndVersionId(axisQueryVo.getProductType()
                , new SectionId(sectionId)
                , new SubjectId(subjectId)
                , new VersionId(versionId));
    }

    public void insert(Axis axis) {
        axisRepository.add(axis);
    }

    public void insertAll(List<Axis> axes) {
        axisRepository.addAll(axes);
    }

    public void update(Axis axis) {
        axisRepository.add(axis);
    }

    public void updateAll(List<Axis> axisList) {
        axisRepository.addAll(axisList);
    }

    public void delete(Axis axis) {
        axisRepository.remove(axis);
    }

    public void deleteAll(List<Axis> axisList) {
        axisRepository.removeAll(axisList);
    }

}
