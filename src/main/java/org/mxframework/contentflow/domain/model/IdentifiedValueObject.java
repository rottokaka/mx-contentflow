package org.mxframework.contentflow.domain.model;

import javax.persistence.*;

/**
 * @author mx
 */
@MappedSuperclass
public class IdentifiedValueObject extends IdentifiedDomainObject {
    private static final long serialVersionUID = 1L;

    public IdentifiedValueObject(Long id) {
        super(id);
    }

    protected IdentifiedValueObject() {
        super();
    }

}
