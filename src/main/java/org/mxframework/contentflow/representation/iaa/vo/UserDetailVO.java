package org.mxframework.contentflow.representation.iaa.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.iaa.UserBase;

/**
 * UserDetailVO: 用户详情[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetailVO extends UserBaseVO {

    private Boolean owned;

    public UserDetailVO(UserBase userBase) {
        super(userBase);
    }

    public UserDetailVO() {
    }
}
