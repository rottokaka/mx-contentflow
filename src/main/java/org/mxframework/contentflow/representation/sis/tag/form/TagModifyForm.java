package org.mxframework.contentflow.representation.sis.tag.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TagCreateForm: 标签修改表单
 *
 * @author mx
 */
@Data
public class TagModifyForm {

    @NotNull(message = "标签名称不能为空")
    @Size(min = 2, max = 20, message = "标签名称字符串长度不能超出范围'2~20'")
    private String name;

    @Size(max = 300, message = "标签描述字符串长度不能超出300")
    private String description;
}
