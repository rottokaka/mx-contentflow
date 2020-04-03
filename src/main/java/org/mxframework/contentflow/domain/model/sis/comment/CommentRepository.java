package org.mxframework.contentflow.domain.model.sis.comment;

import org.mxframework.contentflow.domain.model.sis.identity.Commenter;
import org.mxframework.contentflow.domain.model.sis.product.Product;

import java.util.Collection;

/**
 * @author mx
 */
public interface CommentRepository {

    CommentId nextIdentity();

    Comment commentOfCommentId(CommentId commentId);

    Collection<Comment> commentsOfCommenter(Commenter commenter);

    Collection<Comment> commentsOfProduction(Product product);

    void addComment(Comment comment);

    void removeComment(Comment comment);
}
