package org.mxframework.contentflow.controller.ccp;

import org.mxframework.contentflow.application.ccp.SnippetApplicationService;
import org.mxframework.contentflow.constant.PaginationConstant;
import org.mxframework.contentflow.constant.ccp.SnippetConstant;
import org.mxframework.contentflow.controller.IaaController;
import org.mxframework.contentflow.domain.model.ccp.product.snippet.Snippet;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetCardVO;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * SnippetController: 片段控制器
 *
 * @author mx
 */
@Controller
@RequestMapping("snippet")
public class SnippetController {
    private static final Logger logger = LoggerFactory.getLogger(IaaController.class);

    @Autowired
    private SnippetApplicationService snippetApplicationService;

    /**
     * 获取片段主页页面
     *
     * @return 字符串，导向片段发现页面
     */
    @GetMapping
    public String getIndex() {
        logger.info("获取片段主页页面");
        return "forward:/snippet/discover";
    }

    /**
     * 获取片段主页页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("discover")
    public ModelAndView getDiscover(@RequestParam(required = false, defaultValue = "") String keyword
            , @RequestParam(required = false, defaultValue = PaginationConstant.PAGINATION_DEFAULT_PAGE) String page
            , @RequestParam(required = false, defaultValue = PaginationConstant.PAGINATION_DEFAULT_SIZE) String size
            , @RequestParam(required = false, defaultValue = SnippetConstant.SNIPPET_SORT_DEFAULT_CREATED_DESC) String sort
            , Model model) {
        logger.info("获取片段发现页面");
        Page<SnippetCardVO> snippetPage = snippetApplicationService.pageByKeyword(Integer.parseInt(page) - 1, Integer.parseInt(size), sort, keyword.trim());
        List<SnippetCardVO> snippetCardVoList = snippetPage.getContent();
        model.addAttribute("keyword", keyword.trim());
        model.addAttribute("sort", sort);
        model.addAttribute("page", snippetPage);
        model.addAttribute("snippetCardVoList", snippetCardVoList);
        return new ModelAndView("snippet/discover", "snippetModel", model);
    }

    /**
     * 获取片段卡片页面
     *
     * @param keyword 关键字
     * @param page    当前页码
     * @param size    每页数据
     * @param model   数据模型
     * @return 模型视图
     */
    @GetMapping("more")
    public ModelAndView getMore(@RequestParam(required = false, defaultValue = "") String keyword
            , @RequestParam(required = false, defaultValue = PaginationConstant.PAGINATION_DEFAULT_PAGE) String page
            , @RequestParam(required = false, defaultValue = PaginationConstant.PAGINATION_DEFAULT_SIZE) String size
            , @RequestParam(required = false, defaultValue = SnippetConstant.SNIPPET_SORT_DEFAULT_CREATED_DESC) String sort
            , Model model) {
        logger.info("获取片段更多页面");
        Page<SnippetCardVO> snippetPage = snippetApplicationService.pageByKeyword(Integer.parseInt(page) - 1, Integer.parseInt(size), sort, keyword);
        List<SnippetCardVO> snippetCardVoList = snippetPage.getContent();
        model.addAttribute("snippetPage", snippetPage);
        model.addAttribute("snippetList", snippetCardVoList);
        return new ModelAndView("snippet/card_list", "snippetModel", model);
    }

    /**
     * 获取片段创建页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getCreate(Model model) {
        logger.info("获取片段创建页面");
        return new ModelAndView("snippet/create", "snippetModel", model);
    }

    /**
     * 获取片段详情页面
     *
     * @param snippetId 片段ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("{snippetId}/detail")
    public ModelAndView getDetail(@PathVariable String snippetId, Model model) {
        logger.info("获取片段详情页面，片段ID{}", snippetId);
        SnippetDetailVO snippetDetailVo = snippetApplicationService.getDetailBySnippetId(snippetId);
        model.addAttribute("snippetDetailVo", snippetDetailVo);
        return new ModelAndView("snippet/detail", "snippetModel", model);
    }

    /**
     * 获取片段内容页面
     *
     * @param snippetId 片段ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("{snippetId}/content")
    public ModelAndView getContent(@PathVariable String snippetId, Model model) {
        logger.info("获取片段内容页面，片段ID{}", snippetId);
        Snippet snippet = snippetApplicationService.getBySnippetId(snippetId);
        model.addAttribute("content", snippet.content());
        return new ModelAndView("snippet/content-self", "snippetModel", model);
    }

    /**
     * 获取片段修改页面
     *
     * @param snippetId 片段ID
     * @param model     模型
     * @return 模型视图
     */
    @GetMapping("{snippetId}/modify")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView getModify(@PathVariable String snippetId, Model model) {
        logger.info("获取片段修改页面，片段ID{}", snippetId);
        model.addAttribute("snippetId", snippetId);
        model.addAttribute("snippetModifyForm", snippetApplicationService.getModifyVoBy(snippetId));
        return new ModelAndView("snippet/modify", "snippetModel", model);
    }

}
