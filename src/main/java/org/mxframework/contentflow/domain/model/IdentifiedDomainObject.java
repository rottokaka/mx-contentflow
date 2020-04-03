package org.mxframework.contentflow.domain.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mx
 */
@MappedSuperclass
public class IdentifiedDomainObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT COMMENT '委托ID'")
    private Long id;

    public IdentifiedDomainObject(Long id) {
        this.setId(id);
    }

    protected IdentifiedDomainObject() {
        super();
        this.setId(-1L);
    }

    protected Long id() {
        return this.id;
    }

    protected void setId(Long id) {
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
        return "IdentifiedDomainObject{" +
                "id=" + id +
                '}';
    }
}
