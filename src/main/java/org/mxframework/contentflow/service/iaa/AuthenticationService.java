package org.mxframework.contentflow.service.iaa;

import org.mxframework.contentflow.constant.iaa.UserExceptionEnum;
import org.mxframework.contentflow.domain.model.iaa.User;
import org.mxframework.contentflow.exception.UserException;
import org.mxframework.contentflow.util.SecurityUtil;
import org.springframework.stereotype.Service;

/**
 * @author mx
 */
@Service
public class AuthenticationService {

    public String identity() {
        User user = SecurityUtil.getPrincipal();
        if (user != null) {
            return user.username();
        } else {
            throw new UserException(UserExceptionEnum.USER_ON_ABSENT);
        }
    }
}
