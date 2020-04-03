package org.mxframework.contentflow.representation.iaa.form;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * UserModifyForm: 用户修改表单
 *
 * @author mx
 */
@Data
public class UserModifyForm {

    private String userId;

    @Size(max = 50, message = "用户网站地址字符串长度不能超过50")
    private String website;

    @Size(max = 300, message = "用户简介字符串大小不能超过300")
    private String note;

    @Size(max = 600, message = "用户公告字符串大小不能超过600")
    private String notice;

}
