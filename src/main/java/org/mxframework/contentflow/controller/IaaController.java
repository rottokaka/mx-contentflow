package org.mxframework.contentflow.controller;

import org.mxframework.contentflow.application.iaa.UserApplicationService;
import org.mxframework.contentflow.representation.MenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * IaaController: 身份认证授权控制器
 *
 * @author mx
 */
@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("iaa")
public class IaaController {
    private static final Logger logger = LoggerFactory.getLogger(IaaController.class);

    @Autowired
    private UserApplicationService userApplicationService;

    /**
     * 获取用户设置页面
     *
     * @param model 数据模型
     * @return 模型视图
     */
    @GetMapping
    public ModelAndView getIndex(Model model) {
        List<MenuVO> menuVoList = new ArrayList<>();
        menuVoList.add(new MenuVO("个人信息", "/iaa/profile"));
        menuVoList.add(new MenuVO("账号设置", "/iaa/account"));
        model.addAttribute("menuVoList", menuVoList);
        return new ModelAndView("iaa/index", "settingModel", model);
    }

    /**
     * 获取个人信息主页
     * <p>设置中心-个人信息</p>
     *
     * @param model 数据模型
     * @return 模型视图
     */
    @GetMapping("profile")
    public ModelAndView getProfile(Model model) {
        model.addAttribute("userBaseVo", userApplicationService.getUserBaseVo());
        return new ModelAndView("iaa/profile", "userModel", model);
    }

    /**
     * 获取账号设置主页
     * <p>设置中心-账号设置</p>
     *
     * @param model 数据模型
     * @return 模型视图
     */
    @GetMapping("account")
    public ModelAndView getAccount(Model model) {
        model.addAttribute("userBaseVo", userApplicationService.getUserBaseVo());
        return new ModelAndView("iaa/account", "userModel", model);
    }
}
