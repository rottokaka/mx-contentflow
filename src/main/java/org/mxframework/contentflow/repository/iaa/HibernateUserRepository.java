package org.mxframework.contentflow.repository.iaa;

import org.mxframework.contentflow.domain.model.iaa.User;
import org.mxframework.contentflow.domain.model.iaa.UserId;
import org.mxframework.contentflow.domain.model.iaa.UserRepository;
import org.mxframework.contentflow.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mx
 */
@Component
public class HibernateUserRepository implements UserRepository {

    private final static String USER_ID_PREFIX = "iaa-user-";

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public UserId nextIdentiy() {
        return new UserId(USER_ID_PREFIX + UUIDUtil.getUuidPartOne());
    }

    @Override
    public User userOfUserId(UserId userId) {
        return userJpaRepository.findByUserId(userId);
    }

    @Override
    public User userOfUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public void add(User user) {
        userJpaRepository.save(user);
    }
}
