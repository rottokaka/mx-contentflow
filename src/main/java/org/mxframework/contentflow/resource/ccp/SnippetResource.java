package org.mxframework.contentflow.resource.ccp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.ccp.SnippetApplicationService;
import org.mxframework.contentflow.util.ResultUtil;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetCreateForm;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetModifyForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mx
 */
@Api("Snippet RESTful API")
@RestController
@RequestMapping("snippets")
public class SnippetResource {
    private static final Logger logger = LoggerFactory.getLogger(SnippetResource.class);

    @Autowired
    private SnippetApplicationService snippetApplicationService;

    /**
     * 列出片段
     *
     * @return 结果[VO]
     */
    @ApiOperation("列出片段")
    @GetMapping
    public ResultVO list() {
        return ResultUtil.success(snippetApplicationService.listPublicSnippets());
    }

    /**
     * 新建片段
     *
     * @param snippetCreateForm 片段创建表单
     * @return 结果[VO]
     */
    @ApiOperation("新建片段")
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResultVO post(@ApiParam("片段创建表单") @Valid @RequestBody SnippetCreateForm snippetCreateForm) {
        return ResultUtil.success(snippetApplicationService.post(snippetCreateForm));
    }

    /**
     * 获取片段
     *
     * @param snippetId 片段ID
     * @return 结果[VO]
     */
    @ApiOperation("获取片段")
    @GetMapping("{snippetId}")
    public ResultVO getBySnippetId(@ApiParam("片段ID") @PathVariable String snippetId) {
        return ResultUtil.success(snippetApplicationService.getBySnippetId(snippetId));
    }

    /**
     * 更新片段
     *
     * @param snippetId         片段ID
     * @param snippetModifyForm 片段修改表单
     * @return 结果[VO]
     */
    @ApiOperation("更新片段")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("{snippetId}")
    public ResultVO putBySnippetId(@ApiParam("片段ID") @PathVariable String snippetId
            , @ApiParam("片段修改表单") @Valid @RequestBody SnippetModifyForm snippetModifyForm) {
        return ResultUtil.success(snippetApplicationService.updateBySnippetId(snippetId, snippetModifyForm));
    }

    /**
     * 删除片段
     *
     * @param snippetId 片段ID
     * @return 结果[VO]
     */
    @ApiOperation("删除片段")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{snippetId}")
    public ResultVO deleteBySnippetId(@ApiParam("片段ID") @PathVariable String snippetId) {
        snippetApplicationService.deleteBySnippetId(snippetId);
        return ResultUtil.success();
    }
}
