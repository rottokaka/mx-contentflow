package org.mxframework.contentflow.controller.pmc;

import org.mxframework.contentflow.application.pmc.ProjectApplicationService;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectConfigModifyForm;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectDetailVO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectItemVO;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectModifyForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * ProjectController: 项目控制器
 *
 * @author mx
 */
@RestController
@RequestMapping("project")
public class ProjectController {
    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectApplicationService projectApplicationService;

    /**
     * 获取项目主页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping
    public ModelAndView getIndex(Model model) {
        List<ProjectItemVO> projectItemVoList = projectApplicationService.listItemVoTop();
        model.addAttribute("projectItemVoList", projectItemVoList);
        return new ModelAndView("project/index", "projectModel", model);
    }

    /**
     * 获取项目发现页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("explore")
    public ModelAndView getExplore(Model model) {
        model.addAttribute("projectItemVoList", projectApplicationService.listItemVoPublic());
        return new ModelAndView("project/list", "projectModel", model);
    }

    /**
     * 获取项目创建页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getCreate(Model model) {
        model.addAttribute("optionalAboveProjectList", projectApplicationService.listAboveVo());
        return new ModelAndView("project/create", "projectModel", model);
    }

    /**
     * 获取项目配置修稿页面
     *
     * @param projectId 项目ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("{projectId}/config/modify")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getConfigModify(@PathVariable String projectId, Model model) {
        ProjectConfigModifyForm projectConfigModifyForm = projectApplicationService.getConfigModifyVoByProjectId(projectId);
        model.addAttribute("projectConfigModifyForm", projectConfigModifyForm);
        return new ModelAndView("project/config/modify", "projectModel", model);
    }

    /**
     * 获取项目更新页面
     *
     * @param projectId 项目ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("{projectId}/modify")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getModify(@PathVariable String projectId, Model model) {
        ProjectModifyForm projectModifyForm = projectApplicationService.getModifyFormByProjectId(projectId);
        List<ProjectItemVO> aboveProjectItemVoList = projectApplicationService.listAboveItemVoByCreatorExcludeProjectId(projectId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("projectModifyForm", projectModifyForm);
        model.addAttribute("aboveProjectItemVoList", aboveProjectItemVoList);
        return new ModelAndView("project/modify", "projectModel", model);
    }


    /**
     * 获取项目视图页面
     *
     * @param projectId 项目ID
     * @param versionId 版本ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("{projectId}/detail")
    public ModelAndView getDetail(@PathVariable String projectId
            , @RequestParam(required = false, defaultValue = "") String versionId
            , Model model) {
        ProjectDetailVO projectDetailVo = projectApplicationService.getDetailVoByProjectId(projectId, versionId);
        model.addAttribute("projectDetailVo", projectDetailVo);
        return new ModelAndView("project/detail", "projectModel", model);
    }

}
