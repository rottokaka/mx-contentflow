package org.mxframework.contentflow.repository.pmc;

import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author mx
 */
@Repository
public interface ProjectJpaRepository extends JpaRepository<Project, Long> {

    Project findByProjectId(ProjectId projectId);

    List<Project> findAllByProperty(Integer property);

    List<Project> findAllByScope(Integer scope);

    List<Project> findAllByAboveProjectId(String aboveProjectId);

    List<Project> findAllByAboveProjectIdAndScope(String aboveProjectId, Integer scope);

    List<Project> findAllByCreator(Creator creator);

    List<Project> findAllByCreatorAndScope(Creator creator, Integer scope);
}
