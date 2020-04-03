package org.mxframework.contentflow.controller.iaa;

import org.mxframework.contentflow.application.iaa.UserApplicationService;
import org.mxframework.contentflow.representation.iaa.vo.UserDetailVO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectItemVO;
import org.mxframework.contentflow.representation.sis.tag.vo.TagItemVO;
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

import java.util.List;

/**
 * UserController: 用户控制器
 *
 * @author mx
 */
@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserApplicationService userApplicationService;

    /**
     * 获取用户主页页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping
    public ModelAndView index(Model model) {
        logger.info("获取用户主页页面");
        model.addAttribute("userVoList", userApplicationService.listCardVo());
        return new ModelAndView("user/index", "userModel", model);
    }

    /**
     * 获取用户详情页面
     *
     * @param username 用户账号
     * @param model    模型
     * @return 模型视图
     */
    @GetMapping(value = "{username}")
    public ModelAndView view(@PathVariable String username, Model model) {
        logger.info("获取用户详情页面");
        UserDetailVO userDetailVo = userApplicationService.getDetailVoByUsername(username);
        List<ProjectItemVO> projectItemVoList = userApplicationService.listProjectItemVoByUsername(username);
        List<TagItemVO> tagItemVoList = userApplicationService.listTagItemVoByUsername(username);
        model.addAttribute("userDetailVo", userDetailVo);
        model.addAttribute("projectItemVoList", projectItemVoList);
        model.addAttribute("tagItemVoList", tagItemVoList);
        return new ModelAndView("user/detail", "userModel", model);
    }

    /**
     * 获取用户创建页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("create")
    @PreAuthorize("hasRole('ROOT')")
    public ModelAndView create(Model model) {
        logger.info("获取用户创建页面");
        return new ModelAndView("user/create", "userModel", model);
    }

    /**
     * 获取用户修改页面
     *
     * @param userId 用户ID
     * @param model  模型
     * @return 模型视图
     */
    @GetMapping("modify")
    public ModelAndView modify(String userId, Model model) {
        logger.info("获取用户修改页面");
        model.addAttribute("userModifyForm", userApplicationService.getModifyFormByUserId(userId));
        return new ModelAndView("user/modify", "userModel", model);
    }

}
