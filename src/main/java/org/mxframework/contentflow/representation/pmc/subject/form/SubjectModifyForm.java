package org.mxframework.contentflow.representation.pmc.subject.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * SubjectModifyForm: 专题修改表单
 *
 * @author mx
 */
@Data
public class SubjectModifyForm {

    @NotBlank(message = "名称不能为空")
    @Size(min = 2, max = 20, message = "名称字符长度不能超出范围‘2~20’")
    private String name;

    @Size(max = 300, message = "描述字符串长度不能超过‘300’")
    private String description;

    public SubjectModifyForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SubjectModifyForm() {}
}
