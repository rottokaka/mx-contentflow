package org.mxframework.contentflow.representation.iaa;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author mx
 */
@Data
public class UserBase {

    private Date gmtCreate;
    private Date gmtModified;

    private String userId;
    private String email;
    private String username;
    private String nickname;
    private String avatar;
    private String website;
    private String note;
    private String notice;

    public UserBase(UserBase userBase) {
        BeanUtils.copyProperties(userBase, this);
    }

    public UserBase(Date gmtCreate
            , Date gmtModified
            , String userId
            , String email
            , String username
            , String nickname
            , String avatar
            , String website
            , String note
            , String notice) {
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.avatar = avatar;
        this.website = website;
        this.note = note;
        this.notice = notice;
    }

    public UserBase() {
    }
}
