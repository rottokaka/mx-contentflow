package org.mxframework.contentflow.domain.model.pmc.indentity;

import javax.persistence.Embeddable;

/**
 * @author mx
 */
@Embeddable
public final class Creator extends AbstractIdentity {
    private static final long serialVersionUID = 1L;

    public Creator(String identity) {
        super(identity);
    }

    protected Creator() {
        super();
    }
}
