package org.mxframework.contentflow.representation.iaa.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * UserCreateForm: 用户创建表单
 *
 * @author mx
 */
@Data
public class UserCreateForm {

    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "用户邮箱格式不正确")
    private String email;

    @NotNull(message = "用户账号不能为空")
    @Size(min = 6, max = 20, message = "用户账号不能超出范围‘6~20’")
    private String username;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 20, message = "用户密码不能超出范围‘6~20’")
    private String password;

    @Size(max = 50, message = "用户网站地址字符串长度不能超过50")
    private String website;

    @Size(max = 300, message = "用户简介字符串大小不能超过300")
    private String note;

    @Size(max = 600, message = "用户公告字符串大小不能超过600")
    private String notice;
}
