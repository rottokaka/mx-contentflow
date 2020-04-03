package org.mxframework.contentflow.representation.ccp.blog.form;

import lombok.Data;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * BlogConfigModifyForm: 博客配置修改表单
 *
 * @author mx
 */
@Data
public class BlogConfigModifyForm {

    private String blogId;
    private String title;

    // 可修改字段~
    // =================================================================================================================

    @NotNull(message = "范围不能为空")
    private Integer scope;

    @NotNull(message = "收录是否允许不能为空")
    private Integer collectionNotAllowed;

    public BlogConfigModifyForm(Blog blog) {
        BeanUtils.copyProperties(blog, this);
    }
}
