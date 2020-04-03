package org.mxframework.contentflow.representation.iaa.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.iaa.UserBase;

/**
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCardDTO extends UserBaseDTO {
    private static final long serialVersionUID = 1L;

    public UserCardDTO(UserBase userBase) {
        super(userBase);
    }
}
