package org.mxframework.contentflow.constant.iaa;

import lombok.Getter;

/**
 * UserEnum: 用户枚举
 *
 * @author mx
 */
@Getter
public enum UserEnum {
    /**
     * 用户的权限-ROOT
     */
    USER_AUTHORITY_ROOT(1L, "ROOT"),
    /**
     * 用户的权限-USER
     */
    USER_AUTHORITY_USER(2L, "USER"),

    ;

    private Long id;
    private String authority;

    /**
     * 用户枚举
     *
     * @param id        ID
     * @param authority 权限
     */
    UserEnum(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }
}
