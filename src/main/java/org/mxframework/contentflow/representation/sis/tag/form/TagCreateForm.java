package org.mxframework.contentflow.representation.sis.tag.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * TagCreateForm: 标签创建表单
 *
 * @author mx
 */
@Data
public class TagCreateForm {

    @NotBlank(message = "标签名称不能为空")
    @Size(min = 2, max = 20, message = "标签名称字符长度不能超出范围'2~20'")
    private String name;

    @Size(max = 300, message = "标签描述字符串长度不能超出300")
    private String description;

    private Integer property;
}
