package org.mxframework.contentflow.representation.iaa.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.iaa.UserBase;

/**
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCardVO extends UserBaseVO {

    public UserCardVO(UserBase userBase) {
        super(userBase);
    }

    public UserCardVO() {
    }
}
