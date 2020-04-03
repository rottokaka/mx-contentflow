package org.mxframework.contentflow.exception;

import org.mxframework.contentflow.constant.ccp.BlogExceptionEnum;
import org.mxframework.contentflow.controller.pmc.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BlogException: 自定义博客异常
 * <p>
 * spring 只会对RuntimeException进行事物会滚
 *</p>
 * @author mx
 */
public class BlogException extends MxException {
    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private BlogExceptionEnum blogExceptionEnum;

    public BlogException(BlogExceptionEnum blogExceptionEnum) {
        super(blogExceptionEnum.getCode(), blogExceptionEnum.getMessage());
    }

    public BlogException() {
        super();
    }

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogException(Throwable cause) {
        super(cause);
    }

    protected BlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BlogException(String code, String message) {
        super(code, message);
    }

    public BlogExceptionEnum getBlogExceptionEnum() {
        return blogExceptionEnum;
    }

    public void setBlogExceptionEnum(BlogExceptionEnum blogExceptionEnum) {
        this.blogExceptionEnum = blogExceptionEnum;
    }
}
