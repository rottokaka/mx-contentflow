package org.mxframework.contentflow.repository.iaa;

import org.mxframework.contentflow.domain.model.iaa.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AuthorityRepository: 权限仓库
 *
 * @author mx
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
