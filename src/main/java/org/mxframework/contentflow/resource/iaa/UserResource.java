package org.mxframework.contentflow.resource.iaa;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.iaa.UserApplicationService;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.iaa.form.UserCreateForm;
import org.mxframework.contentflow.representation.iaa.form.UserModifyForm;
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
@RestController
@RequestMapping("users")
public class UserResource {
    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserApplicationService userApplicationService;

    /**
     * 列出用户
     *
     * @return 结果[VO]
     */
    @ApiOperation("列出用户")
    @GetMapping
    public ResultVO list() {
        return ResultUtil.success(userApplicationService.list());
    }

    /**
     * 获取用户
     *
     * @param userId 用户ID
     * @return 结果[VO]
     */
    @ApiOperation("获取用户")
    @GetMapping("{userId}")
    public ResultVO getByUserId(@PathVariable String userId) {
        return ResultUtil.success(userApplicationService.getByUserId(userId));
    }

    /**
     * 新建用户
     *
     * @param userCreateForm 用户创建表单
     * @return 结果[VO]
     */
    @ApiOperation("新建用户")
    @PostMapping
    public ResultVO post(@ApiParam("用户创建表单") @Valid @RequestBody UserCreateForm userCreateForm) {
        return ResultUtil.success(userApplicationService.post(userCreateForm));
    }

    /**
     * 更新用户
     *
     * @param userId         目标用户ID
     * @param userModifyForm 用户修改表单
     * @return 结果[VO]
     */
    @ApiOperation("更新用户")
    @PutMapping("{userId}")
    public ResultVO updateByUserId(@ApiParam("用户ID") @PathVariable String userId
            , @ApiParam("用户修改表单") @Valid @RequestBody UserModifyForm userModifyForm) {
        return ResultUtil.success(userApplicationService.updateByUserId(userId, userModifyForm));
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 结果[VO]
     */
    @ApiOperation("删除用户")
    @DeleteMapping("{userId}")
    @PreAuthorize("hasAnyRole('ROOT','USER')")
    public ResultVO deleteByUseId(@ApiParam("用户ID") @PathVariable String userId) {
        userApplicationService.deleteByUserId(userId);
        return ResultUtil.success();
    }

}
