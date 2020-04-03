package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.sis.identity.Marker;
import org.mxframework.contentflow.domain.model.sis.product.Product;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * @author mx
 */
@Entity
public class ProductTag extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @Embedded
    private Product product;
    @Embedded
    private TagId tagId;
    @Embedded
    private Marker marker;

    public ProductTag(Product product, TagId tagId, Marker marker) {
        this();
        this.setProduct(product);
        this.setTagId(tagId);
        this.setMarker(marker);
    }

    protected ProductTag() {
        super();
    }

    public TagId tagId() {
        return this.tagId;
    }

    public void setTagId(TagId tagId) {
        this.tagId = tagId;
    }

    public Product product() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Marker marker() {
        return this.marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @Override
    public String toString() {
        return "ProductTag{" +
                "product=" + product +
                ", tagId=" + tagId +
                ", marker=" + marker +
                '}';
    }
}
