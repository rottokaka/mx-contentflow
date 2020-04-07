package org.mxframework.contentflow.service.pmc.project;

import org.mxframework.contentflow.constant.pmc.ProjectConstant;
import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mx
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectId nextIdentity() {
        return projectRepository.nextIdentity();
    }

    public boolean contributionNotAllowed(String projectId) {
        return ProjectConstant.PROJECT_CONTRIBUTION_NOT_ALLOWED_TRUE.equals(getByProjectId(new ProjectId(projectId)).contributionNotAllowed());
    }

    public Project getByProjectId(ProjectId projectId) {
        return projectRepository.projectOfProjectId(projectId);
    }

    public Project getByCreatorAndName(Creator creator, String name) {
        return projectRepository.projectOfCreatorAndName(creator, name);
    }

    public List<Project> listByCreatorAndScope(Creator creator, Integer scope) {
        return (List<Project>) projectRepository.projectsOfCreatorAndScope(creator, scope);
    }

    public List<Project> listByProperty(Integer property) {
        return (List<Project>) projectRepository.projectsOfProperty(property);
    }

    public List<Project> listByPropertyAndScopeAndScopeAndAboveProjectId(Integer property, Integer scope, String aboveProjectId) {
        return (List<Project>) projectRepository.projectsOfPropertyAndScopeAndAboveProjectId(property, scope, aboveProjectId);
    }

    public List<Project> listByPropertyAndAboveProjectId(Integer property, String aboveProjectId) {
        return (List<Project>) projectRepository.projectsOfPropertyAndAboveProjectId(property, aboveProjectId);
    }

    public List<Project> listByScope(Integer property) {
        return (List<Project>) projectRepository.projectsOfScope(property);
    }

    public List<Project> listByAboveProjectId(String aboveProjectId) {
        return (List<Project>) projectRepository.projectsOfAboveProjectId(aboveProjectId);
    }

    public List<Project> listByAboveProjectIdIdAndScope(String aboveProjectId, Integer scope) {
        return (List<Project>) projectRepository.projectsOfAboveProjectIdAndScope(aboveProjectId, scope);
    }

    public List<Project> listByCreator(Creator creator) {
        return (List<Project>) projectRepository.projectsOfCreator(creator);
    }

    public void save(Project project) {
        projectRepository.add(project);
    }

    public void update(Project project) {
        projectRepository.add(project);
    }

    public void addAll(List<Project> projects) {
        projectRepository.addAll(projects);
    }

    public void remove(Project project) {
        projectRepository.remove(project);
    }

}
