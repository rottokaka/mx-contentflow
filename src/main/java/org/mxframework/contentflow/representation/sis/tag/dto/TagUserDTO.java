package org.mxframework.contentflow.representation.sis.tag.dto;

import lombok.Data;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.constant.sis.TagConstant;

/**
 * TagOfUserVO: 用户标签视图对象
 *
 * @author mx
 */
@Data
public class TagUserDTO {

    /**
     * tag: 标签
     */
    private Tag tag;

    /**
     * used: 是否使用
     * <p>当前用户是否使用标签标记博客</p>
     * <p>
     * 0-未使用 {@link TagConstant#TAG_OF_USER_IS_USED_NOT}
     * 1-已使用 {@link TagConstant#TAG_OF_USER_IS_USED_YES}
     * </p>
     */
    private Integer used;

    /**
     * counter: 计数器
     * <p>用户使用标签的次数</p>
     */
    private Integer counter;
}
