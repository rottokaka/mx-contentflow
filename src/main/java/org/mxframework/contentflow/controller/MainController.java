package org.mxframework.contentflow.controller;

import org.mxframework.contentflow.constant.iaa.UserEnum;
import org.mxframework.contentflow.domain.model.iaa.Authority;
import org.mxframework.contentflow.domain.model.iaa.User;
import org.mxframework.contentflow.service.iaa.AuthorityService;
import org.mxframework.contentflow.service.iaa.UserService;
import org.mxframework.contentflow.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * MainController: 主要控制器
 *
 * @author mx
 */
@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "redirect:/blog";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMessage", "登陆失败，用户名或者密码错误");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        User principal = SecurityUtil.getPrincipal();
        if (principal != null) {
            return "redirect:/user/" + principal.getUsername();
        } else {
            return "register";
        }
    }

    @PostMapping("/register")
    public String registerUse(User user) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityService.findById(UserEnum.USER_AUTHORITY_USER.getId()));
        user.setAuthorities(authorities);
        user.setPassword(user.getPassword());
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/404")
    public String nfe() {
        return "/404";
    }
}
