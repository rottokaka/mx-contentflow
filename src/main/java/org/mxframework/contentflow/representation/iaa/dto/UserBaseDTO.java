package org.mxframework.contentflow.representation.iaa.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.iaa.UserBase;

/**
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBaseDTO extends UserBase {
    private static final long serialVersionUID = 1L;

    public UserBaseDTO(UserBase userBase) {
        super(userBase);
    }
}
