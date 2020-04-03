package org.mxframework.contentflow.representation.pmc.project.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ProjectCreateForm: 项目表单[VO]
 * <p>参考：project/create.html</p>
 *
 * @author mx
 */
@Data
public class ProjectCreateForm {

    @NotNull(message = "项目名称不能为空")
    @Size(min = 2, max = 20, message = "项目名称不能超出范围‘2~20’")
    private String name;

    @Size(max = 1000, message = "项目描述长度不能超过1000")
    private String description;
    
    @Size(max = 100, message = "项目网站地址长度不能超过100")
    private String website;
    
    @NotNull(message = "项目上级项目不能为空")
    private String aboveProjectId;

    @NotNull(message = "项目访问范围不能为空")
    private Integer scope;

    @NotNull(message = "项目投稿是否允许标志位不能为空")
    private Integer contributionNotAllowed;
}
