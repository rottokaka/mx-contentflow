package org.mxframework.contentflow.resource.pmc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.pmc.SubjectApplicationService;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.pmc.subject.form.SubjectCreateForm;
import org.mxframework.contentflow.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mx
 */
@Api("Subject RESTful API")
@RestController
@RequestMapping("subjects")
public class SubjectResource {
    private static final Logger logger = LoggerFactory.getLogger(SubjectResource.class);

    @Autowired
    private SubjectApplicationService subjectApplicationService;

    /**
     * 新建专题
     *
     * @param subjectCreateForm 专题创建表单
     * @return 结果[VO]
     */
    @ApiOperation("新建专题")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResultVO post(@ApiParam("专题创建表单") @Valid @RequestBody SubjectCreateForm subjectCreateForm) {
        return ResultUtil.success(subjectApplicationService.post(subjectCreateForm));
    }

    /**
     * 列出专题，通过版本ID
     *
     * @param versionId 版本ID
     * @return 结果[VO]
     */
    @ApiOperation("列出专题，主持的")
    @GetMapping("hosted")
    public ResultVO listByVersionHosted(@ApiParam("版本ID") @RequestParam("versionId") String versionId) {
        return ResultUtil.success(subjectApplicationService.listItemVoByVersionId(versionId));
    }

    /**
     * 专题删除，通过专题ID
     *
     * @return 结果[VO]
     */
    @ApiOperation("删除专题")
    @DeleteMapping("{subjectId}")
    public ResultVO deleteBySubjectId(@ApiParam("专题ID") @PathVariable String subjectId) {
        subjectApplicationService.deleteBySubjectId(subjectId);
        return ResultUtil.success();
    }

}
