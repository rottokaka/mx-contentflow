package org.mxframework.contentflow.representation.sis.tag.dto;

import lombok.Data;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.constant.sis.TagConstant;

/**
 * TagBlogDTO: 标签博客[DTO]
 *
 * @author mx
 */
@Data
public class TagBlogDTO {

    /**
     * tag: 标签
     */
    private Tag tag;

    /**
     * labeled: 是否标记
     * <p>当前用户是否使用标签标记博客</p>
     * <p>
     * 0-未标记 {@link TagConstant#TAG_OF_BLOG_IS_LABELED_NOT}
     * 1-已标记 {@link TagConstant#TAG_OF_BLOG_IS_LABELED_YES}
     * </p>
     */
    private Integer labeled;

    /**
     * counter: 计数器
     * <p>用户使用标签的次数</p>
     */
    private Integer counter;
}
