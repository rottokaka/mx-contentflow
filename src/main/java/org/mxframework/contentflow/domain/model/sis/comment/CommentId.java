package org.mxframework.contentflow.domain.model.sis.comment;

import org.mxframework.contentflow.domain.model.AbstractValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mx
 */
@Embeddable
public class CommentId extends AbstractValueObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "comment_id_id", unique = true)
    private String id;

    public CommentId(String id) {
        this();
        this.setId(id);
    }

    protected CommentId() {
        super();
    }

    public String id() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
