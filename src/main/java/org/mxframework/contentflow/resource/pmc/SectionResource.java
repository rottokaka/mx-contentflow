package org.mxframework.contentflow.resource.pmc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.pmc.SectionApplicationService;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.pmc.section.form.SectionCreateForm;
import org.mxframework.contentflow.representation.pmc.section.form.SectionModifyForm;
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
@Api("Section RESTful API")
@RestController
@RequestMapping("sections")
public class SectionResource {
    private static final Logger logger = LoggerFactory.getLogger(SectionResource.class);

    @Autowired
    private SectionApplicationService sectionApplicationService;

    /**
     * 新建类型
     *
     * @param sectionCreateForm 类型创建表单
     * @return 结果[VO]
     */
    @ApiOperation("新建类型")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResultVO post(@ApiParam("类型创建表单") @Valid @RequestBody SectionCreateForm sectionCreateForm) {
        return ResultUtil.success(sectionApplicationService.post(sectionCreateForm));
    }

    /**
     * 更新类型
     *
     * @param sectionId         类型ID
     * @param sectionModifyForm 类型修改表单
     * @return 结果[VO]
     */
    @ApiOperation("更新类型")
    @PutMapping("{sectionId}")
    @PreAuthorize("hasRole('USER')")
    public ResultVO putBySectionId(@ApiParam("类型ID") @PathVariable String sectionId
            , @ApiParam("类型修改表单") @Valid @RequestBody SectionModifyForm sectionModifyForm) {
        return ResultUtil.success(sectionApplicationService.putBySectionId(sectionId, sectionModifyForm));
    }

    /**
     * 删除类型
     *
     * @param sectionId 类型ID
     * @return 结果[VO]
     */
    @ApiOperation("删除类型")
    @DeleteMapping("{sectionId}")
    public ResultVO deleteBySectionId(@ApiParam("类型ID") @PathVariable String sectionId) {
        sectionApplicationService.deleteBySectionId(sectionId);
        return ResultUtil.success();
    }

    /**
     * 列出类型，通过版本ID
     *
     * @param versionId 版本ID
     * @return 结果[VO]
     */
    @ApiOperation("列出类型，主持的")
    @GetMapping("hosted")
    public ResultVO listByVersionHosted(@ApiParam("版本ID") @RequestParam("versionId") String versionId) {
        return ResultUtil.success(sectionApplicationService.listItemVoByVersionId(versionId));
    }

}
