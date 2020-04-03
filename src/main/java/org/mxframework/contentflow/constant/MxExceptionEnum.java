package org.mxframework.contentflow.constant;

import lombok.Getter;

/**
 * MxExceptionEnum: MX异常枚举
 *
 * @author mx
 */
@Getter
public enum MxExceptionEnum {

    /**
     * 数据校验：约束违反
     */
    MX_CONSTRAINT_VIOLATION("0000", "约束违反"),

    /**
     * 未登录任何用户
     */
    MX_USER_NOT_LOGIN("0001", "未登录任何用户"),

    /**
     * 业务校验：没有权限
     */
    MX_USER_NO_AUTHORITY("0002", "没有权限");

    /**
     * code: 编码
     */
    private String code;

    /**
     * message: 信息
     */
    private String message;

    MxExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
