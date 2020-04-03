package org.mxframework.contentflow.domain.model.sis.identity;

import javax.persistence.Embeddable;

/**
 * @author mx
 */
@Embeddable
public final class Operator extends AbstractIdentity {

    public Operator(String identity) {
        super(identity);
    }

    protected Operator() {
        super();
    }
}
