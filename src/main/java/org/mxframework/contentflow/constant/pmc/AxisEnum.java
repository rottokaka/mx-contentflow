package org.mxframework.contentflow.constant.pmc;

import lombok.Getter;

/**
 * AxisEnum: 坐标枚举
 *
 * @author mx
 */
@Getter
public enum AxisEnum {

    /**
     * 版本名称描述: 版本
     */
    AXIS_VERSION("版本", "版本"),
    /**
     * 版本名称描述: 版本_主题
     */
    AXIS_VERSION_THEME("版本_主题", "版本_主题"),
    /**
     * 版本名称描述: 版本_主题_类型
     */
    AXIS_VERSION_THEME_TYPE("版本_主题_类型", "版本_主题_类型"),
    /**
     * 版本名称描述: 版本_类型
     */
    AXIS_VERSION_TYPE("版本_类型", "版本_类型"),
    ;
    /**
     * name: 名称
     */
    private String name;
    /**
     * description: 描述
     */
    private String description;

    AxisEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
