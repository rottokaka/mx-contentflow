package org.mxframework.contentflow.application.iaa;

import org.mxframework.contentflow.application.pmc.ProjectApplicationService;
import org.mxframework.contentflow.application.sis.TagApplicationService;
import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.constant.iaa.UserEnum;
import org.mxframework.contentflow.constant.iaa.UserExceptionEnum;
import org.mxframework.contentflow.domain.model.iaa.User;
import org.mxframework.contentflow.exception.NotFoundException;
import org.mxframework.contentflow.representation.iaa.dto.UserCardDTO;
import org.mxframework.contentflow.representation.iaa.form.UserCreateForm;
import org.mxframework.contentflow.representation.iaa.form.UserModifyForm;
import org.mxframework.contentflow.representation.iaa.vo.UserBaseVO;
import org.mxframework.contentflow.representation.iaa.vo.UserCardVO;
import org.mxframework.contentflow.representation.iaa.vo.UserDetailVO;
import org.mxframework.contentflow.representation.pmc.project.dto.ProjectItemDTO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectItemVO;
import org.mxframework.contentflow.representation.sis.tag.vo.TagItemVO;
import org.mxframework.contentflow.service.iaa.ProjectTranslator;
import org.mxframework.contentflow.service.iaa.TagTranslator;
import org.mxframework.contentflow.service.iaa.UserService;
import org.mxframework.contentflow.service.iaa.UserTranslator;
import org.mxframework.contentflow.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author mx
 */
@Service
public class UserApplicationService {

    @Autowired
    private ProjectApplicationService projectApplicationService;
    @Autowired
    private TagApplicationService tagApplicationService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserTranslator userTranslator;
    @Autowired
    private ProjectTranslator projectTranslator;
    @Autowired
    private TagTranslator tagTranslator;

    public User getByUserId(String userId) {
        return null;
    }

    public UserModifyForm getModifyFormByUserId(String userId) {
        return null;
    }

    public UserDetailVO getDetailVoByUsername(String username) {
        User byUsername = userService.getByUsername(username);
        if (byUsername != null) {
            return userTranslator.convetToDetailVo(byUsername);
        } else {
            throw new NotFoundException(UserExceptionEnum.USER_ON_ABSENT.getMessage());
        }
    }

    public List<User> list() {
        return null;
    }

    public List<UserCardVO> listCardVo() {
        List<User> userList = userService.selectAll();
        // 用户过滤
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            Collection<? extends GrantedAuthority> authorities = user.authorities();
            boolean isDeleted = false;
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().contains(UserEnum.USER_AUTHORITY_ROOT.getAuthority())) {
                    // 有且仅有一个管理员用户，移除即可
                    iterator.remove();
                    isDeleted = true;
                    break;
                }
            }
            if (isDeleted) {
                break;
            }
        }
        return userTranslator.convertToCardVo(userList);
    }

    public List<UserCardDTO> listCardByIdentities(List<String> identities) {
        if (identities != null && identities.size() > 0) {
            List<User> userList = new ArrayList<>(identities.size());
            identities.forEach(identity -> userList.add(userService.getByUsername(identity)));
            return userTranslator.convertToCardDto(userList);
        } else {
            return null;
        }
    }

    public List<ProjectItemVO> listProjectItemVoByUsername(String username) {
        boolean owned = SecurityUtil.isPrincipal(username);
        List<ProjectItemDTO> projectItemDtoList;
        if (owned) {
            projectItemDtoList = projectApplicationService.listItemByIdentity(username);
        } else {
            projectItemDtoList = projectApplicationService.listItemByIdentityAndScope(username, ScopeConstant.SCOPE_PUBLIC);
        }
        return projectItemDtoList.isEmpty() ? null : projectTranslator.convertToItemVo(projectItemDtoList);
    }

    public List<TagItemVO> listTagItemVoByUsername(String username) {
        return tagTranslator.convertToItemVo(tagApplicationService.listItemByIdentity(username));
    }

    public User post(UserCreateForm userCreateForm) {
        return null;
    }

    public User updateByUserId(String userId, UserModifyForm userModifyForm) {
        return null;
    }

    public void deleteByUserId(String userId) {

    }

    public UserBaseVO getUserBaseVo() {
        return userTranslator.convertToBaseVo(userService.principal());
    }
}
