package org.mxframework.contentflow.controller.sis;

import org.mxframework.contentflow.domain.model.ccp.product.ProductType;
import org.mxframework.contentflow.domain.model.sis.comment.Comment;
import org.mxframework.contentflow.domain.model.sis.identity.Commenter;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.service.sis.CommentService;
import org.mxframework.contentflow.util.ResultUtil;
import org.mxframework.contentflow.representation.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * CommentController: 评论控制器
 *
 * @author mx
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
    public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 保存评论
     *
     * @param comment 评论
     * @return ResponseEntity<ResultVO>
     */
    @PostMapping
    public ResultVO insert(@Valid @RequestBody Comment comment) {
        commentService.add(comment);
        return ResultUtil.success();
    }


    // ~ 获取页面区
    // =================================================================================================================

    /**
     * 查找所有评论
     *
     * @param blogId   博客ID
     * @param username 用户账号
     * @param model    数据模型
     * @return ModelAndView
     */
    @GetMapping
    public ModelAndView selectAllOnCondition(@RequestParam(value = "blogId", required = false, defaultValue = "") String blogId
            , @RequestParam(value = "username", required = false, defaultValue = "") String username, Model model) {
        Set<Comment> commentSet;
        // 查询博客收到的评论
        if (!"".equals(blogId)) {
            commentSet = new HashSet<>(commentService.listAllByProduction(new Product(blogId, ProductType.PRODUCT_BLOG.toString().toUpperCase())));
        }
        // 查询用户发布的评论
        else if (!"".equals(username)) {
            commentSet = new HashSet<>(commentService.listAllByCommenter(new Commenter(username)));
        } else {
            commentSet = null;
        }
        model.addAttribute("commentSet", commentSet);
        return new ModelAndView("comment/list", "commentModel", model);
    }

    /**
     * 获取评论的新建页面
     *
     * @param model 数据模型
     * @return ModelAndView
     */
    @GetMapping("/create")
    public ModelAndView create(Model model) {
        Comment comment = null;
        model.addAttribute("comment", comment);
        return new ModelAndView("comment/create", "commentModel", model);
    }
}
