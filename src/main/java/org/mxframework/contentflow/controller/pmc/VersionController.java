
package org.mxframework.contentflow.controller.pmc;

import org.mxframework.contentflow.application.pmc.VersionApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * VersionController: 版本控制器
 *
 * @author mx
 */
@Controller
@RequestMapping("version")
public class VersionController {
    private final static Logger logger = LoggerFactory.getLogger(VersionController.class);

    @Autowired
    private VersionApplicationService versionApplicationService;

    /**
     * 获取所有版本
     *
     * @param projectId 项目ID
     * @param model     数据模型
     * @return 模型视图
     */
    @GetMapping("manage")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getManage(String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("versionManageVoList", versionApplicationService.listManageVoByProjectId(projectId));
        return new ModelAndView("version/manage-list", "versionModel", model);
    }

    /**
     * 获取版本新建页面
     *
     * @param projectId 项目ID
     * @param model     数据模型
     * @return 模型视图
     */
    @GetMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getCreate(String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return new ModelAndView("version/create", "versionModel", model);
    }

    /**
     * 获取版本更新页面
     *
     * @param versionId 版本ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("{versionId}/modify")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView update(@PathVariable String versionId, Model model) {
        model.addAttribute("versionModifyForm", versionApplicationService.getModifyFormByVersionId(versionId));
        return new ModelAndView("version/modify", "versionModel", model);
    }
}
