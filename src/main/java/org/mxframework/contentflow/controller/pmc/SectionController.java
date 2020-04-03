package org.mxframework.contentflow.controller.pmc;

import org.mxframework.contentflow.application.pmc.SectionApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * SectionController: 类型控制器
 *
 * @author mx
 */
@Controller
@RequestMapping("section")
public class SectionController {
    private final static Logger logger = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionApplicationService sectionApplicationService;

    /**
     * 获取类型管理页面
     *
     * @param versionId 版本ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("manage")
    public ModelAndView getManage(String versionId, Model model) {
        logger.info("获取类型管理页面");
        model.addAttribute("versionId", versionId);
        model.addAttribute("sectionManageVoList", sectionApplicationService.listManageVoByVersionId(versionId));
        return new ModelAndView("section/manage-list", "sectionModel", model);
    }

    /**
     * 获取类型创建页面
     *
     * @param versionId 版本ID
     * @param subjectId 专题ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getCreate(String versionId, String subjectId, Model model) {
        logger.info("获取类型创建页面");
        model.addAttribute("versionId", versionId);
        model.addAttribute("subjectId", subjectId);
        return new ModelAndView("section/create", "sectionModel", model);
    }

    /**
     * 获取类型修改页面
     *
     * @param sectionId 专题ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("modify")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getModify(String sectionId, Model model) {
        logger.info("获取类型修改页面");
        model.addAttribute("sectionModifyVo", sectionApplicationService.getModifyFormBySectionId(sectionId));
        return new ModelAndView("section/modify", "sectionModel", model);
    }

}
