package org.mxframework.contentflow.util;

import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.constant.ResultEnum;

/**
 * ResultUtil: 结果工具
 *
 * @author mx
 * @date 2018-11-28
 */
public class ResultUtil {

    /**
     * 处理成功
     *
     * @param object 对象
     * @return ResultVO
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setValid(true);
        resultVO.setCode(ResultEnum.RESULT_SUCCESS.getCode());
        resultVO.setMessage(ResultEnum.RESULT_SUCCESS.getMessage());
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 处理成功
     *
     * @return ResultVO
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 处理失败
     *
     * @param code    编码
     * @param message 信息
     * @return ResultVO
     */
    public static ResultVO failure(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setValid(false);
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

    /**
     * 处理失败
     *
     * @param resultEnum 结果枚举
     * @return ResultVO
     */
    public static ResultVO failure(ResultEnum resultEnum) {
        return failure(resultEnum.getCode(), resultEnum.getMessage());
    }
}
