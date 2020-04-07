package org.mxframework.contentflow.controller.ccp;

import org.mxframework.contentflow.application.ccp.BlogApplicationService;
import org.mxframework.contentflow.constant.PaginationConstant;
import org.mxframework.contentflow.constant.ccp.BlogConstant;
import org.mxframework.contentflow.constant.ccp.SnippetConstant;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogConfigModifyForm;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogCreateForm;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogModifyForm;
import org.mxframework.contentflow.representation.sis.tag.vo.TagAtProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author mx
 */
@Controller
@RequestMapping("blog")
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogApplicationService blogApplicationService;

    /**
     * 获取博客主页页面
     *
     * @return 主页路径
     */
    @GetMapping
    public String getIndex() {
        logger.info("获取博客主页页面");
        return "redirect:blog/explore";
    }

    /**
     * 获取博客更多页面
     *
     * @param keyword 关键字
     * @param page    页码
     * @param size    大小
     * @param sort    排序
     * @param model   模型
     * @return 模型视图
     */
    @GetMapping("explore")
    public ModelAndView getMore(@RequestParam(required = false, defaultValue = "") String keyword
            , @RequestParam(required = false, defaultValue = PaginationConstant.PAGINATION_DEFAULT_PAGE) String page
            , @RequestParam(required = false, defaultValue = PaginationConstant.PAGINATION_DEFAULT_SIZE) String size
            , @RequestParam(required = false, defaultValue = SnippetConstant.SNIPPET_SORT_DEFAULT_CREATED_DESC) String sort
            , Model model) {
        logger.info("获取博客发现页面，分页信息，关键字：{}， 页码：{}， 每页数量：{}，排序：{}", keyword, page, size, sort);
        Pageable pageable = null;
        if (BlogConstant.BLOG_SORT_DEFAULT_CREATED_DESC.equals(sort)) {
            pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.Direction.DESC, "gmtCreate");
        }
        if (BlogConstant.BLOG_SORT_UPDATED_DESC.equals(sort)) {
            pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.Direction.DESC, "gmtModified");
        }
        Page<Blog> blogPage = blogApplicationService.pageByKeyword(keyword, pageable);
        List<Blog> content = blogPage.getContent();
        model.addAttribute("keyword", keyword);
        model.addAttribute("sort", sort);
        model.addAttribute("page", blogPage);
        model.addAttribute("blogCardVoList", content.isEmpty() ? null : blogApplicationService.listCardByBlogList(content));
        return new ModelAndView("blog/explore", "blogModel", model);
    }

    /**
     * 获取博客卡片页面，发布的
     *
     * @param username 用户账号
     * @param model    模型
     * @return 模型视图
     */
    @GetMapping("posted")
    public ModelAndView getCardByUsernamePosted(String username, Model model) {
        logger.info("获取博客卡片页面，发布的，帐号：{}", username);
        model.addAttribute("flag", "user");
        model.addAttribute("blogCardVoList", blogApplicationService.listCardVoByIdentity(username));
        return new ModelAndView("blog/card-list", "blogModel", model);
    }

    /**
     * 获取博客详情页面
     *
     * @param blogId 博客ID
     * @param model  模型
     * @return 模型视图
     */
    @GetMapping("{blogId}/detail")
    public ModelAndView getDetail(@PathVariable String blogId, Model model) {
        logger.info("获取博客详情页面，通过博客ID：{}", blogId);
        model.addAttribute("blogDetailVo", blogApplicationService.getDetailByBlogId(blogId));
        return new ModelAndView("blog/detail", "blogModel", model);
    }

    /**
     * 获取博客内容页面，通过博客ID
     *
     * @param blogId 博客ID
     * @param model  模型
     * @return 模型视图
     */
    @GetMapping("{blogId}/content")
    public ModelAndView getContent(@PathVariable String blogId, Model model) {
        logger.info("获取博客内容页面，通过博客ID：{}", blogId);
        model.addAttribute("content", blogApplicationService.getByBlogId(blogId).content());
        return new ModelAndView("blog/content-self", "blogModel", model);
    }

    /**
     * 列出所有标签，通过博客ID
     * <p>
     * 博客视图页面(blog/view.html)，加载标记该博客的标签集合
     * 功能：标签组呈现上通过颜色区别区分用户是否用标签标记过博客
     * 已确定，博客
     * 不确定，用户是否登陆，没登陆的用户处理规则为没有标记标签，
     * </p>
     * <p>
     * 获取关于博客的标签，调用
     * </p>
     *
     * @param blogId 博客ID
     * @param model  模型
     * @return 数据视图
     */
    @GetMapping("{blogId}/tags")
    public ModelAndView getTags(@PathVariable String blogId, Model model) {
        logger.info("获取博客标签页面，通过博客ID：{}", blogId);
        List<TagAtProductVO> tagAtProductVoList = blogApplicationService.listTagOfBlog(blogId);
        model.addAttribute("flag", "blog");
        model.addAttribute("tagAtProductVoList", tagAtProductVoList);
        return new ModelAndView("blog/tag/item-list", "tagModel", model);
    }

    /**
     * 获取博客创建页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('ROOT','USER')")
    public ModelAndView getCreate(Model model) {
        logger.info("获取博客创建页面");
        // 初始化BlogView @like Property or field 'id' cannot be found on null
        // 暂时性结论 如果涉及对象，必须初始化
        BlogCreateForm blogCreateForm = new BlogCreateForm();
        model.addAttribute("blogCreateForm", blogCreateForm);
        return new ModelAndView("blog/create", "blogModel", model);
    }

    /**
     * 获取博客更新页面
     *
     * @param blogId 博客ID
     * @param model  模型
     * @return 模型视图
     */
    @GetMapping("{blogId}/modify")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getModify(@PathVariable String blogId, Model model) {
        logger.info("获取博客修改页面，通过博客ID：{}", blogId);
        Blog blog = blogApplicationService.getByBlogId(blogId);
        BlogModifyForm blogModifyForm = new BlogModifyForm((blog));
        model.addAttribute("blogModifyForm", blogModifyForm);
        return new ModelAndView("blog/modify", "blogModel", model);
    }

    /**
     * 获取博客配置修改页面
     *
     * @param blogId 博客ID
     * @param model  模型
     * @return 模型视图
     */
    @GetMapping("{blogId}/config/modify")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getConfigModify(@PathVariable String blogId, Model model) {
        logger.info("获取博客配置修改页面，通过博客ID：{}", blogId);
        Blog blog = blogApplicationService.getByBlogId(blogId);
        BlogConfigModifyForm blogConfigModifyForm = new BlogConfigModifyForm(blog);
        model.addAttribute("blogConfigModifyForm", blogConfigModifyForm);
        return new ModelAndView("blog/config/modify", "blogModel", model);
    }
}
