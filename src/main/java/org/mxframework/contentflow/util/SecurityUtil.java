package org.mxframework.contentflow.util;

import org.mxframework.contentflow.domain.model.iaa.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * SecurityUtil: 安全工具类
 *
 * @author mx
 * @date 2018-12-11
 */
public class SecurityUtil {

    /**
     * 判断当前登陆用户是否是该用户主页或者博客的拥有着
     *
     * @param username 账户
     * @return boolean
     */
    public static boolean isPrincipal(String username) {
        boolean isOwner = false;
        // principal(当事人)
        User principal = getPrincipal();
        if (principal != null && username.equals(principal.getUsername())) {
            isOwner = true;
        }
        return isOwner;
    }

    /**
     * 获取当事人
     *
     * @return User, allow return <code>null</code>
     */
    public static User getPrincipal() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }
}
