package org.mxframework.contentflow.application.pmc;

import org.mxframework.contentflow.application.iaa.IdentityApplicationService;
import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.constant.pmc.ProjectConstant;
import org.mxframework.contentflow.constant.pmc.VersionConstant;
import org.mxframework.contentflow.domain.model.pmc.indentity.Creator;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.representation.pmc.project.dto.ProjectItemDTO;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectConfigModifyForm;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectCreateForm;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectModifyForm;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectAboveVO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectDetailVO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectItemVO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectManageVO;
import org.mxframework.contentflow.service.pmc.project.ProjectService;
import org.mxframework.contentflow.service.pmc.translator.ProjectTranslator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mx
 */
@Service
public class ProjectApplicationService {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectTranslator projectTranslator;

    @Autowired
    private AxisApplicationService axisApplicationService;
    @Autowired
    private VersionApplicationService versionApplicationService;
    @Autowired
    private IdentityApplicationService identityApplicationService;

    public Project getByProjectId(String projectId) {
        return projectService.getByProjectId(new ProjectId(projectId));
    }

    public ProjectItemVO getItemVoByProjectId(String projectId) {
        Project project = projectService.getByProjectId(new ProjectId(projectId));
        return projectTranslator.convertToItemVo(project);
    }

    /**
     * 获取项目详情，通过项目ID和版本ID
     *
     * @param projectId 项目ID[M]
     * @param versionId 版本ID[O]
     * @return 项目详情[VO]
     */
    public ProjectDetailVO getDetailVoByProjectId(String projectId, String versionId) {
        Version version;
        if (versionId != null && !"".equals(versionId.trim())) {
            version = versionApplicationService.getByVersionId(versionId);
        } else {
            version = versionApplicationService.getDefaultByProjectId(projectId);
        }
        Project project = projectService.getByProjectId(new ProjectId(projectId));
        return projectTranslator.convertToDetailVo(project, version);
    }

    public ProjectModifyForm getModifyFormByProjectId(String projectId) {
        return projectTranslator.convertToModifyForm(projectService.getByProjectId(new ProjectId(projectId)));
    }

    public ProjectConfigModifyForm getConfigModifyVoByProjectId(String projectId) {
        Project project = projectService.getByProjectId(new ProjectId(projectId));
        return projectTranslator.convertToConfigModifyVo(project);
    }

    public List<Project> list() {
        return null;
    }

    public List<ProjectAboveVO> listAboveVo() {
        List<Project> projectList = projectService.listByCreator(new Creator(identityApplicationService.identity()));
        if (projectList != null && projectList.size() > 0) {
            return projectTranslator.convertToAboveVo(projectList);
        } else {
            return null;
        }
    }

    public List<ProjectItemVO> listItemVoByIdentityPublic(String identity) {
        return projectTranslator.convertToItemVo(projectService.listByCreatorAndScope(new Creator(identity), ScopeConstant.SCOPE_PUBLIC));
    }

    public List<ProjectItemVO> listItemVoByIdentity() {
        List<Project> projectList = projectService.listByCreator(new Creator(identityApplicationService.identity()));
        if (projectList != null && projectList.size() > 0) {
            return projectTranslator.convertToItemVo(projectList);
        } else {
            return null;
        }
    }

    public List<ProjectItemVO> listAboveItemVoByCreatorExcludeProjectId(String projectId) {
        List<Project> projects = projectService.listByCreator(new Creator(identityApplicationService.identity()));
        projects.removeIf(project -> project.projectId().id().equals(projectId));
        return projectTranslator.convertToItemVo(projects);
    }

    public List<ProjectItemDTO> listItemByIdentity(String identity) {
        List<Project> projects = projectService.listByCreator(new Creator(identity));
        return projectTranslator.convertToItemDTO(projects);
    }

    public List<ProjectItemDTO> listItemByIdentityAndScope(String identity, Integer scope) {
        List<Project> projects = projectService.listByCreatorAndScope(new Creator(identity), scope);
        return projectTranslator.convertToItemDTO(projects);
    }

