package org.mxframework.contentflow.domain.model.pmc.product;

import org.mxframework.contentflow.domain.model.AbstractValueObject;
import org.mxframework.contentflow.exception.MxException;

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
        if (id != null) {
            this.id = id;
        } else {
            throw new MxException("产品ID不能为空");
        }
    }

    public String type() {
        return this.type;
    }

    public void setType(String type) {
        if (type != null) {
            this.type = type;
        } else {
            throw new MxException("产品类型不能为空");
        }
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
