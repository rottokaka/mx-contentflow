package org.mxframework.contentflow.representation.pmc.project;

import org.springframework.beans.BeanUtils;

/**
 * @author mx
 */
public class ProjectBase {

    private String projectId;
    private String creatorIdentity;
    private String name;
    private String description;
    private String avatar;
    private String website;
    private Integer property;
    private Integer scope;
    private Integer contributionNotAllowed;
    private Integer archived;
    private Integer pinned;

    private String aboveProjectId;

    public ProjectBase(ProjectBase projectBase) {
        BeanUtils.copyProperties(projectBase, this);
    }

    public ProjectBase() {
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreatorIdentity() {
        return creatorIdentity;
    }

    public void setCreatorIdentity(String creatorIdentity) {
        this.creatorIdentity = creatorIdentity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Integer getContributionNotAllowed() {
        return contributionNotAllowed;
    }

    public void setContributionNotAllowed(Integer contributionNotAllowed) {
        this.contributionNotAllowed = contributionNotAllowed;
    }

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public Integer getPinned() {
        return pinned;
    }

    public void setPinned(Integer pinned) {
        this.pinned = pinned;
    }

    public String getAboveProjectId() {
        return aboveProjectId;
    }

    public void setAboveProjectId(String aboveProjectId) {
        this.aboveProjectId = aboveProjectId;
    }

    @Override
    public String toString() {
        return "ProjectBase{" +
                "projectId='" + projectId + '\'' +
                ", creatorIdentity='" + creatorIdentity + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", website='" + website + '\'' +
                ", property=" + property +
                ", scope=" + scope +
                ", contributionNotAllowed=" + contributionNotAllowed +
                ", archived=" + archived +
                ", pinned=" + pinned +
                ", aboveProjectId='" + aboveProjectId + '\'' +
                '}';
    }
}
