package org.mxframework.contentflow.controller;

import org.mxframework.contentflow.application.sis.TagApplicationService;
import org.mxframework.contentflow.representation.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Controller
@RequestMapping("sis")
public class SisController {

    @Autowired
    private TagApplicationService tagApplicationService;

    /**
     * 获取项目管理中心主页
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping
    public ModelAndView getIndex(Model model) {
        List<MenuVO> menuVoList = new ArrayList<>();
        menuVoList.add(new MenuVO("标签管理", "/sis/tag"));
        model.addAttribute("menuVoList", menuVoList);
        return new ModelAndView("sis/index", "sisModel", model);
    }

    @GetMapping("tag")
    public ModelAndView getTagManage(Model model) {
        model.addAttribute("tagPersonManageVoList", tagApplicationService.listTagPersonManageVo());
        return new ModelAndView("sis/tag-person", "sisModel", model);
    }

}
