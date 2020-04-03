package org.mxframework.contentflow.domain.model.pmc.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.constant.pmc.ProjectConstant;
import org.mxframework.contentflow.constant.pmc.ProjectExceptionEnum;
import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;
import org.mxframework.contentflow.exception.ProjectException;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Project: 项目
 *
 * @author mx
 */
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Project extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "项目ID不能为空")
    @Embedded
    private ProjectId projectId;

    @NotNull(message = "项目创建者不能为空")
    @Embedded
    private Creator creator;

    @NotNull(message = "项目名称不能为空")
    @Column
    private String name;

    @Column
    private String description;

    @Column(nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '性质'")
    private Integer property;

    @Size(max = 200, message = "头像地址长度不能超过‘200’")
    @Column(columnDefinition = "VARCHAR(200) COMMENT '头像地址'")
    private String avatar;

    @Size(max = 100, message = "网站地址长度不能超过‘100’")
    @Column(columnDefinition = "VARCHAR(100) COMMENT '网站地址'")
    private String website;

    @Column(name = "scope", columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '范围'")
    private Integer scope;

    @Column(name = "contribution_not_allowed", columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '投稿是否被允许'")
    private Integer contributionNotAllowed;

    @Column(name = "is_archived", columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '是否归档'")
    private Integer archived;

    @Column(name = "is_pinned", columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '是否置顶'")
    private Integer pinned;

    @Column(name = "above_project_id")
    private String aboveProjectId;

    public Project(ProjectId projectId
            , Creator creator
            , String name
            , String description
            , Integer property
            , String avatar
            , String website
            , String aboveProjectId
            , Integer scope
            , Integer contributionNotAllowed
            , Integer archived
            , Integer pinned) {
        this();
        this.setProjectId(projectId);
        this.setCreator(creator);
        this.setProperty(property);
        this.setName(name);
        this.setDescription(description);
        this.setAvatar(avatar);
        this.setWebsite(website);
        this.setAboveProjectId(aboveProjectId);
        this.setScope(scope);
        this.setContributionNotAllowed(contributionNotAllowed);
        this.setArchived(archived);
        this.setPinned(pinned);
    }

    public Project(ProjectId projectId, Creator creator, String name) {
        this(projectId, creator, name, null, null, null, null, null, null, null, null, null);
    }

    protected Project() {
        super();
    }

    public ProjectId projectId() {
        return this.projectId;
    }

    public Creator creator() {
        return this.creator;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public Integer property() {
        return this.property;
    }

    public String avatar() {
        return this.avatar;
    }

    public String website() {
        return this.website;
    }

    public String aboveProjectId() {
        return this.aboveProjectId;
    }

    public Integer scope() {
        return this.scope;
    }

    public Integer contributionNotAllowed() {
        return this.contributionNotAllowed;
    }

    public Integer archived() {
        return this.archived;
    }

    public Integer pinned() {
        return this.pinned;
    }

    public void setProjectId(ProjectId projectId) {
        if (projectId != null) {
            this.projectId = projectId;
        } else {
            throw new ProjectException(ProjectExceptionEnum.PROJECT_CONSTRAINT_VIOLATION_PROJECTID_NOT_NULL);
        }
    }

    public void setCreator(Creator creator) {
        if (creator != null) {
            this.creator = creator;
        } else {
            throw new ProjectException(ProjectExceptionEnum.PROJECT_CONSTRAINT_VIOLATION_CREATOR_NOT_NULL);
        }
    }

    public void setName(String name) {
        if (creator != null) {
            this.name = name;
        } else {
            throw new ProjectException(ProjectExceptionEnum.PROJECT_CONSTRAINT_VIOLATION_NAME_NOT_NULL);
        }
    }

    public void setDescription(String description) {
        if (description != null && !"".equals(description.trim())) {
            this.description = description;
        } else {
            this.description = this.name();
        }
    }

    public void setProperty(Integer property) {
        if (property != null) {
            this.property = property;
        } else {
            this.property = ProjectConstant.PROJECT_PROPERTY_PRIVATE;
        }
    }

    public void setAvatar(String avatar) {
        if (avatar != null && !"".equals(avatar.trim())) {
            this.avatar = avatar;
        } else {
            this.avatar = null;
        }
    }

    public void setWebsite(String website) {
        if (website != null && !"".equals(website.trim())) {
            this.website = website;
        } else {
            this.website = null;
        }
    }

    public void setAboveProjectId(String aboveProjectId) {
        if (aboveProjectId != null && !"".equals(aboveProjectId.trim())) {
            this.aboveProjectId = aboveProjectId;
        } else {
            this.aboveProjectId = ProjectConstant.PROJECT_DEFAULT_ABOVE_ID;
        }
    }

    public void setScope(Integer scope) {
        if (scope != null) {
            this.scope = scope;
        } else {
            this.scope = ScopeConstant.SCOPE_PUBLIC;
        }
    }

    public void setContributionNotAllowed(Integer contributionNotAllowed) {
        if (contributionNotAllowed != null) {
            this.contributionNotAllowed = contributionNotAllowed;
        } else {
            this.contributionNotAllowed = ProjectConstant.PROJECT_CONTRIBUTION_NOT_ALLOWED_DEFAULT_FALSE;
        }
    }

    public void setArchived(Integer archived) {
        if (archived != null) {
            this.archived = archived;
        } else {
            this.archived = ProjectConstant.PROJECT_ARCHIVED_NOT;
        }
    }

    public void setPinned(Integer pinned) {
        if (pinned != null) {
            this.pinned = pinned;
        } else {
            this.pinned = ProjectConstant.PROJECT_PINNED_NOT;
        }
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", creator=" + creator +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", property=" + property +
                ", avatar='" + avatar + '\'' +
                ", website='" + website + '\'' +
                ", aboveProjectId='" + aboveProjectId + '\'' +
                ", scope=" + scope +
                ", contributionNotAllowed=" + contributionNotAllowed +
                ", archived=" + archived +
                ", pinned=" + pinned +
                '}';
    }
}
