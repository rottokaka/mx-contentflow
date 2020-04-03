package org.mxframework.contentflow.constant.sis;

import org.mxframework.contentflow.representation.sis.tag.vo.TagAtProductVO;
import org.mxframework.contentflow.representation.sis.tag.vo.TagDetailVO;
import org.mxframework.contentflow.representation.sis.tag.vo.TagOfUserVO;

/**
 * TagConstant: 用户标签常量
 *
 * @author mx
 */
public class TagConstant {

    /**
     * Tag.property: 0，公共标签
     */
    public static final Integer TAG_PROPERTY_PUBLIC = 0;

    /**
     * Tag.property: 1，个人标签
     */
    public static final Integer TAG_PROPERTY_PRIVATE = 1;

    /**
     * Tag.property: 2，私人标签-标记标签
     */
    public static final Integer TAG_PROPERTY_PRIVATE_MARKUP = 2;

    /**
     * Tag.property: 3，私人标签-管理标签
     */
    public static final Integer TAG_PROPERTY_PRIVATE_MANAGE = 3;

    /**
     * TAGS_SEPARATOR: 标签字符串正则表达式，分隔符
     */
    public static final String TAGS_SEPARATOR = ",";

    /**
     * TAG_DESCRIPTION_SEPARATOR: 标签描述拼接字符串分隔符
     */
    public static final String TAG_DESCRIPTION_SEPARATOR = "|";

    /**
     * TagDetailVO.aim: 0：标记标签，添加该博客
     */
    public static final String TAG_VIEW_PATTERN_INSERT = "PATTERN_INSERT";

    /**
     * TagDetailVO.aim: 1：取消标记，移除该博客
     */
    public static final String TAG_VIEW_PATTERN_REMOVE = "PATTERN_REMOVE";

    /**
     * TagDetailVO.color: 0：默认颜色
     *
     * @see TagDetailVO#color
     */
    public static final Integer TAG_VIEW_COLOR_DEFAULT = 0;

    /**
     * TagDetailVO.color: 1：自定义，样式1，颜色
     *
     * @see TagDetailVO#color
     */
    public static final Integer TAG_VIEW_COLOR_CUSTOM1 = 1;

    /**
     * TagAtProductVO.labeled: 0-未标记
     *
     * @see TagAtProductVO#labeled
     */
    public static final Integer TAG_OF_BLOG_IS_LABELED_NOT = 0;

    /**
     * TagAtProductVO.labeled: 1-已标记
     *
     * @see TagAtProductVO#labeled
     */
    public static final Integer TAG_OF_BLOG_IS_LABELED_YES = 1;

    /**
     * TagOfUserVO.used: 0-未标记
     *
     * @see TagOfUserVO#used
     */
    public static final Integer TAG_OF_USER_IS_USED_NOT = 0;

    /**
     * TagOfUserVO.used: 1-已标记
     *
     * @see TagOfUserVO#used
     */
    public static final Integer TAG_OF_USER_IS_USED_YES = 1;

    /**
     * TagRelevance.contentType: TAG_TYPE_BLOG 阅读博客
     */
    public static final String TAG_TYPE_BLOG = "TAG_BLOG";

    public static final String TAGS_ORDER_BY_DEFAULT = "hot";

    public static final String TAGS_ORDER_BY_HOT = "hot";
    public static final String TAGS_ORDER_BY_NEW = "new";
    public static final String TAGS_ORDER_BY_TRENDING = "trending";
}
