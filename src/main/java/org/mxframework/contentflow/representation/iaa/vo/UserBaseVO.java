package org.mxframework.contentflow.representation.iaa.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.iaa.UserBase;

/**
 * UserBaseVO: 用户基础[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBaseVO extends UserBase {

    public UserBaseVO(UserBase userBase) {
        super(userBase);
    }

    public UserBaseVO() {
    }
}
