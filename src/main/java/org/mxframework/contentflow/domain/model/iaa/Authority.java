package org.mxframework.contentflow.domain.model.iaa;

import org.mxframework.contentflow.domain.model.IdentifiedValueObject;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

/**
 * Authority: 权限
 *
 * @author mx
 */
@Entity
public class Authority extends IdentifiedValueObject implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    private String name;

    protected Authority() {
    }

    public String name() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getAuthority() {
        return this.name();
    }
}
