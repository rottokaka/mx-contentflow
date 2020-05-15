package org.mxframework.contentflow.repository.ccp;

import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogId;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogSnippet;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogSnippetRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author mx
 */
@Repository
public class HibernateBlogSnippetRepository implements BlogSnippetRepository {

    private final BlogSnippetJpaRepository blogSnippetJpaRepository;

    public HibernateBlogSnippetRepository(BlogSnippetJpaRepository blogSnippetJpaRepository) {
        this.blogSnippetJpaRepository = blogSnippetJpaRepository;
    }

    @Override
    public Collection<BlogSnippet> blogSnippetsOfBlogId(BlogId blogId) {
        return blogSnippetJpaRepository.findAllByBlogId(blogId);
    }

    @Override
    public void add(BlogSnippet blogSnippet) {
        blogSnippetJpaRepository.save(blogSnippet);
    }

    @Override
    public void addAll(Collection<BlogSnippet> blogSnippets) {
        blogSnippetJpaRepository.saveAll(blogSnippets);
    }

    @Override
    public void removeAll(Collection<BlogSnippet> blogSnippets) {
        blogSnippetJpaRepository.deleteAll(blogSnippets);
    }
}
