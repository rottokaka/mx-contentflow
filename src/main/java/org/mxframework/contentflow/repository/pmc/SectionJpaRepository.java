package org.mxframework.contentflow.repository.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.section.Section;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SectionRepository: 类型仓库
 *
 * @author mx
 */
@Repository
public interface SectionJpaRepository extends JpaRepository<Section, Long> {

    /**
     * 查找类型
     *
     * @param sectionId 类型ID
     * @return 类型
     */
    Section findBySectionId(SectionId sectionId);

    /**
     * 查找类型
     *
     * @param versionId 版本ID
     * @param name      名称
     * @return 类型
     */
    Section findByVersionIdAndName(VersionId versionId, String name);

    /**
     * 列出类型
     *
     * @param versionId 版本ID
     * @return 类型集合
     */
    List<Section> findAllByVersionId(VersionId versionId);

}
