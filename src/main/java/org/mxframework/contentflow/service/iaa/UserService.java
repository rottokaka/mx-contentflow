package org.mxframework.contentflow.service.iaa;

import org.mxframework.contentflow.constant.MxExceptionEnum;
import org.mxframework.contentflow.constant.iaa.UserEnum;
import org.mxframework.contentflow.constant.iaa.UserExceptionEnum;
import org.mxframework.contentflow.domain.model.iaa.*;
import org.mxframework.contentflow.exception.MxException;
import org.mxframework.contentflow.exception.UserException;
import org.mxframework.contentflow.repository.iaa.UserJpaRepository;
import org.mxframework.contentflow.representation.iaa.form.UserCreateForm;
import org.mxframework.contentflow.representation.iaa.form.UserModifyForm;
import org.mxframework.contentflow.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * IUserService: 用户接口
 *
 * @author mx
 */
@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    private final UserJpaRepository userJpaRepository;

    private final AuthorityService authorityService;

    public UserService(UserJpaRepository userJpaRepository, AuthorityService authorityService) {
        this.userJpaRepository = userJpaRepository;
        this.authorityService = authorityService;
    }

    public User principal() {
        User user = SecurityUtil.getPrincipal();
        if (user != null) {
            return (User) this.loadUserByUsername(user.username());
        } else {
            throw new MxException(MxExceptionEnum.MX_USER_NOT_LOGIN);
        }
    }

    public String identity() {
        User user = SecurityUtil.getPrincipal();
        if (user != null) {
            return user.username();
        } else {
            throw new MxException(MxExceptionEnum.MX_USER_NOT_LOGIN);
        }
    }

    public User getByUsername(String username) {
        return userRepository.userOfUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userJpaRepository.findByUsername(username);
    }

    public boolean hasTaskOfDeleteById(Long id, User user) {
        if (user == null) {
            throw new MxException(MxExceptionEnum.MX_USER_NOT_LOGIN);
        }
        User pUser = (User) this.loadUserByUsername(user.username());
        User byId = this.selectById(id);
        if (!(this.hasRoot(pUser) || byId.userId().equals(pUser.userId()))) {
            throw new MxException(MxExceptionEnum.MX_USER_NO_AUTHORITY);
        }
        return true;
    }

    public boolean isValidOnDeleteById(Long id) {
        if (this.selectById(id) == null) {
            throw new UserException(UserExceptionEnum.USER_ON_ABSENT);
        }
        return true;
    }

    public boolean hasRoot(User user) {
        boolean isRoot = false;
        Collection<? extends GrantedAuthority> authorities = user.authorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().contains(UserEnum.USER_AUTHORITY_ROOT.getAuthority())) {
                isRoot = true;
                break;
            }
        }
        return isRoot;
    }

    public List<User> selectAll() {
        return userJpaRepository.findAll();
    }

    public List<User> selectAllByUsernameLike(String username) {
        return userJpaRepository.findAllByUsernameContaining(username);
    }

    public User selectById(Long id) {
        return userJpaRepository.findById(id).orElse(null);
    }

    @Transactional(rollbackFor = {Exception.class})
    public User insert(UserCreateForm insertVO) {
        UserId userId = userRepository.nextIdentiy();
        // 权限设置
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityService.findById(UserEnum.USER_AUTHORITY_USER.getId()));
        User user = new User(userId, insertVO.getUsername(), insertVO.getPassword(), authorities);
        userRepository.add(user);
        return userRepository.userOfUserId(userId);
    }

    private User update(User user) {
        return userJpaRepository.save(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    public User updateById(Long id, UserModifyForm updateVO) {
        // 获取目标用户对象
        User target = selectById(id);
        // 修改用户更新内容
        target.setWebsite(updateVO.getWebsite());
        target.setNotice(updateVO.getNotice());
        target.setNote(updateVO.getNote());
        return update(target);
    }

    @Transactional(rollbackFor = {Exception.class})
    public User updateByIdOfNotice(Long id, String latestNotice) {
        User target = selectById(id);
        target.setNotice(latestNotice);
        return update(target);
    }

    @Transactional(rollbackFor = {Exception.class})
    public User register(User user) {
        return userJpaRepository.save(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

}
