package org.mxframework.contentflow.representation.ccp.snippet.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * SnippetCreateForm: 片段修改[VO]
 *
 * @author mx
 */
@Data
@NoArgsConstructor
public class SnippetModifyForm {

    @NotNull(message = "标题不能为空")
    @Size(min = 2, max = 200, message = "标题字符串长度不能超出范围‘2~20’")
    private String title;

    @NotNull(message = "内容不能为空")
    @Size(min = 2, max = 10000, message = "内容不能超出范围‘2~10000’")
    private String content;

    private String contentHtml;

    @Size(max = 300, message = "描述字符串长度不能超过‘300’")
    private String description;

    @NotNull(message = "片段访问范围不能为空")
    private Integer scope;
}
