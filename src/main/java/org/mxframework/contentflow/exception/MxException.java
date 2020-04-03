package org.mxframework.contentflow.exception;

import org.mxframework.contentflow.constant.MxExceptionEnum;

/**
 * MxException: MX异常
 *
 * @author mx
 */
public class MxException extends RuntimeException {

    /**
     * code: 异常编号
     */
    private String code;

    private MxExceptionEnum mxExceptionEnum;

    public MxException() {
    }

    public MxException(MxExceptionEnum mxExceptionEnum) {
        super(mxExceptionEnum.getMessage());
        this.code = mxExceptionEnum.getCode();
    }

    public MxException(String message) {
        super(message);
    }

    public MxException(String message, Throwable cause) {
        super(message, cause);
    }

    public MxException(Throwable cause) {
        super(cause);
    }

    public MxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MxException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MxExceptionEnum getMxExceptionEnum() {
        return mxExceptionEnum;
    }

    public void setMxExceptionEnum(MxExceptionEnum mxExceptionEnum) {
        this.mxExceptionEnum = mxExceptionEnum;
    }
}
