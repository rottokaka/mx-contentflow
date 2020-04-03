package org.mxframework.contentflow.constant.pmc;

import lombok.Getter;

/**
 * TopicExceptionEnum: 项目异常枚举
 *
 * @author mx
 */
@Getter
public enum ProjectExceptionEnum {

    // 数据校验~
    // =================================================================================================================

    /**
     * 数据校验：约束违反
     */
    PROJECT_CONSTRAINT_VIOLATION("3100", "项目约束违反"),
    PROJECT_CONSTRAINT_VIOLATION_PROJECTID_NOT_NULL("3101", "项目ID不能为空"),
    PROJECT_CONSTRAINT_VIOLATION_CREATOR_NOT_NULL("3102", "项目创建者不能为空"),
    PROJECT_CONSTRAINT_VIOLATION_NAME_NOT_NULL("3103", "项目名称不能为空"),

    /**
     * 业务校验：项目不允许投稿
     */
    PROJECT_NOT_ALLOW_CONTRIBUTE("3201", "项目不允许投稿"),

    /**
     * 业务校验：项目不允许访问
     */
    PROJECT_NOT_ALLOW_ACCESS("3202", "项目不允许访问"),
    ;


    /**
     * code: 编码
     */
    private String code;

    /**
     * message: 信息
     */
    private String message;

    ProjectExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
