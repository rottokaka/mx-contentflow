package org.mxframework.contentflow.service.pmc.translator;

import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.constant.pmc.ProjectConstant;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.section.Section;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.domain.model.pmc.project.subject.Subject;
import org.mxframework.contentflow.exception.NotFoundException;
import org.mxframework.contentflow.representation.pmc.project.ProjectBase;
import org.mxframework.contentflow.representation.pmc.project.dto.ProjectItemDTO;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectConfigModifyForm;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectModifyForm;
import org.mxframework.contentflow.representation.pmc.project.vo.*;
import org.mxframework.contentflow.service.pmc.project.ProjectService;
import org.mxframework.contentflow.service.pmc.project.SectionService;
import org.mxframework.contentflow.service.pmc.project.SubjectService;
import org.mxframework.contentflow.service.pmc.project.VersionService;
import org.mxframework.contentflow.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class ProjectTranslator {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private VersionService versionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private SectionTranslator sectionTranslator;
    @Autowired
    private SubjectTranslator subjectTranslator;
    @Autowired
    private VersionTranslator versionTranslator;

    public ProjectModifyForm convertToModifyForm(Project project) {
        ProjectModifyForm projectModifyForm = new ProjectModifyForm();
        projectModifyForm.setName(project.name());
        projectModifyForm.setDescription(project.description());
        projectModifyForm.setWebsite(project.website());
        projectModifyForm.setAboveProjectId(project.aboveProjectId());
        return projectModifyForm;
    }

    public ProjectDetailVO convertToDetailVo(Project project, Version version) {
        Integer scope = project.scope();
        // 暂不处理内部和秘密范围
        if (ScopeConstant.SCOPE_INTERNAL.equals(scope) || ScopeConstant.SCOPE_SECRET.equals(scope)) {
            throw new NotFoundException();
        }
        // 理论上如果项目范围是私密，非项目作者不应该访问，也不允许查看
        if (ScopeConstant.SCOPE_PRIVATE.equals(scope) && !SecurityUtil.isPrincipal(project.creator().identity())) {
            throw new NotFoundException();
        }
        // 显示所有
        ProjectDetailVO projectDetailVo = new ProjectDetailVO(convertToBase(project));
        if (!ProjectConstant.PROJECT_DEFAULT_ABOVE_ID.equals(project.aboveProjectId())) {
            projectDetailVo.setAboveProjectItemVo(convertToItemVo(projectService.getByProjectId(new ProjectId(project.aboveProjectId()))));
        }
        List<Project> projectList = projectService.listByAboveProjectId(project.projectId().id());
        if (!projectList.isEmpty()) {
            projectDetailVo.setBelowProjectItemVoList(convertToItemVo(projectList));
        }
        projectDetailVo.setVersionItemVoList(versionTranslator.convertToItemVo(versionService.listByProjectId(project.projectId())));
        projectDetailVo.setAppointedVersionVo(versionTranslator.convertToItemVo(version));
        List<Subject> subjectList = subjectService.listByVersionId(version.versionId());
        projectDetailVo.setSubjectItemVoList(subjectList.isEmpty() ? null : subjectTranslator.convertToItemVo(subjectList));
        List<Section> sectionList = sectionService.listByVersionId(version.versionId());
        projectDetailVo.setSectionItemVoList(sectionList.isEmpty() ? null : sectionTranslator.convertToItemVo(sectionList));
        // 子项目集合：范围不限
        return projectDetailVo;
    }

    private ProjectBase convertToBase(Project project) {
        ProjectBase projectBase = new ProjectBase();
        projectBase.setProjectId(project.projectId().id());
        projectBase.setCreatorIdentity(project.creator().identity());
        projectBase.setName(project.name());
        projectBase.setDescription(project.description());
        projectBase.setAvatar(project.avatar());
        projectBase.setWebsite(project.website());
        projectBase.setProperty(project.property());
        projectBase.setScope(project.scope());
        projectBase.setContributionNotAllowed(project.contributionNotAllowed());
        projectBase.setArchived(project.archived());
        projectBase.setPinned(project.pinned());
        projectBase.setAboveProjectId(project.aboveProjectId());
        return projectBase;
    }

    public ProjectBaseVO convertToBaseVo(Project project) {
        return new ProjectBaseVO(convertToBase(project));
    }

    public ProjectBaseVO convertToProjectBaseVo(Project project) {
        return new ProjectBaseVO(convertToBase(project));
    }

    public List<ProjectAboveVO> convertToAboveVo(List<Project> projects) {
        List<ProjectAboveVO> projectAboveVoList = new ArrayList<>(projects.size());
        projects.forEach(project -> projectAboveVoList.add(new ProjectAboveVO(convertToBase(project))));
        return projectAboveVoList;
    }

    public List<ProjectItemDTO> convertToItemDTO(List<Project> projects) {
        List<ProjectItemDTO> projectItemDtos = new ArrayList<>(projects.size());
        projects.forEach(project -> projectItemDtos.add(new ProjectItemDTO(convertToBase(project))));
        return projectItemDtos;
    }

    public ProjectItemVO convertToItemVo(Project project) {
        return new ProjectItemVO(convertToBase(project));
    }

    public List<ProjectItemVO> convertToItemVo(List<Project> projects) {
        List<ProjectItemVO> projectItemVos = new ArrayList<>(projects.size());
        projects.forEach(project -> projectItemVos.add(new ProjectItemVO(convertToBase(project))));
        return projectItemVos;
    }

    public List<ProjectManageVO> convertToManageVo(List<Project> projects) {
        List<ProjectManageVO> projectManageVoList = new ArrayList<>(projects.size());
        projects.forEach(project -> projectManageVoList.add(new ProjectManageVO(convertToBase(project))));
        return projectManageVoList;
    }

    public ProjectConfigModifyForm convertToConfigModifyVo(Project project) {
        ProjectConfigModifyForm modifyVo = new ProjectConfigModifyForm();
        modifyVo.setProjectId(project.projectId().id());
        modifyVo.setProjectName(project.name());
        modifyVo.setScope(project.scope());
        modifyVo.setContributionNotAllowed(project.contributionNotAllowed());
        return modifyVo;
    }

    public List<ProjectAtBlogVO> convertToProjectAtBlogVo(List<Project> projectList) {
        List<ProjectAtBlogVO> projectAtBlogVoList = new ArrayList<>(projectList.size());
        projectList.forEach(project -> projectAtBlogVoList.add(new ProjectAtBlogVO(convertToBase(project))));
        return projectAtBlogVoList;
    }

}
