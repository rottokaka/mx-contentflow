package org.mxframework.contentflow.constant.ccp;

/**
 * BlogExceptionEnum: 博客异常枚举
 *
 * @author mx
 */
public enum BlogExceptionEnum {

    // 数据校验~
    // =================================================================================================================

    /**
     * 数据校验：约束违反
     */
    BLOG_CONSTRAINT_VIOLATION("1100", "博客约束违反"),

    /**
     * 数据校验：博客标题不能为空
     */
    BLOG_TITLE_NOT_NULL("1102", "标题不能为空"),

    /**
     * 数据校验：内容不能为空
     */
    BLOG_NOT_NULL_ON_CONTENT("1103", "内容不能为空"),

    /**
     * 数据校验：概要不能为空
     */
    BLOG_NOT_NULL_ON_SUMMARY("1104", "概要不能为空"),

    /**
     * 数据校验：博客概要字符长度不能超过140
     */
    BLOG_SUMMARY_LENGTH("1105", "博客概要字符长度不能超过140"),

    // 业务校验~
    // =================================================================================================================

    /**
     * 博客业务问题
     */
    BLOG_FAILURE_SERVICE("1200", "博客业务问题"),

    /**
     * 业务校验：博客不存在
     */
    BLOG_ON_ABSENT("1201", "博客不存在"),

    /**
     * 博客标题已存在
     */
    BLOG_FAILURE_SERVICE_ON_EXIST_HEAD("1202", "标题已经存在"),

    /**
     * 博客缺失
     */
    BLOG_FAILURE_DELETE_ON_ABSENT("1303", "博客缺失"),

    /**
     * 博客不允许收录
     */
    BLOG_NOT_ALLOW_COLLECT("1304", "博客不允许收录"),
    ;

    /**
     * code: 编码
     */
    private String code;

    /**
     * message: 信息
     */
    private String message;

    BlogExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
