package org.mxframework.contentflow.service.ccp;

import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.repository.ccp.BlogJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author mx
 */
@Service
public class BlogPagination {

    @Autowired
    private BlogJpaRepository blogJpaRepository;

    public Page<Blog> pageByKeyword(String keyword, Pageable pageable) {
        if (!"".equals(keyword.trim())) {
            return blogJpaRepository.findDistinctByScopeAndTitleContainingOrSummaryContainingOrContentContaining(ScopeConstant.SCOPE_PUBLIC
                    , keyword
                    , keyword
                    , keyword
                    , pageable);
        } else {
            return blogJpaRepository.findDistinctByScope(ScopeConstant.SCOPE_PUBLIC, pageable);
        }
    }
}
