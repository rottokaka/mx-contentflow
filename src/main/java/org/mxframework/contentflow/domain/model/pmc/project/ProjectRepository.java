package org.mxframework.contentflow.domain.model.pmc.project;

import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;

import java.util.Collection;
import java.util.List;

/**
 * @author mx
 */
public interface ProjectRepository {

    ProjectId nextIdentity();

    Project projectOfProjectId(ProjectId projectId);

    Collection<Project> projectsOfCreator(Creator creator);

    Collection<Project> projectsOfCreatorAndScope(Creator creator, Integer scope);

    Collection<Project> projectsOfProperty(Integer perproty);

    Collection<Project> projectsOfScope(Integer scope);

    Collection<Project> projectsOfAboveProjectId(String aboveProjectId);

    Collection<Project> projectsOfAboveProjectIdAndScope(String aboveProjectId, Integer scope);

    void add(Project project);

    void addAll(Collection<Project> projects);

    void remove(Project project);

    void removeAll(Collection<Project> projects);

}
