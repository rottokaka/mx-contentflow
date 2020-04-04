package org.mxframework.contentflow.repository.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * VersionRepository: 版本仓库
 *
 * @author mx
 */
@Repository
public interface VersionJpaRepository extends JpaRepository<Version, Long> {


    Version findByVersionId(VersionId versionId);

    Version findByProjectIdAndName(ProjectId projectId, String name);

    List<Version> findAllByProjectId(ProjectId projectId);

}
