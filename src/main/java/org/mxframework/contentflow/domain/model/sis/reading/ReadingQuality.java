package org.mxframework.contentflow.domain.model.sis.reading;

import org.mxframework.contentflow.domain.model.AbstractValueObject;
import org.mxframework.contentflow.domain.model.sis.product.Product;

/**
 * @author mx
 */
public class ReadingQuality extends AbstractValueObject {

    private Product product;
    private Integer counterSum;
    private String likePercent;

    public ReadingQuality(Product product, Integer counterSum, String likePercent) {
        this.setProduct(product);
        this.setCounterSum(counterSum);
        this.setLikePercent(likePercent);
    }

    public Product product() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer counterSum() {
        return this.counterSum;
    }

    public void setCounterSum(Integer counterSum) {
        this.counterSum = counterSum;
    }

    public String likePercent() {
        return this.likePercent;
    }

    public void setLikePercent(String likePercent) {
        this.likePercent = likePercent;
    }
}
