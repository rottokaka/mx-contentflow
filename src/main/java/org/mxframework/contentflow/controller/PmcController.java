package org.mxframework.contentflow.controller;

import org.mxframework.contentflow.application.pmc.ProjectApplicationService;
import org.mxframework.contentflow.representation.MenuVO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectItemVO;
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
 * PmcController: 项目管理中心控制器
 *
 * @author mx
 */
@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("pmc")
public class PmcController {
    private static final Logger logger = LoggerFactory.getLogger(PmcController.class);

    @Autowired
    private ProjectApplicationService projectApplicationService;

    /**
     * 获取项目管理中心主页
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping
    public ModelAndView getIndex(Model model) {
        List<MenuVO> menuVoList = new ArrayList<>();
        menuVoList.add(new MenuVO("项目管理", "/pmc/project"));
        menuVoList.add(new MenuVO("因素管理", "/pmc/factor"));
        menuVoList.add(new MenuVO("坐标管理", "/pmc/axis"));
        model.addAttribute("menuVoList", menuVoList);
        return new ModelAndView("pmc/index", "contentModel", model);
    }

    /**
     * 获取项目管理页面
     * <p>项目管理中心-项目管理</p>
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("project")
    public ModelAndView getProjectManage(Model model) {
        model.addAttribute("projectManageVoList"
                , projectApplicationService.listManageVoByIdentity());
        return new ModelAndView("pmc/project", "projectModel", model);
    }

    /**
     * 获取因素管理页面
     * <p>项目管理中心-因素管理（版本，专题和类型）</p>
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("factor")
    public ModelAndView getSubjectManage(Model model) {
        model.addAttribute("projectItemVoList"
                , projectApplicationService.listItemVoByIdentity());
        return new ModelAndView("pmc/factor", "projectModel", model);
    }


    /**
     * 获取坐标管理页面
     * <p>项目管理中心-坐标管理</p>
     *
     * @param model    模型
     * @return 模型视图
     */
    @GetMapping("axis")
    public ModelAndView getAxisManage( Model model) {
        List<ProjectItemVO> projectItemVoList = projectApplicationService.listItemVoByIdentity();
        model.addAttribute("projectItemVoList", projectItemVoList);
        return new ModelAndView("pmc/axis", "axisModel", model);
    }
}
