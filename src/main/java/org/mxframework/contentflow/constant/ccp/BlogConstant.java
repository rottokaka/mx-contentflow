package org.mxframework.contentflow.constant.ccp;

/**
 * BlogConstant: 博客常量
 *
 * @author mx
 */
public class BlogConstant {

    /**
     * blog.contentType-博客的类型：0-系统自建；
     */
    public static final Integer BLOG_PROPERTY_ON_AUTO = 0;

    /**
     * blog.contentType-博客的类型：1-用户创建
     */
    public static final Integer BLOG_PROPERTY_BY_USER = 1;

    /**
     * blog.aboveId: 0L【默认】
     */
    public static final String BLOG_ABOVEID_DEFAULT = "ccp-blog-00000000";

    /**
     * blog.archived: 0-博客未归档标志位【默认】
     */
    public static final Integer BLOG_ARCHIVED_NOT_DEFAULT = 0;

    /**
     * blog.archived: 增加归档次数
     */
    public static final Integer BLOG_ARCHIVED_FACTOR_PLUS_AUGEND = 1;

    /**
     * blog.archived: 减去归档次数
     */
    public static final Integer BLOG_ARCHIVED_FACTOR_MINUS_MINUEND = -1;

    /**
     * blog.public: 0-公开的
     */
    public static final Integer BLOG_SCOPE_PUBLIC_DEFAULT = 0;

    /**
     * blog.scope: 1-内部的
     */
    public static final Integer BLOG_SCOPE_INTERNAL = 1;

    /**
     * blog.scope: 2-秘密的
     */
    public static final Integer BLOG_SCOPE_SECRET = 2;

    /**
     * blog.scope: 3-私人的
     */
    public static final Integer BLOG_SCOPE_PRIVATE = 3;

    /**
     * blog.hidden: 0-博客是否公开【默认】
     */
    public static final Integer BLOG_HIDDEN_NOT = 0;

    /**
     * blog.hidden: 1-博客是否公开
     */
    public static final Integer BLOG_HIDDEN_YES = 1;

    /**
     * BlogConfig.notAllowCollect: 0-博客已允许被收录【默认】
     */
    public static final Integer BLOG_NOT_ALLOW_COLLECT_DEFAULT_FALSE = 0;

    /**
     * BlogConfig.notAllowCollect: 1-博客不允许被收录
     */
    public static final Integer BLOG_NOT_ALLOW_COLLECT_TRUE = 1;

    /**
     * blog.content-博客内容展示长度
     */
    public static final Integer BLOG_CONTENT_DISPLAY_LENGTH = 140;

    public static final String BLOG_PATTERN_H2_TITLE = ".*\\n\\s*(?--+)";

    /**
     * Markdown 标题一 样式[1]
     * ex: =======\n
     */
    public static final String BLOG_PATTERN_H1_STYLE_1 = ".*\\n\\s*(?====+)";

    /**
     * Markdown 标题一 样式[2]
     * ex: # 标题一\n
     */
    public static final String BLOG_PATTERN_H1_STYLE_2 = "(?<=\\r|\\n|\\r\\n)#{1}\\s.*";

    /**
     * Markdown 标题二 样式[1]
     * ex: -------\n
     */
    public static final String BLOG_PATTERN_H2_STYLE_1 = ".*\\n\\s*(?=---+)";

    /**
     * Markdown 标题二 样式[2]
     * ex: ## 标题二\n
     */
    public static final String BLOG_PATTERN_H2_STYLE_2 = "(?<=\\r|\\n|\\r\\n)#{2}\\s.*";

    /**
     * Markdown 标题三
     * ex: ## 标题三\n
     */
    public static final String BLOG_PATTERN_H3 = "(?<=\\r|\\n|\\r\\n)#{3}\\s.*";

    /**
     * Markdown文档的概要匹配
     * ex: > summary \n
     */
    public static final String BLOG_PATTERN_SUMMARY = "(?<=>).*";

    /**
     * 更新博客，涉及坐标，模式为添加
     */
    public static final String BLOG_AXIS_PATTERN_INSERT = "AXIS_INSERT";

    /**
     * 更新博客，涉及坐标，模式为移除
     */
    public static final String BLOG_AXIS_PATTERN_REMOVE = "AXIS_REMOVE";

    /**
     * 更新博客，涉及坐标，模式为删除
     */
    public static final String BLOG_AXIS_PATTERN_DELETE = "AXIS_DELETE";

    /**
     * blog.summary: 默认长度不超过140
     */
    public static final Integer BLOG_SUMMARY_LENGTH = 140;

    /**
     * blog.content: part(------)
     */
    public static final String BLOG_CONTENT_PART = "\n-------\n";

    /**
     * blog.content: \n
     */
    public static final String BLOG_CONTENT_N = "\n";

    /**
     * blog.content: h2
     */
    public static final String BLOG_CONTENT_H2 = "## H2 \n";

    /**
     * blog.content: h3
     */
    public static final String BLOG_CONTENT_H3 = "## H3 \n";

    /**
     * blog.content: h4
     */
    public static final String BLOG_CONTENT_H4 = "## H4 \n";

    /**
     * TagDetailVO.color: 0：默认颜色
     *
     * @see BlogTagVo#color
     */
    public static final Integer BLOG_TAG_VO_COLOR_DEFAULT = 0;

    /**
     * TagDetailVO.color: 1：自定义，样式1，颜色
     *
     * @see BlogTagVo#color
     */
    public static final Integer BLOG_TAG_VO_COLOR_CUSTOM1 = 1;

    /**
     * 博客TOP
     */
    public static final Integer BLOG_TOP_10 = 10;
    public static final String BLOG_SORT_DEFAULT_CREATED_DESC = "CREATED_RECENTLY";
    public static final String BLOG_SORT_UPDATED_DESC = "UPDATED_RECENTLY";
}
