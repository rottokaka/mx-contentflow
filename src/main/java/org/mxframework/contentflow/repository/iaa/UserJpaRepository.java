package org.mxframework.contentflow.repository.iaa;

import org.mxframework.contentflow.domain.model.iaa.User;
import org.mxframework.contentflow.domain.model.iaa.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository: 用户仓库
 *
 * @author mx
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUserId(UserId userId);

    List<User> findAllByUsernameContaining(String username);
}
