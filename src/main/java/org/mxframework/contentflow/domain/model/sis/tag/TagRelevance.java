package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.IdentifiedValueObject;
import org.mxframework.contentflow.domain.model.sis.product.Product;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * TagRelevance: 标签关联
 *
 * @author mx
 */
@Entity
public class TagRelevance extends IdentifiedValueObject {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "[标签关联]标签字符串不能为空")
    @Column(nullable = false, columnDefinition = "VARCHAR(200) COMMENT '标签字符串'")
    private String tags;

    @Embedded
    private Product product;

    public TagRelevance(String tags, Product product) {
        this();
        this.setTags(tags);
        this.setProduct(product);
    }

    protected TagRelevance() {
        super();
    }

    public String tags() {
        return this.tags;
    }

    public Product product() {
        return this.product;
    }


    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "TagRelevance{" +
                "tags='" + tags + '\'' +
                ", product=" + product +
                '}';
    }
}
