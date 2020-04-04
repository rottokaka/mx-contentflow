package org.mxframework.contentflow.representation.pmc.subject.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * SubjectCreateForm: 专题创建表单
 *
 * @author mx
 */
@Data
public class SubjectCreateForm {

    private String versionId;

    @NotNull(message = "名称不能为空")
    @Size(min = 2, max = 20, message = "名称字符长度不能超出范围‘2~20’")
    private String name;

    @Size(max = 300, message = "描述字符串长度不能超过‘300’")
    private String description;

}
