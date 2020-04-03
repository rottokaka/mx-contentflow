package org.mxframework.contentflow.domain.model.ccp.product.blog;

import org.mxframework.contentflow.domain.model.ccp.collaborator.Blogger;

import java.util.Collection;

/**
 * @author mx
 */
public interface BlogRepository {

    BlogId nextIdentity();

    Blog blogOfBlogId(BlogId blogId);

    Collection<Blog> blogsOfBlogger(Blogger blogger);

    Collection<Blog> blogsOfBloggerAndScope(Blogger blogger, Integer scope);

    Collection<Blog> blogsOfBloggerAndArchived(Blogger blogger, Integer archived);

    Collection<Blog> blogsOfScope(Integer scope);

    void add(Blog blog);

    void addAll(Collection<Blog> blogs);

    void remove(Blog blog);

    void removeAll(Collection<Blog> blogs);
}
