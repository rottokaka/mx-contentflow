package org.mxframework.contentflow.representation.pmc.version;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * VersionCreateForm: 版本创建表单
 *
 * @author mx
 */
@Data
public class VersionCreateForm {

    private String projectId;

    @NotNull
    @Size(min = 2, max = 20, message = "版本名称字符串长度不能超出范围‘2~20’")
    private String name;

    @Size(max = 300, message = "描述字符串长度不能超过‘300’")
    private String description;
}
