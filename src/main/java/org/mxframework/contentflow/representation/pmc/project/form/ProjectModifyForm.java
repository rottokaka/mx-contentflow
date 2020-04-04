package org.mxframework.contentflow.representation.pmc.project.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ProjectModifyForm: 项目修改表单
 *
 * @author mx
 */
@Data
public class ProjectModifyForm {

    @NotNull(message = "项目名称不能为空")
    @Size(min = 2, max = 20, message = "项目名称字符串不能超出范围‘2~20’")
    private String name;

    @Size(max = 1000, message = "项目描述字符串长度不能超过1000")
    private String description;

    @Size(max = 100, message = "项目网站地址长度不能超过100")
    private String website;

    /**
     * 默认为“0”
     */
    @NotNull(message = "项目上级项目不能为空")
    private String aboveProjectId;

}
