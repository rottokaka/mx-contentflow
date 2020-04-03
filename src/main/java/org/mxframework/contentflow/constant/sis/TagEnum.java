package org.mxframework.contentflow.constant.sis;

import lombok.Getter;

/**
 * TagEnum: 标签枚举
 *
 * @author mx
 */
@Getter
public enum TagEnum {
    /**
     * Tag.form 1: 自建标签
     */
    TAG_FORM_CREATE(0, "自建标签"),
    /**
     * Tag.form 2: 管理标签
     */
    TAG_FORM_MANAGE(1, "管理标签"),
    /**
     * Tag.form 1: 标记标签，通过标记，复制别人标签
     */
    TAG_FORM_MARKUP(2, "标记标签"),

    ;

    private Integer form;
    private String mean;

    /**
     * 用户枚举
     *
     * @param form 形式编码
     * @param mean 具体意义
     */
    TagEnum(Integer form, String mean) {
        this.form = form;
        this.mean = mean;
    }
}
