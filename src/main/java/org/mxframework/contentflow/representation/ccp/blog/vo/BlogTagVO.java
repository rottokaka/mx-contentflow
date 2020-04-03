package org.mxframework.contentflow.representation.ccp.blog.vo;

import org.mxframework.contentflow.constant.ccp.BlogConstant;

/**
 * BlogTagVO: 博客标签视图对象
 *
 * <p>
 * 作用：主要用于博客查看页面，关于该博客的标签集的展示
 * </p>
 *
 * @author mx
 */
public class BlogTagVO {

    /**
     * id: 标签ID
     */
    private Long id;

    /**
     * name: 标签名称
     */
    private String name;

    /**
     * description: 标签描述
     */
    private String description;

    /**
     * color: 颜色
     * <p>
     * 描述：目前支持两种，默认和自定义[1]。或许还有多种颜色的区别。
     * 0，{@link BlogConstant#BLOG_TAG_VO_COLOR_DEFAULT}，用户未使用此标签标记博客
     * 1，{@link BlogConstant#BLOG_TAG_VO_COLOR_CUSTOM1}，用户已使用此标签标记博客
     * </p>
     */
    private Integer color;

    public BlogTagVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
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
        return "BlogTagVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color=" + color +
                '}';
    }
}
