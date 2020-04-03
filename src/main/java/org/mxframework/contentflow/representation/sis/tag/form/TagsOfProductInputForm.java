package org.mxframework.contentflow.representation.sis.tag.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TagsOfProductInputForm: 产品标签输入表单
 *
 * @author mx
 */
@Data
public class TagsOfProductInputForm {

    @NotNull(message = "标签名称不能为空")
    private String tags;

    @NotNull(message = "产品ID不能为空")
    private String productId;

    @NotNull(message = "产品类型不能为空")
    private String productType;
}
