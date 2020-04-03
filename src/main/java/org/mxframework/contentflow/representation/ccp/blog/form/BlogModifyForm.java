package org.mxframework.contentflow.representation.ccp.blog.form;

import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * BlogModifyForm: 博客修改表单
 *
 * @author mx
 */
public class BlogModifyForm {

    /**
     * id: ID
     */
    private Long id;

    /**
     * title: 标题
     */
    private String title;

    /**
     * content: 内容
     */
    @NotNull(message = "博客内容不能为空")
    private String content;

    /**
     * contentHtml: 内容HTML格式
     */
    private String contentHtml;

    public BlogModifyForm() {
    }

    public BlogModifyForm(Blog blog) {
        BeanUtils.copyProperties(blog, this);
    }

    public BlogModifyForm(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
