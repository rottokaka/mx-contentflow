package org.mxframework.contentflow.domain.model.iaa;

import org.mxframework.contentflow.domain.model.AbstractValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mx
 */
@Embeddable
public class UserId extends AbstractValueObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id_id")
    private String id;

    public UserId(String id) {
        this();
        this.setId(id);
    }

    protected UserId() {
        super();
    }

    public String id() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
