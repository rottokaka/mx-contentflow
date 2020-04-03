package org.mxframework.contentflow.representation.ccp.blog.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * BlogCreateForm: 博客创建表单
 *
 * @author mx
 */
@Data
public class BlogCreateForm {

    private String title;
    private String summary;
    private String content;
    private String contentHtml;
    private String tags;
    @NotNull(message = "博客访问范围不能为空")
    private Integer scope;
    @NotNull(message = "博客是否不允许收录标志位不能为空")
    private Integer collectionNotAllowed;
}
