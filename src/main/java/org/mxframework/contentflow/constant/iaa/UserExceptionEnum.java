package org.mxframework.contentflow.constant.iaa;

import lombok.Getter;

/**
 * UserExceptionEnum： 用户异常枚举
 *
 * @author mx
 */
@Getter
public enum UserExceptionEnum {

    /**
     * 业务校验：没有权限
     */
    USER_ON_ABSENT("1001", "用户不存在"),

    /**
     * 用户未创建任何博客
     */
    USER_NOT_CREATE_ANY_BLOGS("1101", "用户未创建任何博客"),

    /**
     * 用户未创建任何博客
     */
    USER_NOT_CREATE_ANY_TAGS("1102", "用户未创建任何标签"),

    /**
     * 用户未创建任何项目
     */
    USER_NOT_CREATE_ANY_PROJECTS("1103", "用户未创建任何项目"),

    ;

    /**
     * code: 编码
     */
    private String code;

    /**
     * message: 信息
     */
    private String message;

    UserExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
