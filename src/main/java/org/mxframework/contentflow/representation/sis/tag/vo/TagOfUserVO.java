package org.mxframework.contentflow.representation.sis.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mxframework.contentflow.constant.sis.TagConstant;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;

/**
 * TagOfUserVO: 用户标签[VO]
 *
 * @author mx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagOfUserVO {

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
