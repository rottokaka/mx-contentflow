package org.mxframework.contentflow.resource.pmc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.pmc.VersionApplicationService;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.pmc.version.VersionCreateForm;
import org.mxframework.contentflow.representation.pmc.version.VersionModifyForm;
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
@Api("Version RESTful API")
@RestController
@RequestMapping("versions")
public class VersionResource {
    private static final Logger logger = LoggerFactory.getLogger(VersionResource.class);

    @Autowired
    private VersionApplicationService versionApplicationService;

    /**
     * 新建版本
     *
     * @param versionCreateForm 版本表单[VO]
     * @return 结果[VO]
     */
    @ApiOperation("新建版本")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResultVO post(@ApiParam("版本创建表单") @Valid @RequestBody VersionCreateForm versionCreateForm) {
        return ResultUtil.success(versionApplicationService.post(versionCreateForm));
    }

    /**
     * 获取版本，通过版本ID
     *
     * @param versionId 版本ID
     * @return 结果[VO]
     */
    @ApiOperation("获取版本")
    @GetMapping("{versionId}")
    public ResultVO getByVersionId(@ApiParam("版本ID") @PathVariable String versionId) {
        return ResultUtil.success(versionApplicationService.getByVersionId(versionId));
    }

    /**
     * 更新版本，通过版本ID
     *
     * @param versionId         版本ID
     * @param versionModifyForm 版本
     * @return 结果[VO]
     */
    @ApiOperation("更新版本")
    @PutMapping("{versionId}")
    @PreAuthorize("hasRole('USER')")
    public ResultVO updateById(@ApiParam("版本ID") @PathVariable String versionId
            , @ApiParam("版本修改表单") @Valid @RequestBody VersionModifyForm versionModifyForm) {
        return ResultUtil.success(versionApplicationService.putByVersionId(new VersionId(versionId), versionModifyForm));
    }

    /**
     * 删除版本，通过版本ID
     *
     * @param versionId 版本ID
     * @return 结果[VO]
     */
    @ApiOperation("删除版本")
    @DeleteMapping("{versionId}")
    @PreAuthorize("hasRole('USER')")
    public ResultVO deleteById(@ApiParam("版本ID") @PathVariable String versionId) {
        versionApplicationService.deleteByVersionId(versionId);
        return ResultUtil.success();
    }

    /**
     * 列出版本，通过项目ID
     *
     * @param projectId 项目ID
     * @return 版本管理[VO]集合
     */
    @ApiOperation("列出版本，主持的")
    @GetMapping("hosted")
    public ResultVO listByProjectIdHosted(@ApiParam("项目ID") @RequestParam("projectId") String projectId) {
        return ResultUtil.success(versionApplicationService.listItemVoByProjectId(projectId));
    }
}
