package org.mxframework.contentflow.domain.model.ccp.product.blog;

import com.google.common.base.Objects;
import org.mxframework.contentflow.constant.ccp.BlogSnippetType;
import org.mxframework.contentflow.domain.model.IdentifiedValueObject;
import org.mxframework.contentflow.domain.model.ccp.product.snippet.Snippet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author mx
 */
@Entity
public class BlogSnippet extends IdentifiedValueObject {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "博客ID不能为空")
    @Embedded
    private BlogId blogId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "snippet_id", referencedColumnName = "id", columnDefinition = "bigint COMMENT '片段委托ID'")
    private Snippet snippet;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`blog_snippet_type`", columnDefinition = "tinyint unsigned COMMENT '类型'")
    private BlogSnippetType blogSnippetType;

    @Column(name = "rank", nullable = false, columnDefinition = "tinyint unsigned COMMENT '排列'")
    private Integer rank;

    public BlogSnippet(BlogId blogId, Snippet snippet, BlogSnippetType blogSnippetType, Integer rank) {
        this();
        this.setBlogId(blogId);
        this.setSnippet(snippet);
        this.setBlogSnippetType(blogSnippetType);
        this.setRank(rank);
    }

    public void setBlogId(BlogId blogId) {
        this.blogId = blogId;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public void setBlogSnippetType(BlogSnippetType blogSnippetType) {
        this.blogSnippetType = blogSnippetType;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public BlogId blogId() {
        return this.blogId;
    }

    public Snippet snippet() {
        return this.snippet;
    }

    public BlogSnippetType blogSnippetType() {
        return this.blogSnippetType;
    }

    public Integer rank() {
        return this.rank;
    }

    protected BlogSnippet() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlogSnippet)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BlogSnippet that = (BlogSnippet) o;
        return Objects.equal(blogId, that.blogId) &&
                Objects.equal(snippet, that.snippet) &&
                blogSnippetType == that.blogSnippetType &&
                Objects.equal(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), blogId, snippet, blogSnippetType, rank);
    }

    @Override
    public String toString() {
        return "BlogSnippet{" +
                "blogId=" + blogId +
                ", snippet=" + snippet +
                ", blogSnippetType=" + blogSnippetType +
                ", rank=" + rank +
                '}';
    }
}
