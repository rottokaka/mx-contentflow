package org.mxframework.contentflow.constant.sis;

import lombok.Getter;

/**
 * TagExceptionEnum: 标签异常枚举
 *
 * @author mx
 */
@Getter
public enum TagExceptionEnum {

    // 数据校验~
    // =================================================================================================================

    /**
     * 数据校验：约束违反
     */
    TAG_CONSTRAINT_VIOLATION("2100", "标签约束违反"),

    /**
     * 业务校验：话题不允许投稿
     */
    TAG_EXIST("2201", "标签已存在");


    /**
     * code: 编码
     */
    private String code;

    /**
     * message: 信息
     */
    private String message;

    TagExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
