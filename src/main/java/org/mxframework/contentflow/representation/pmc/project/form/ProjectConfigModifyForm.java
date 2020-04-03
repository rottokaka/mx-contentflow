package org.mxframework.contentflow.representation.pmc.project.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ProjectConfigModifyForm: 项目配置修改表单
 *
 * @author mx
 */
@Data
public class ProjectConfigModifyForm {

    private String projectId;

    private String projectName;

    @NotNull(message = "项目访问范围标志位不能为空")
    private Integer scope;

    @NotNull(message = "项目投稿是否允许标志位不能为空")
    private Integer contributionNotAllowed;

}
