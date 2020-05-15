package org.mxframework.contentflow.domain.model.ccp.product.blog;

import org.mxframework.contentflow.domain.model.AbstractValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author mx
 */
@Embeddable
public class BlogId extends AbstractValueObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "blog_id_id", nullable = false, columnDefinition = "VARCHAR(50) COMMENT '博客ID'")
    private String id;

    public BlogId(String id) {
        this.setId(id);
    }

    protected BlogId() {
        super();
    }

    public String id() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "BlogId{id='" + id + '}';
    }
}