    public List<ProjectItemVO> listItemVoPublic() {
        List<Project> projectList = projectService.listByScope(ScopeConstant.SCOPE_PUBLIC);
        if (projectList != null && projectList.size() > 0) {
            return projectTranslator.convertToItemVo(projectList);
        } else {
            return null;
        }
    }

    public List<ProjectItemVO> listItemVoTop() {
        List<Project> projectList = projectService.listByAboveProjectIdIdAndScope(ProjectConstant.PROJECT_DEFAULT_ABOVE_ID
                , ScopeConstant.SCOPE_PUBLIC);
        if (projectList != null && projectList.size() > 0) {
            return projectTranslator.convertToItemVo(projectList);
        } else {
            return null;
        }
    }

    public List<ProjectManageVO> listManageVoByIdentity() {
        List<Project> projectList = projectService.listByCreator(new Creator(identityApplicationService.identity()));
        if (projectList != null && projectList.size() > 0) {
            return projectTranslator.convertToManageVo(projectList);
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void post(ProjectCreateForm projectCreateForm) {
        ProjectId projectId = projectService.nextIdentity();
        String projectName = projectCreateForm.getName();
        Project project = new Project(projectId, new Creator(identityApplicationService.identity()), projectName);
        project.setDescription(projectCreateForm.getDescription());
        project.setWebsite(projectCreateForm.getWebsite());
        project.setScope(projectCreateForm.getScope());
        project.setContributionNotAllowed(projectCreateForm.getContributionNotAllowed());
        project.setAboveProjectId(projectCreateForm.getAboveProjectId());
        // 添加默认版本
        VersionId versionId = versionApplicationService.nextIdentity();
        Version version = new Version(versionId, projectId, projectName + VersionConstant.VERSION_DEFAULT_NAME_SUFFIX);
        version.setRank(versionApplicationService.listByProjectId(projectId.id()).size());
        versionApplicationService.save(version);
        projectService.add(project);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void updateById(ProjectId projectId, ProjectModifyForm projectModifyForm) {
        Project originalProject = projectService.getByProjectId(projectId);
        // 属性复制
        BeanUtils.copyProperties(projectModifyForm, originalProject, "id");
        projectService.add(originalProject);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void patchOnConfig(String projectId, ProjectConfigModifyForm projectConfigModifyForm) {
        Project byProjectId = projectService.getByProjectId(new ProjectId(projectId));
        byProjectId.setScope(projectConfigModifyForm.getScope());
        byProjectId.setContributionNotAllowed(projectConfigModifyForm.getContributionNotAllowed());
        projectService.add(byProjectId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void updateByProjectId(String projectId, ProjectModifyForm projectModifyForm) {
        Project byProjectId = projectService.getByProjectId(new ProjectId(projectId));
        byProjectId.setName(projectModifyForm.getName());
        byProjectId.setDescription(projectModifyForm.getDescription());
        byProjectId.setWebsite(projectModifyForm.getWebsite());
        String aboveProjectId = projectModifyForm.getAboveProjectId();
        if (aboveProjectId != null && !"".equals(aboveProjectId.trim())) {
            byProjectId.setAboveProjectId(aboveProjectId);
        } else {
            byProjectId.setAboveProjectId(ProjectConstant.PROJECT_DEFAULT_ABOVE_ID);
        }
        projectService.add(byProjectId);
    }

    public void deleteByProjectId(String projectId) {
        Project project = projectService.getByProjectId(new ProjectId(projectId));
        // 项目级别：关联子项目处理
        List<Project> belowProjectList = projectService.listByAboveProjectId(project.projectId().id());
        if (!belowProjectList.isEmpty()) {
            // for强循环用户遍历，不修改数据
            for (int i = 0; i < belowProjectList.size(); i++) {
                Project temp = belowProjectList.get(i);
                temp.setAboveProjectId(ProjectConstant.PROJECT_DEFAULT_ABOVE_ID);
                belowProjectList.set(i, temp);
            }
            projectService.addAll(belowProjectList);
        }
        // 坐标级别
        axisApplicationService.deleteAllByProjectId(projectId);
        // 版本级别
        versionApplicationService.deleteAllByProjectId(projectId);
        // 删除项目
        projectService.remove(projectService.getByProjectId(project.projectId()));
    }

}
