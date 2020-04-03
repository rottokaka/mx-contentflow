package org.mxframework.contentflow.domain.model.sis.comment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mxframework.contentflow.domain.model.sis.identity.Commenter;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void nextIdentity() {
        CommentId commentId = commentRepository.nextIdentity();
        assertTrue(commentId.id().startsWith("SIS-COMMENT-"));
    }

    @Test
    public void commentOfCommentId() {
        Comment comment = commentRepository.commentOfCommentId(new CommentId("SIS-COMMENT-20244662"));
        assertEquals("SIS-COMMENT-20244662", comment.commentId().id());
    }

    @Test
    public void commentsOfCommenter() {
        Collection<Comment> comments = commentRepository.commentsOfCommenter(new Commenter("rottokaka"));
        assertTrue(comments.size() > 1);
        assertEquals(new ArrayList<Comment>(comments).get(0).commenter().identity(), "rottokaka");
    }

    @Test
    public void commentsOfProduction() {
        Collection<Comment> comments = commentRepository.commentsOfProduction(new Product("CCP-BLOG-11111111", "PRODUCTION-BLOG"));
        assertTrue(comments.size() > 1);
        assertEquals(new ArrayList<Comment>(comments).get(0).production().id(), "CCP-BLOG-11111111");
        assertEquals(new ArrayList<Comment>(comments).get(0).production().type(), "PRODUCTION-BLOG");
    }

    @Test
    public void addComment() {
        CommentId commentId = new CommentId("SIS-COMMENT-11111111");
        commentRepository.addComment(new Comment(commentId
                , new Product("CCP-BLOG-11111111", "PRODUCTION-BLOG")
                , new Commenter("rottokaka")
                , "comment test"));
        Comment comment = commentRepository.commentOfCommentId(commentId);
        assertEquals(commentId.id(), comment.commentId().id());
    }

    @Test
    public void removeComment() {
    }
}