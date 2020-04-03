package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.identity.Marker;
import org.mxframework.contentflow.domain.model.sis.tag.ProductTag;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mx
 */
@Repository
public interface ProductTagJpaRepository extends JpaRepository<ProductTag, Long> {

    ProductTag findByProductAndTagIdAndMarker(Product product, TagId tagId, Marker marker);

    List<ProductTag> findAllByTagIdAndProduct_Type(TagId tagId, String contentType);

    List<ProductTag> findAllByTagId(TagId tagId);

    List<ProductTag> findAllByProduct(Product product);

}
