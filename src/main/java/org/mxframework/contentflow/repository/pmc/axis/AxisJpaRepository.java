package org.mxframework.contentflow.repository.pmc.axis;

import org.mxframework.contentflow.domain.model.pmc.axis.Axis;
import org.mxframework.contentflow.domain.model.pmc.product.Product;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AxisRepository: 坐标仓库
 *
 * @author mx
 */
@Repository
public interface AxisJpaRepository extends JpaRepository<Axis, Long> {

    /**
     * 查找坐标，通过产品和项目ID
     *
     * @param product   产品
     * @param projectId 项目ID
     * @return 坐标
     */
    Axis findByProductAndProjectId(Product product, ProjectId projectId);

    List<Axis> findAllByProduct(Product product);

    /**
     * 查找所有坐标，通过产品类型，类型ID，专题ID和版本ID
     *
     * @param productType 产品类型
     * @param sectionId   类型ID
     * @param subjectId   专题ID
     * @param versionId   版本ID
     * @return 坐标集合
     */
    List<Axis> findAllByProduct_TypeAndSectionIdAndSubjectIdAndVersionId(String productType
            , SectionId sectionId
            , SubjectId subjectId
            , VersionId versionId);

    List<Axis> findAllByProduct_TypeAndSectionIdAndVersionId(String productType, SectionId sectionId, VersionId versionId);

    List<Axis> findAllByProduct_TypeAndSubjectIdAndVersionId(String productType, SubjectId subjectId, VersionId versionId);

    List<Axis> findAllByProductIdAndProductType(String productId, String productType);

    List<Axis> findAllByProduct_TypeAndVersionId(String productType, VersionId versionId);

    /**
     * 查找所有坐标，通过产品类型和项目ID
     *
     * @param productType 产品类型
     * @param projectId   项目ID
     * @return 坐标集合
     */
    List<Axis> findAllByProduct_TypeAndProjectId(String productType, ProjectId projectId);

    /**
     * 查找所有坐标，通过类型ID，专题ID和版本ID
     *
     * @param sectionId 类型ID
     * @param subjectId 专题ID
     * @param versionId 版本ID
     * @return 坐标集合
     */
    List<Axis> findAllBySectionIdAndSubjectIdAndVersionId(SectionId sectionId, SubjectId subjectId, VersionId versionId);

    /**
     * 查找所有的坐标，通过专题ID和版本ID
     *
     * @param subjectId 专题ID
     * @param versionId 版本ID
     * @return 坐标集合
     */
    List<Axis> findAllBySubjectIdAndVersionId(SubjectId subjectId, VersionId versionId);

    List<Axis> findAllByProjectId(ProjectId projectId);

    /**
     * 查找所有坐标，通过版本ID
     *
     * @param versionId 版本ID
     * @return 坐标集合
     */
    List<Axis> findAllByVersionId(VersionId versionId);

    List<Axis> findAllBySubjectId(SubjectId subjectId);

    List<Axis> findAllBySectionId(SectionId sectionId);


    /**
     * 删除坐标，通过产品ID，产品类型和项目ID
     *
     * @param product   产品
     * @param projectId 项目ID
     */
    void deleteByProductAndProjectId(Product product, String projectId);

    /**
     * 删除所有坐标，通过类型ID
     *
     * @param sectionId 类型ID
     */
    void deleteAllBySectionId(SectionId sectionId);

    /**
     * 删除所有坐标
     *
     * @param sectionIdList 类型ID集合
     */
    void deleteAllBySectionIdIn(List<SectionId> sectionIdList);

    /**
     * 删除所有坐标，通过专题ID
     *
     * @param subjectId 专题ID
     */
    void deleteAllBySubjectId(SubjectId subjectId);

    /**
     * 删除所有坐标
     *
     * @param subjectIdList 专题ID集合
     */
    void deleteAllBySubjectIdIn(List<SubjectId> subjectIdList);

    /**
     * 删除所有坐标，通过版本ID
     *
     * @param versionId 版本ID
     */
    void deleteAllByVersionId(VersionId versionId);

    /**
     * 删除所有坐标
     *
     * @param versionIdList 版本ID集合
     */
    void deleteAllByVersionIdIn(List<VersionId> versionIdList);
}
