package org.mxframework.contentflow.exception;

import org.mxframework.contentflow.constant.sis.TagExceptionEnum;
import org.mxframework.contentflow.controller.pmc.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TagException: 标签异常
 *
 * @author mx
 */
public class TagException extends MxException {
    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private TagExceptionEnum tagExceptionEnum;

    public TagException(TagExceptionEnum tagExceptionEnum) {
        super(tagExceptionEnum.getCode(), tagExceptionEnum.getMessage());
    }

    public TagException(String message) {
        super(message);
    }

    public TagExceptionEnum getTagExceptionEnum() {
        return tagExceptionEnum;
    }

    public void setTagExceptionEnum(TagExceptionEnum tagExceptionEnum) {
        this.tagExceptionEnum = tagExceptionEnum;
    }
}
