package org.mxframework.contentflow.domain.model.sis.comment;

import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.identity.Commenter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Comment: 评论
 *
 * @author mx
 */
@Entity
public class Comment extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @Embedded
    private CommentId commentId;
    @Embedded
    private Product product;
    @Embedded
    private Commenter commenter;
    @Column
    private String content;

    public Comment(CommentId commentId, Product product, Commenter commenter, String content) {
        super();
        this.setCommentId(commentId);
        this.setProduct(product);
        this.setCommenter(commenter);
        this.setContent(content);
    }

    protected Comment () {
        super();
    }

    public CommentId commentId() {
        return this.commentId;
    }

    public void setCommentId(CommentId commentId) {
        this.commentId = commentId;
    }

    public Product production() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Commenter commenter() {
        return this.commenter;
    }

    public void setCommenter(Commenter commenter) {
        this.commenter = commenter;
    }

    public String content() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", product=" + product +
                ", commenter=" + commenter +
                ", content='" + content + '\'' +
                '}';
    }
}
