package org.mxframework.contentflow.resource.pmc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.pmc.ProjectApplicationService;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectConfigModifyForm;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectCreateForm;
import org.mxframework.contentflow.representation.pmc.project.form.ProjectModifyForm;
import org.mxframework.contentflow.util.ResultUtil;
import org.mxframework.contentflow.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mx
 */
@Api("Project RESTful API")
@RestController
@RequestMapping("projects")
public class ProjectResource {
    private static final Logger logger = LoggerFactory.getLogger(ProjectResource.class);

    @Autowired
    private ProjectApplicationService projectApplicationService;

    /**
     * 列出项目
     *
     * @return 结果[VO]
     */
    @ApiOperation("列出项目")
    @GetMapping
    public ResultVO list() {
        return ResultUtil.success(projectApplicationService.list());
    }

    /**
     * 新建项目
     *
     * @param projectCreateForm 项目插入[VO]
     * @return 结果[VO]
     */
    @ApiOperation("新建项目")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResultVO post(@ApiParam("项目新建表单") @Valid @RequestBody ProjectCreateForm projectCreateForm) {
        return ResultUtil.success(projectApplicationService.post(projectCreateForm));
    }

    /**
     * 获取项目，通过项目ID
     *
     * @param projectId 项目ID
     * @return 结果[VO]
     */
    @ApiOperation("获取项目")
    @GetMapping("{projectId}")
    public ResultVO getByProjectId(@ApiParam("项目ID") @PathVariable String projectId) {
        return ResultUtil.success(projectApplicationService.getByProjectId(projectId));
    }


    /**
     * 更新项目
     *
     * @param projectModifyForm 项目更新视图对象
     * @return 结果[VO]
     */
    @ApiOperation("更新项目")
    @PutMapping("{projectId}")
    public ResultVO putByProjectId(@ApiParam("项目ID") @PathVariable String projectId
            , @ApiParam("项目修改表单") @Valid @RequestBody ProjectModifyForm projectModifyForm) {
        return ResultUtil.success(projectApplicationService.putByProjectId(projectId, projectModifyForm));
    }

    /**
     * 删除项目，通过项目ID
     *
     * @param projectId 项目ID
     * @return 结果[VO]
     */
    @ApiOperation("删除项目")
    @DeleteMapping("{projectId}")
    public ResultVO deleteByProjectId(@ApiParam("项目ID") @PathVariable String projectId) {
        projectApplicationService.deleteByProjectId(projectId);
        return ResultUtil.success();
    }

    /**
     * 更新项目配置
     *
     * @param projectId               项目配置ID
     * @param projectConfigModifyForm 项目配置更新视图对象
     * @return ResultVO 返回结果视图对象
     */
    @ApiOperation("更新项目配置")
    @PatchMapping(value = "{projectId}/config")
    @PreAuthorize("hasRole('USER')")
    public ResultVO patchOnConfig(@ApiParam("项目ID") @PathVariable String projectId
            , @ApiParam("项目配置修改表单") @Valid @RequestBody ProjectConfigModifyForm projectConfigModifyForm) {
        projectApplicationService.patchOnConfig(projectId, projectConfigModifyForm);
        return ResultUtil.success();
    }

    /**
     * 列出项目，用户创建的
     *
     * @param username 帐号
     * @return 结果[VO]
     */
    @ApiOperation("列出项目，用户创建的")
    @GetMapping("created")
    public ResultVO listByUsername(@ApiParam("账号身份") String username) {
        // 获取指定用户的项目，判断当前登录用户是否是指定用户。如果是，显示用户创建的所有；如果否，返回用户创建的公开项目
        if (SecurityUtil.isPrincipal(username)) {
            return ResultUtil.success(projectApplicationService.listItemVoByIdentity());
        } else {
            return ResultUtil.success(projectApplicationService.listItemVoByIdentityPublic(username));
        }
    }

}
