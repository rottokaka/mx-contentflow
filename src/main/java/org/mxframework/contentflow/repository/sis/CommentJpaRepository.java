package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.comment.Comment;
import org.mxframework.contentflow.domain.model.sis.comment.CommentId;
import org.mxframework.contentflow.domain.model.sis.identity.Commenter;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mx
 */
@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    Comment findByCommentId(CommentId commentId);

    List<Comment> findAllByCommenter(Commenter commenter);

    List<Comment> findAllByProduct(Product product);

}
