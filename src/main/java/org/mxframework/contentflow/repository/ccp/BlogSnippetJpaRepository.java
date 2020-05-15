package org.mxframework.contentflow.repository.ccp;

import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogId;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogSnippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mx
 */
@Repository
public interface BlogSnippetJpaRepository extends JpaRepository<BlogSnippet, Long> {

    List<BlogSnippet> findAllByBlogId(BlogId blogId);
}
