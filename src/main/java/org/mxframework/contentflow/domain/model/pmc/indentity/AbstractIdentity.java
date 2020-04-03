package org.mxframework.contentflow.domain.model.pmc.indentity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author mx
 */
@MappedSuperclass
public abstract class AbstractIdentity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "identity")
    private String identity;

    public AbstractIdentity(String identity) {
        this.setIdentity(identity);
    }

    protected AbstractIdentity() {
        super();
    }

    public String identity() {
        return this.identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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
        return "AbstractIdentity{" +
                "identity='" + identity + '\'' +
                '}';
    }
}
