package org.mxframework.contentflow.util;

import org.mxframework.contentflow.constant.DataLayout;

/**
 * @author mx
 */
public class DataLayoutUtil {

    public static DataLayout layout(String layout) {
        if (layout != null && !"".equals(layout)) {
            return DataLayout.valueOf(layout);
        } else {
            return DataLayout.DATA_LAYOUT_DEFAULT_BASE;
        }
    }
}
