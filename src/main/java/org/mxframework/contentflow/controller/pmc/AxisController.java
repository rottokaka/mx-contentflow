package org.mxframework.contentflow.controller.pmc;

import org.mxframework.contentflow.application.pmc.AxisApplicationService;
import org.mxframework.contentflow.exception.MxException;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogCardVO;
import org.mxframework.contentflow.representation.pmc.axis.query.AxisQueryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * AxisController: 坐标控制器
 *
 * @author mx
 */
@Controller
@RequestMapping("axis")
public class AxisController {
    private final static Logger logger = LoggerFactory.getLogger(AxisController.class);

    @Autowired
    private AxisApplicationService axisApplicationService;

    /**
     * 查找所有博客，通过项目
     *
     * @param axisQueryVo 坐标查询[VO]
     * @param model       模型
     * @return 模型视图
     */
    @GetMapping("product")
    public ModelAndView getProduct(@Valid AxisQueryVO axisQueryVo, Model model) {
        List<BlogCardVO> blogCardVoList = axisApplicationService.listBlogCardByAxisQueryInFuzzy(axisQueryVo);
        model.addAttribute("flag", "project");
        model.addAttribute("blogCardVoList", blogCardVoList);
        return new ModelAndView("axis/product/blog/card-list", "blogModel", model);
    }

    /**
     * 获取项目的投稿页面
     * <p>投稿，博客投稿项目</p>
     *
     * @param projectId 项目ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("project/contribute")
    public ModelAndView getContribute(String projectId, Model model) {
        try {
            List<BlogCardVO> blogCardVoList = axisApplicationService.listBlogCardVoOnContribute(projectId);
            model.addAttribute("blogCardVoList", blogCardVoList);
        } catch (MxException me) {
            model.addAttribute("message", me.getMessage());
        }
        return new ModelAndView("axis/project/contribute", "blogModel", model);
    }

    /**
     * 获取坐标下的博客集合，用于导出，展示
     * <p>导出：加载当前坐标的可展示博客列表页面</p>
     *
     * @param axisQueryVo 坐标查询
     * @param model       模型
     * @return 模型视图
     */
    @GetMapping("product/export")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getProductExport(@Valid AxisQueryVO axisQueryVo, Model model) {
        model.addAttribute("productAtAxisVoList", axisApplicationService.exportProductsByAxisQueryVo(axisQueryVo));
        return new ModelAndView("axis/export", "axisModel", model);
    }

    /**
     * 获取坐标下的博客集合，用于导入，管理
     * <p>导入：加载当前坐标的可管理博客列表页面</p>
     *
     * @param axisQueryVo 坐标查询
     * @param model       模型
     * @return 模型视图
     */
    @GetMapping("product/import")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getProductImport(AxisQueryVO axisQueryVo, Model model) {
        List<BlogCardVO> blogCardVoList = axisApplicationService.importProductsByAxisQuery(axisQueryVo);
        model.addAttribute("blogCardVoList", blogCardVoList);
        return new ModelAndView("axis/import", "axisModel", model);
    }

    /**
     * 获取博客收录页面
     * <p>收录，项目收录博客</p>
     *
     * @param blogId 博客ID
     * @param model  模型
     * @return 模型视图
     */
    @GetMapping("product/collect")
    public ModelAndView collect(String blogId, Model model) {
        try {
            model.addAttribute("projectAtBlogVoList", axisApplicationService.listProjectAtBlogOnCollect(blogId));
        } catch (MxException me) {
            model.addAttribute("message", me.getMessage());
        }
        return new ModelAndView("axis/product/blog/collect", "projectModel", model);
    }

    /**
     * 获取博客项目条目页面
     * <p>列出所有项目，以按钮组的方式，通过博客</p>
     *
     * @param blogId 博客ID
     * @param model  模型
     * @return 模型视图
     */
    @GetMapping("/blog/project_item")
    public ModelAndView listInBtnByBlog(String blogId, Model model) {
        model.addAttribute("projectItemVoList", axisApplicationService.listProjectItemVoByBlogId(blogId));
        model.addAttribute("flag", "blog");
        return new ModelAndView("axis/product/blog/project-item", "projectModel", model);
    }

}
