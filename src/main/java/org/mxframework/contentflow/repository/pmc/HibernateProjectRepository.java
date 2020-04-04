package org.mxframework.contentflow.repository.pmc;

import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectRepository;
import org.mxframework.contentflow.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernateProjectRepository implements ProjectRepository {

    private final static String PROJECT_ID_PREFIX = "pmc-project-";

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Override
    public ProjectId nextIdentity() {
        return new ProjectId(PROJECT_ID_PREFIX + UUIDUtil.getUuidPartOne());
    }

    @Override
    public Project projectOfProjectId(ProjectId projectId) {
        return projectJpaRepository.findByProjectId(projectId);
    }

    @Override
    public Collection<Project> projectsOfCreator(Creator creator) {
        return projectJpaRepository.findAllByCreator(creator);
    }

    @Override
    public Collection<Project> projectsOfCreatorAndScope(Creator creator, Integer scope) {
        return projectJpaRepository.findAllByCreatorAndScope(creator, scope);
    }

    @Override
    public Collection<Project> projectsOfProperty(Integer perproty) {
        return projectJpaRepository.findAllByProperty(perproty);
    }

    @Override
    public Collection<Project> projectsOfScope(Integer scope) {
        return projectJpaRepository.findAllByScope(scope);
    }

    @Override
    public Collection<Project> projectsOfAboveProjectId(String aboveProjectId) {
        return projectJpaRepository.findAllByAboveProjectId(aboveProjectId);
    }

    @Override
    public Collection<Project> projectsOfAboveProjectIdAndScope(String aboveProjectId, Integer scope) {
        return projectJpaRepository.findAllByAboveProjectIdAndScope(aboveProjectId, scope);
    }

    @Override
    public void add(Project project) {
        projectJpaRepository.save(project);
    }

    @Override
    public void addAll(Collection<Project> projects) {
        projectJpaRepository.saveAll(projects);
    }

    @Override
    public void remove(Project project) {
        projectJpaRepository.delete(project);
    }

    @Override
    public void removeAll(Collection<Project> projects) {
        projectJpaRepository.deleteAll(projects);
    }
}
