package org.mxframework.contentflow.domain.model.sis.reading;

import org.mxframework.contentflow.constant.sis.ReadingConstant;
import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.sis.identity.Reader;
import org.mxframework.contentflow.domain.model.sis.product.Product;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Reading: 阅读，浏览
 *
 * @author mx
 */
@Entity
public class Reading extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @Embedded
    private Product product;

    @Embedded
    private Reader reader;

    @NotNull(message = "已喜欢状态不能为空")
    @Column(name = "is_liked", nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '已喜欢状态：0->未标明；1->已喜欢'")
    private Integer liked;

    @NotNull(message = "不喜欢状态不能为空")
    @Column(name = "is_disliked", nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '不喜欢状态：0->未标明；1->不喜欢'")
    private Integer disliked;

    @NotNull(message = "次数不能为空")
    @Column(nullable = false, columnDefinition = "INTEGER UNSIGNED COMMENT '阅读次数'")
    private Integer counter;

    // ~ Constructors
    // =================================================================================================================

    public Reading(Product product, Reader reader, Integer liked, Integer disliked, Integer counter) {
        this();
        this.setProduct(product);
        this.setReader(reader);
        this.setLiked(liked);
        this.setDisliked(disliked);
        this.setCounter(counter);
    }

    public Reading(Product product, Reader reader) {
        this(product, reader, ReadingConstant.READER_LIKED_NOT, ReadingConstant.READER_DISLIKED_NOT, ReadingConstant.READER_COUNTER_DEFAULT);
    }

    protected Reading() {
        super();
    }

    public Product product() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Reader reader() {
        return this.reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Integer liked() {
        return this.liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Integer disliked() {
        return this.disliked;
    }

    public void setDisliked(Integer disliked) {
        this.disliked = disliked;
    }

    public Integer counter() {
        return this.counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
