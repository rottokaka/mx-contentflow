package org.mxframework.contentflow.constant.pmc;

/**
 * FactorConstant: 因素常量
 *
 * @author m
 * @date 2018-11-29
 */
public class FactorConstant {

    /**
     * factor.axis : version-主题【AXIS_VERSION】
     */
    public static final String FACTOR_AXIS_VERSION = "AXIS_VERSION";

    /**
     * factor.axis : theme-属性【AXIS_THEME】
     */
    public static final String FACTOR_AXIS_THEME = "AXIS_THEME";

    /**
     * factor.axis : contentType-版本【AXIS_TYPE】
     */
    public static final String FACTOR_AXIS_TYPE = "AXIS_TYPE";

    /**
     * factor.name: 默认版本
     */
    public static final String FACTOR_DEFAULT_VERSION_NAME = "default_version";

    /**
     * factor.description: 默认版本
     */
    public static final String FACTOR_DEFAULT_VERSION_DESCRIPTION = "default_version";

    /**
     * factor.id :default
     */
    public static final Long FACTOR_DEFAULT_NOT_NULL = 0L;

    /**
     * factor.isSticky
     * 不置顶；【默认】
     */
    public static final Integer FACTOR_ISSTICKY_NOT = 0;

    /**
     * factor.isSticky
     * 已置顶
     */
    public static final Integer FACTOR_ISSTICKY_YES = 1;
}
