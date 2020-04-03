package org.mxframework.contentflow.domain.model.iaa;

/**
 * @author mx
 */
public interface UserRepository {

    UserId nextIdentiy();

    User userOfUserId(UserId userId);

    User userOfUsername(String username);

    void add(User user);
}
