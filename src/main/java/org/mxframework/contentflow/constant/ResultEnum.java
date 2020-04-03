package org.mxframework.contentflow.constant;

import lombok.Getter;

/**
 * ResultEnum: 结果枚举
 *
 * @author mx
 */
@Getter
public enum ResultEnum {

    /**
     * 未知错误【默认】
     */
    RESULT_FAILURE(-1, "未知错误"),

    /**
     * 操作成功【默认】
     */
    RESULT_SUCCESS(0, "操作成功"),

    /**
     * 版本错误：校验不通过
     */
    RESULT_FAILURE_VERSION_VALIDATION(170, "版本校验问题"),

    /**
     * 版本错误：默认版本不允许删除
     */
    RESULT_VERSION_IS_CUSTOM_NOT(0, "默认版本不允许删除"),
    /**
     * 主题错误：主题不能为空
     */
    RESULT_FAILURE_THEME_NO_NULL(0, "主题不能为空"),

    /**
     * 用户缺失
     */
    VERSION_FAILURE_DELETE_ON_ABSENT(5101, "版本缺失"),

    /**
     * 失败：版本处理失败，删除规则（默认版本不可删除）
     */
    VERSION_FAILURE_DELETE_RULE_ON_NOT_DEFAULT(5102, "默认版本不可删除"),

    /**
     * 阅读失败：阅读缺失，用户未阅读
     */
    READER_FAILURE_ON_ABSENT(6500, "阅读缺失，用户未阅读"),

    /**
     * 投票失败：用户已投票
     */
    VOTE_FAILURE_INSERT_RULE_ON_YET_VOTE(6201, "用户已投票"),


    // 话题层面~
    // =================================================================================================================

    TOPIC_NOT_ALLOW_CONTRIBUTE(3001, "此话题不允许投稿")

    ;

    /**
     * code: 结果编码
     */
    private Integer code;
    /**
     * message: 结果信息
     */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
