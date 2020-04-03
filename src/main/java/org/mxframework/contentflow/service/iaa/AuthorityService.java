package org.mxframework.contentflow.service.iaa;

import org.mxframework.contentflow.domain.model.iaa.Authority;
import org.mxframework.contentflow.repository.iaa.AuthorityRepository;
import org.springframework.stereotype.Service;

/**
 * AuthorityService: 权限接口
 *
 * @author mx
 */
@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority findById(Long id) {
        return authorityRepository.findById(id).orElse(null);
    }
}
