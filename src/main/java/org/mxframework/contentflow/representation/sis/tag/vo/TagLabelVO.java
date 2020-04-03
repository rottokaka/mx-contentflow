package org.mxframework.contentflow.representation.sis.tag.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TagLabelVO: 标签标记视图对象
 *
 * @author mx
 * @date 2019-10-11
 */
@Data
public class TagLabelVO {

    /**
     * purpose: 目标
     */
    @NotNull(message = "操作目标不能为空")
    private String purpose;

    /**
     * tagName: 标签名称
     */
    @NotNull(message = "标签名称不能为空")
    private String tagName;

    /**
     * blogId: 博客ID
     */
    @NotNull(message = "博客ID不能为空")
    private Long blogId;
}
