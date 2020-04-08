package org.mxframework.contentflow.service.ccp;

import org.mxframework.contentflow.constant.ccp.BlogConstant;
import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.domain.model.ccp.collaborator.Blogger;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogId;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogRepository;
import org.mxframework.contentflow.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * BlogService: 博客接口
 *
 * @author mx
 */
@Service
public class BlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);

    @Autowired
    private BlogRepository blogRepository;

    public BlogId nextIdentity() {
        return blogRepository.nextIdentity();
    }

    public Blog getByBlogId(BlogId blogId) {
        return blogRepository.blogOfBlogId(blogId);
    }

    public List<Blog> listByScope(Integer scope) {
        return (List<Blog>) blogRepository.blogsOfScope(scope);
    }

    public List<Blog> listByBloggerAndScope(Blogger blogger, Integer scope) {
        return (List<Blog>) blogRepository.blogsOfBloggerAndScope(blogger, scope);
    }

    public List<Blog> listByBloggerAndArchived(Blogger blogger, Integer archived) {
        return (List<Blog>) blogRepository.blogsOfBloggerAndArchived(blogger, archived);
    }

    public List<Blog> listByBlogger(Blogger blogger) {
        return (List<Blog>) blogRepository.blogsOfBlogger(blogger);
    }

    public List<Blog> listCardByBlogIdList(List<BlogId> blogIdList) {
        List<Blog> blogList = new ArrayList<>(blogIdList.size());
        for (BlogId blogId : blogIdList) {
            blogList.add(blogRepository.blogOfBlogId(blogId));
        }
        return blogList;
    }

    public List<Blog> listByBlogIdList(List<BlogId> blogIdList) {
        List<Blog> blogList = new ArrayList<>(blogIdList.size());
        for (BlogId blogId : blogIdList) {
            blogList.add(blogRepository.blogOfBlogId(blogId));
        }
        return blogList;
    }

    public void add(Blog blog) {
        blogRepository.add(blog);
    }

    public void insertInFile(File blogFile) {
        String content = FileUtil.read(blogFile);
        Blog blog = new Blog(blogRepository.nextIdentity());
        blog.setScope(ScopeConstant.SCOPE_PRIVATE);
        blog.setCollectionNotAllowed(BlogConstant.BLOG_NOT_ALLOW_COLLECT_DEFAULT_FALSE);
        blog.setContent(content);
        blogRepository.add(blog);
    }

    public void update(Blog blog) {
        blogRepository.add(blog);
    }

    public void updateArchived(BlogId blogId, Integer factor) {
        Blog blog = blogRepository.blogOfBlogId(blogId);
        Integer archived = blog.archived();
        blog.setArchived(archived + factor);
        blogRepository.add(blog);
    }

    public void patchAllArchived(List<BlogId> blogIdList, Integer factor) {
        if (blogIdList != null && blogIdList.size() > 0) {
            blogIdList.forEach(blogId -> this.updateArchived(blogId, factor));
        }
    }

    public void deleteByBlogId(BlogId blogId) {
        blogRepository.remove(blogRepository.blogOfBlogId(blogId));
    }

    public void removeAll(List<Blog> blogList) {
        blogRepository.removeAll(blogList);
    }
}
