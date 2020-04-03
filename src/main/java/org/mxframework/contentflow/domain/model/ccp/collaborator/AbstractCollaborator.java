package org.mxframework.contentflow.domain.model.ccp.collaborator;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author mx
 */
@MappedSuperclass
public abstract class AbstractCollaborator implements Comparable<AbstractCollaborator>, Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private String identity;

    public AbstractCollaborator(String identity) {
        super();
        this.setIdentity(identity);
    }

    protected AbstractCollaborator() {
        super();
    }

    public String identify() {
        return this.identity;
    }

    @Override
    public int compareTo(AbstractCollaborator collaborator) {
        return this.identify().compareTo(collaborator.identify());
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
        return "AbstractCollaborator{" +
                "identity='" + identity + '\'' +
                '}';
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
