package org.mxframework.contentflow.exception;

import org.mxframework.contentflow.constant.pmc.ProjectExceptionEnum;

/**
 * ProjectNotAllowContributeException: 项目不允许投稿异常
 *
 * @author mx
 */
public class ProjectNotAllowContributeException extends ProjectException {

    public ProjectNotAllowContributeException() {
        super(ProjectExceptionEnum.PROJECT_NOT_ALLOW_CONTRIBUTE.getCode(), ProjectExceptionEnum.PROJECT_NOT_ALLOW_CONTRIBUTE.getMessage());
    }
}
