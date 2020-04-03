package org.mxframework.contentflow.representation.pmc.project.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectVO;
import org.mxframework.contentflow.domain.model.iaa.User;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

/**
 * ProjectDTO: 项目[DTO]
 *
 * @author mx
 */
public class ProjectDTO {

    // 基本数据~ ref Topic.class
    //==================================================================================================================

    /**
     * id: 项目ID
     */
    private Long id;

    /**
     * property: 项目性质
     */
    private Integer property;

    /**
     * name: 名称
     */
    private String name;

    /**
     * description: 描述，对名称的解释，具体的描述
     */
    private String description;

    /**
     * website：网址
     */
    private String website;

    /**
     * aboveId: 上级ID
     */
    private Long aboveId;

    /**
     * user: 用户
     */
    private User user;

    // 配置数据~ ref TopicConfig.class
    //==================================================================================================================



    /**
     * hidden: 是否隐藏
     */
    private Integer hidden;

    /**
     * archived: 是否归档
     */
    private Integer archived;

    /**
     * pinned: 是否置顶
     */
    private Integer pinned;

    /**
     * notAllowContribute: 是否允许投稿
     */
    private Integer notAllowContribute;

    // 关系数据~ ref
    //==================================================================================================================


    // 2. 项目收录
    //==================================================================================================================
    // 逻辑：判断topic.blogSet是否包含blog。0-未收录【默认】；1-已收录
    // 实例：页面，user/project_col.html；解释，项目是否对该博客进行收录

    /**
     * collected: 是否收录
     * aspect: #2
     */
    private Integer collected;

    /**
     * aboveTopic: 上级项目
     */
    private Project aboveProject;

    /**
     * belowTopicList: 下级项目列表
     */
    private List<Project> belowProjectList;

    /**
     * userSet: 用户集合
     */
    private Set<User> userSet;

    /**
     * tagSet: 标签集合
     */
    private Set<Tag> tagSet;

    // 项目

    public ProjectDTO() {
    }

    public ProjectDTO(Project project) {
        BeanUtils.copyProperties(project, this);
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Integer getPinned() {
        return pinned;
    }

    public void setPinned(Integer pinned) {
        this.pinned = pinned;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Long getAboveId() {
        return aboveId;
    }

    public void setAboveId(Long aboveId) {
        this.aboveId = aboveId;
    }

    @JsonView(ProjectVO.ProjectDetailView.class)
    public Integer getNotAllowContribute() {
        return notAllowContribute;
    }

    public void setNotAllowContribute(Integer notAllowContribute) {
        this.notAllowContribute = notAllowContribute;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Integer getCollected() {
        return collected;
    }

    public void setCollected(Integer collected) {
        this.collected = collected;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Project getAboveProject() {
        return aboveProject;
    }

    public void setAboveProject(Project aboveProject) {
        this.aboveProject = aboveProject;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public List<Project> getBelowProjectList() {
        return belowProjectList;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public void setBelowProjectList(List<Project> belowProjectList) {
        this.belowProjectList = belowProjectList;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @JsonView(ProjectVO.ProjectBaseView.class)
    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return String.format("Topic[id:%d, name:'%s', description:'%s']", this.getId(), this.getName(), this.getDescription());
    }
}
