package org.mxframework.contentflow.controller.pmc;

import org.mxframework.contentflow.application.pmc.SubjectApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * SubjectController: 专题控制器
 *
 * @author mx
 */
@RestController
@RequestMapping("subject")
public class SubjectController {
    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectApplicationService subjectApplicationService;

    /**
     * 获取专题管理页面
     *
     * @param versionId 版本ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("manage")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getManage(String versionId, Model model) {
        logger.info("获取专题管理页面");
        model.addAttribute("versionId", versionId);
        model.addAttribute("subjectManageVoList", subjectApplicationService.listManageVoByVersionId(versionId));
        return new ModelAndView("subject/manage-list", "subjectModel", model);
    }

    /**
     * 获取专题创建页面
     *
     * @param versionId 版本ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getCreate(String versionId, Model model) {
        logger.info("获取专题创建页面");
        model.addAttribute("versionId", versionId);
        return new ModelAndView("subject/create", "subjectModel", model);
    }

    /**
     * 获取专题修改页面
     *
     * @param subjectId 专题ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("modify")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getModify(String subjectId, Model model) {
        logger.info("获取专题修改页面");
        model.addAttribute("subjectModifyVo", subjectApplicationService.getModifyFormBySubjectId(subjectId));
        return new ModelAndView("subject/modify", "subjectModel", model);
    }
}
