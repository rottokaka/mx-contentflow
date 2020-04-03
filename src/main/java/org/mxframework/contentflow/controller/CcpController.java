package org.mxframework.contentflow.controller;

import org.mxframework.contentflow.application.ccp.BlogApplicationService;
import org.mxframework.contentflow.representation.MenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * CcpController: 协作内容创作控制器
 *
 * @author mx
 */
@PreAuthorize("hasRole('USER')")
@Controller
@RequestMapping("ccp")
public class CcpController {
    private static final Logger logger = LoggerFactory.getLogger(CcpController.class);

    @Autowired
    private BlogApplicationService blogApplicationService;

    /**
     * 获取用户内容主页
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping
    public ModelAndView getIndex(Model model) {
        List<MenuVO> menuVoList = new ArrayList<>();
        menuVoList.add(new MenuVO("博客管理", "/ccp/blog"));
        //menuVoList.add(new MenuVO("片段管理", "/ccp/snippet"));
        model.addAttribute("menuVoList", menuVoList);
        return new ModelAndView("ccp/index", "contentModel", model);
    }

    /**
     * 获取博客管理页面
     * <p>用户内容-博客管理</p>
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("blog")
    public ModelAndView getBlogManage(Model model) {
        logger.info("博客博客管理页面");
        model.addAttribute("blogManageVoList", blogApplicationService.listManageVo());
        return new ModelAndView("ccp/blog", "blogModel", model);
    }

}
