package org.mxframework.contentflow.service.pmc.project;

import org.mxframework.contentflow.constant.pmc.ProjectConstant;
import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectRepository;
import org.mxframework.contentflow.exception.ProjectException;
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
        Project project = projectRepository.projectOfProjectId(projectId);
        if (project == null) {
            throw new ProjectException("项目不存在，项目ID：" + projectId.id());
        }
        return project;
    }

    public List<Project> listByCreatorAndScope(Creator creator, Integer scope) {
        return (List<Project>) projectRepository.projectsOfCreatorAndScope(creator, scope);
    }

    public List<Project> listByProperty(Integer property) {
        return (List<Project>) projectRepository.projectsOfProperty(property);
    }

    public List<Project> listByScope(Integer property) {
        return (List<Project>) projectRepository.projectsOfScope(property);
    }

    public List<Project> listByAboveProjectId(String aboveProjectId) {
        return (List<Project>) projectRepository.projectsOfAboveProjectId(aboveProjectId);
    }

    public List<Project> listByCreator(Creator creator) {
        return (List<Project>) projectRepository.projectsOfCreator(creator);
    }

    public void add(Project project) {
        projectRepository.add(project);
    }

    public void addAll(List<Project> projects) {
        projectRepository.addAll(projects);
    }

    public void remove(Project project) {
        projectRepository.remove(project);
    }
}
