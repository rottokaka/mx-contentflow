package org.mxframework.contentflow.exception;

import org.mxframework.contentflow.constant.iaa.UserExceptionEnum;

/**
 * UserException: 用户异常
 *
 * @author mx
 */
public class UserException extends MxException {

    private UserExceptionEnum userExceptionEnum;

    public UserException(UserExceptionEnum userExceptionEnum) {
        super(userExceptionEnum.getCode(), userExceptionEnum.getMessage());
    }
    public UserExceptionEnum getUserExceptionEnum() {
        return userExceptionEnum;
    }

    public void setUserExceptionEnum(UserExceptionEnum userExceptionEnum) {
        this.userExceptionEnum = userExceptionEnum;
    }
}
