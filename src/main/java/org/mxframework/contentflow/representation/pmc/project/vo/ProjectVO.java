package org.mxframework.contentflow.representation.pmc.project.vo;

import com.fasterxml.jackson.annotation.JsonView;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.representation.pmc.project.dto.ProjectDTO;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.domain.model.iaa.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ProjectVO: 项目[vo]
 *
 * @author mx
 */
public class ProjectVO {

    // 视图接口~
    //==================================================================================================================

    /**
     * 项目基本视图
     */
    public interface ProjectBaseView {
    }

    /**
     * 项目详细视图
     * 场景：用户的详情页面
     * 页面：project/view.html
     */
    public interface ProjectDetailView extends ProjectBaseView {
    }

    // 基本数据~ ref Project.class
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
     * website: 网址
     *
     * <p>Github.com</p>
     */
    private String website;

    /**
     * user: 用户
     */
    private User user;

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
     * aboveId: 上级ID
     */
    private Long aboveId;

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
     * aboveProject: 上级项目
     */
    private Project aboveProject;

    /**
     * belowProjectList: 下级项目列表
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

    public ProjectVO() {
    }

    public ProjectVO(Project project) {
        BeanUtils.copyProperties(project, this);
    }

    public static ProjectVO convert(ProjectDTO projectDto) {
        ProjectVO projectVO = new ProjectVO();
        BeanUtils.copyProperties(projectDto, projectVO);
        return projectVO;
    }

    public static List<ProjectVO> convert(List<ProjectDTO> projectDtoList) {
        List<ProjectVO> projectVoList = new ArrayList<>();
        projectDtoList.forEach(topicDto -> projectVoList.add(ProjectVO.convert(topicDto)));
        return projectVoList;
    }

    @JsonView(ProjectBaseView.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(ProjectBaseView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(ProjectBaseView.class)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonView(ProjectBaseView.class)
    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    @JsonView(ProjectBaseView.class)
    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    @JsonView(ProjectBaseView.class)
    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    @JsonView(ProjectBaseView.class)
    public Integer getPinned() {
        return pinned;
    }

    public void setPinned(Integer pinned) {
        this.pinned = pinned;
    }

    @JsonView(ProjectBaseView.class)
    public Long getAboveId() {
        return aboveId;
    }

    public void setAboveId(Long aboveId) {
        this.aboveId = aboveId;
    }

    @JsonView(ProjectDetailView.class)
    public Integer getNotAllowContribute() {
        return notAllowContribute;
    }

    public void setNotAllowContribute(Integer notAllowContribute) {
        this.notAllowContribute = notAllowContribute;
    }

    @JsonView(ProjectBaseView.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonView(ProjectBaseView.class)
    public Integer getCollected() {
        return collected;
    }

    public void setCollected(Integer collected) {
        this.collected = collected;
    }

    @JsonView(ProjectBaseView.class)
    public Project getAboveProject() {
        return aboveProject;
    }

    public void setAboveProject(Project aboveProject) {
        this.aboveProject = aboveProject;
    }

    @JsonView(ProjectBaseView.class)
    public List<Project> getBelowProjectList() {
        return belowProjectList;
    }

    @JsonView(ProjectBaseView.class)
    public void setBelowProjectList(List<Project> belowProjectList) {
        this.belowProjectList = belowProjectList;
    }

    @JsonView(ProjectBaseView.class)
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @JsonView(ProjectBaseView.class)
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
        return "ProjectVO{" +
                "id=" + id +
                ", property=" + property +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", user=" + user +
                ", hidden=" + hidden +
                ", archived=" + archived +
                ", pinned=" + pinned +
                ", aboveId=" + aboveId +
                ", notAllowContribute=" + notAllowContribute +
                ", collected=" + collected +
                ", aboveProject=" + aboveProject +
                '}';
    }
}
