package org.mxframework.contentflow.handler;

import org.mxframework.contentflow.constant.ResultEnum;
import org.mxframework.contentflow.exception.MxException;
import org.mxframework.contentflow.exception.NotFoundException;
import org.mxframework.contentflow.exception.iaa.UserNotLoginedException;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * MxControllerAdvice: MX控制器通知
 * <p>系统总控制器通知</p>
 *
 * @author mx
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    /**
     * handle {@link Exception}
     *
     * @param e 异常
     * @return 结果[VO]
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleException(Exception e) {
        logger.error("【未知错误】", e);
        return ResultUtil.failure(ResultEnum.RESULT_FAILURE);
    }

    /**
     * handle {@link AccessDeniedException}
     *
     * @param ade 异常
     * @return 结果[VO]
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultVO handleAccessDeniedException(AccessDeniedException ade) {
        return ResultUtil.failure(ResultEnum.RESULT_FAILURE.getCode(), ade.getMessage());
    }

    /**
     * handle {@link MxException}
     *
     * @param mx 异常
     * @return 结果[VO]
     */
    @ExceptionHandler(MxException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handleMxException(MxException mx) {
        return ResultUtil.failure(Integer.getInteger(mx.getCode()), mx.getMessage());
    }

    /**
     * handle {@link MethodArgumentNotValidException}
     * <p>
     * 如果参数添加，bindingResult，则会抛出BindException
     * 如果@Entity实体，校验（@Size），则会抛出ConstraintViolationException
     * </p>
     *
     * @param e 异常
     * @return 结果[VO]
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleBindingResult(e.getBindingResult());
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundException(NotFoundException nfe) {
        return new ModelAndView("/error/404");
    }

    @ExceptionHandler(value = {BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handleBindException(BindException be) {
        return handleBindingResult(be.getBindingResult());
    }

    /**
     * handle {@link ConstraintViolationException}
     *
     * @param cve 异常
     * @return 结果[VO]
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handleConstraintViolationException(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> constraintViolationSet = cve.getConstraintViolations();
        StringBuilder message = new StringBuilder();
        constraintViolationSet.forEach(constraintViolation -> message.append(constraintViolation.getMessage()).append(System.lineSeparator()));
        return ResultUtil.failure(ResultEnum.RESULT_FAILURE.getCode(), message.toString());
    }

    /**
     * handle {@link UserNotLoginedException}
     *
     * @param unle UserNotLoginedException
     * @return 模型视图
     */
    @ExceptionHandler(value = {UserNotLoginedException.class})
    public ResultVO handleUserNotLoginedException(UserNotLoginedException unle) {
        return ResultUtil.failure(ResultEnum.RESULT_FAILURE.getCode(), unle.getMessage());
    }

    /**
     * handle BindingResult
     *
     * @param bindingResult excepetion
     * @return 结果[VO]
     */
    private static ResultVO handleBindingResult(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder message = new StringBuilder();
        fieldErrors.forEach(fieldError -> message.append(fieldError.getDefaultMessage()).append(System.lineSeparator()));
        return ResultUtil.failure(ResultEnum.RESULT_FAILURE.getCode(), message.toString());
    }

}
