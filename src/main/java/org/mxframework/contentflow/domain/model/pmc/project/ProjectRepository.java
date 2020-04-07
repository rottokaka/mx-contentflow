package org.mxframework.contentflow.domain.model.pmc.project;

import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;

import java.util.Collection;

/**
 * @author mx
 */
public interface ProjectRepository {

    ProjectId nextIdentity();

    Project projectOfProjectId(ProjectId projectId);

    Project projectOfCreatorAndName(Creator creator, String name);

    Collection<Project> projectsOfCreator(Creator creator);

    Collection<Project> projectsOfCreatorAndScope(Creator creator, Integer scope);

    Collection<Project> projectsOfProperty(Integer perproty);

    Collection<Project> projectsOfPropertyAndScopeAndAboveProjectId(Integer property, Integer scope, String aboveProjectId);

    Collection<Project> projectsOfPropertyAndAboveProjectId(Integer property, String aboveProjectId);

    Collection<Project> projectsOfScope(Integer scope);

    Collection<Project> projectsOfAboveProjectId(String aboveProjectId);

    Collection<Project> projectsOfAboveProjectIdAndScope(String aboveProjectId, Integer scope);

    void add(Project project);

    void addAll(Collection<Project> projects);

    void remove(Project project);

    void removeAll(Collection<Project> projects);

}
