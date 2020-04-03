package org.mxframework.contentflow.domain.model.sis.product;

import org.mxframework.contentflow.domain.model.AbstractValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mx
 */
@Embeddable
public class Product extends AbstractValueObject {

    @Column(name = "product_id")
    private String id;
    @Column(name = "product_type")
    private String type;

    public Product(String id, String type) {
        this.setId(id);
        this.setType(type);
    }

    protected Product() {
        super();
    }

    public String id() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String type() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "Content{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
