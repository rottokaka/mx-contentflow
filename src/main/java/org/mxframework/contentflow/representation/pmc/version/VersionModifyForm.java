package org.mxframework.contentflow.representation.pmc.version;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * VersionModifyForm: 版本修改表单
 *
 * @author mx
 */
@Data
public class VersionModifyForm {

    @NotBlank(message = "版本名称不能为空")
    @Size(min = 2, max = 20, message = "版本名称字符串长度不能超出范围‘2~20’")
    private String name;

    @Size(max = 300, message = "描述字符串长度不能超过‘300’")
    private String description;

    public VersionModifyForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public VersionModifyForm() {}
}
