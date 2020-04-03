package org.mxframework.contentflow.repository.ccp;

import org.mxframework.contentflow.domain.model.ccp.collaborator.Blogger;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BlogRepository: 博客仓库
 *
 * @author mx
 */
@Repository
public interface BlogJpaRepository extends JpaRepository<Blog, Long> {

    List<Blog> findAllByBlogIdIn(List<BlogId> blogIds);

    List<Blog> findAllByScope(Integer scope);

    List<Blog> findTop10ByScopeOrderByGmtCreateDesc(Integer scope);

    Page<Blog> findDistinctByScopeAndTitleContainingOrSummaryContainingOrContentContaining(Integer scope, String title, String summary, String content, Pageable pageable);

    Page<Blog> findDistinctByScope(Integer scope, Pageable pageable);

    List<Blog> findAllByBloggerOrderByGmtModifiedDesc(Blogger blogger);

    List<Blog> findAllByArchivedAndBloggerOrderByGmtModifiedDesc(Integer archived, Blogger blogger);

    List<Blog> findAllByScopeAndBloggerOrderByGmtCreateDesc(Integer scope, Blogger blogger);

    List<Blog> findAllByScopeAndBlogIdIn(Integer scope, List<BlogId> blogIds);

    Blog findByBlogId(BlogId blogId);

    void deleteAllByBlogger(Blogger blogger);
}
