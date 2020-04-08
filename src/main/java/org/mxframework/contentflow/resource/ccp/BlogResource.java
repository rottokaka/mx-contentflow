package org.mxframework.contentflow.resource.ccp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.ccp.BlogApplicationService;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogId;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogConfigModifyForm;
import org.mxframework.contentflow.util.ResultUtil;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogCreateForm;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogModifyForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mx
 */
@Api("Blog RESTful API")
@RestController
@RequestMapping("blogs")
public class BlogResource {
    private static final Logger logger = LoggerFactory.getLogger(BlogResource.class);

    @Autowired
    private BlogApplicationService blogApplicationService;

    /**
     * 列出博客
     *
     * @return 返回结果[VO]
     */
    @ApiOperation("列出博客")
    @GetMapping
    public ResultVO list(@ApiParam("数据布局") String layout) {
        return ResultUtil.success(blogApplicationService.listVo(layout));
    }

    /**
     * 新建博客
     *
     * @param blogCreateForm 博客插入表单[VO]
     * @return 结果[VO]
     */
    @ApiOperation("新建博客")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResultVO post(@ApiParam("博客新建表单") @Valid @RequestBody BlogCreateForm blogCreateForm) {
        return ResultUtil.success(blogApplicationService.post(blogCreateForm));
    }

    /**
     * 查找博客，通过博客ID
     *
     * @param blogId 博客ID
     * @return 结果[VO]
     */
    @ApiOperation("查找博客")
    @GetMapping("{blogId}")
    public ResultVO getByBlogId(@ApiParam("博客ID") @PathVariable String blogId, @ApiParam("数据布局") String layout) {
        return ResultUtil.success(blogApplicationService.getBaseByBlogId(blogId, layout));
    }

    /**
     * 更新博客，通过博客ID
     *
     * @param blogId         博客ID
     * @param blogModifyForm 博客更新[VO]
     * @return 结果[VO]
     */
    @ApiOperation("更新博客")
    @PutMapping("{blogId}")
    @PreAuthorize("hasRole('USER')")
    public ResultVO putByBlogId(@ApiParam("博客ID") @PathVariable String blogId
            , @ApiParam("博客修改表单") @Valid @RequestBody BlogModifyForm blogModifyForm) {
        return ResultUtil.success(blogApplicationService.putBlogByBlogId(blogId, blogModifyForm));
    }

    /**
     * 更新博客，通过博客ID
     * <p>博客配置</p>
     *
     * @param blogId         博客ID
     * @param configUpdateVo 博客配置更新[VO]
     * @return 结果[VO]
     */
    @ApiOperation("更新博客配置")
    @PatchMapping("{blogId}/config")
    @PreAuthorize("hasRole('USER')")
    public ResultVO patchConfigByBlogId(@ApiParam("博客ID") @PathVariable String blogId
            , @ApiParam("博客配置修改表单") @Valid @RequestBody BlogConfigModifyForm configUpdateVo) {
        blogApplicationService.patchConfig(new BlogId(blogId), configUpdateVo);
        return ResultUtil.success();
    }

    /**
     * 删除博客，通过博客ID
     *
     * @param blogId 博客ID
     * @return 结果[VO]
     */
    @ApiOperation("删除博客")
    @DeleteMapping("{blogId}")
    @PreAuthorize("hasRole('USER')")
    @Transactional(rollbackFor = {Exception.class})
    public ResultVO deleteByBlogId(@ApiParam("博客ID") @PathVariable String blogId) {
        blogApplicationService.deleteById(blogId);
        return ResultUtil.success();
    }

}
