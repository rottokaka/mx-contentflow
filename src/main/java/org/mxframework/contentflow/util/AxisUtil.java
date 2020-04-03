package org.mxframework.contentflow.util;

import org.mxframework.contentflow.constant.pmc.AxisConstant;

/**
 * AxisUtil: 坐标工具类
 *
 * @author mx
 * @date 2019-03-24
 */
public class AxisUtil {

    /**
     * 字符串转换为长整形（Long）
     *
     * @param id ID字符串
     * @return 如果传参为空，则返回其值，否则返回默认值
     */
    public static String nullReturnDefault(String id) {
        if (id != null && !"".equals(id)) {
            return id;
        } else {
            return AxisConstant.AXIS_DEFAULT_ID;
        }
    }
}
