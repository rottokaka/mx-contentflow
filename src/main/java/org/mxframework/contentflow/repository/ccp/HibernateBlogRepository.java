package org.mxframework.contentflow.repository.ccp;

import org.mxframework.contentflow.domain.model.ccp.collaborator.Blogger;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogId;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogRepository;
import org.mxframework.contentflow.util.UUIDUtil;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernateBlogRepository implements BlogRepository {

    private final static String BLOG_ID_PREFIX = "ccp-blog-";

    public HibernateBlogRepository(BlogJpaRepository blogJpaRepository) {
        this.blogJpaRepository = blogJpaRepository;
    }

    private final BlogJpaRepository blogJpaRepository;

    @Override
    public BlogId nextIdentity() {
        return new BlogId(BLOG_ID_PREFIX + UUIDUtil.getUuidPartOne());
    }

    @Override
    public Blog blogOfBlogId(BlogId blogId) {
        return blogJpaRepository.findByBlogId(blogId);
    }


    @Override
    public Collection<Blog> blogsOfBlogger(Blogger blogger) {
        return blogJpaRepository.findAllByBloggerOrderByGmtModifiedDesc(blogger);
    }

    @Override
    public Collection<Blog> blogsOfBloggerAndScope(Blogger blogger, Integer scope) {
        return blogJpaRepository.findAllByScopeAndBloggerOrderByGmtCreateDesc(scope, blogger);
    }

    @Override
    public Collection<Blog> blogsOfBloggerAndArchived(Blogger blogger, Integer archived) {
        return blogJpaRepository.findAllByArchivedAndBloggerOrderByGmtModifiedDesc(archived, blogger);
    }

    @Override
    public Collection<Blog> blogsOfScope(Integer scope) {
        return blogJpaRepository.findAllByScope(scope);
    }

    @Override
    public void add(Blog blog) {
        blogJpaRepository.save(blog);
    }

    @Override
    public void addAll(Collection<Blog> blogs) {
        blogJpaRepository.saveAll(blogs);
    }

    @Override
    public void remove(Blog blog) {
        blogJpaRepository.delete(blog);
    }

    @Override
    public void removeAll(Collection<Blog> blogs) {
        blogJpaRepository.deleteAll(blogs);
    }
}
