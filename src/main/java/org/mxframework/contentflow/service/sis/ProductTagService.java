package org.mxframework.contentflow.service.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Marker;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.tag.ProductTag;
import org.mxframework.contentflow.domain.model.sis.tag.ProductTagRepository;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mx
 */
@Service
public class ProductTagService {

    private final ProductTagRepository productTagRepository;

    public ProductTagService(ProductTagRepository productTagRepository) {
        this.productTagRepository = productTagRepository;
    }

    public ProductTag getByProductAndTagIdAndMarker(Product product, TagId tagId, Marker marker) {
        return productTagRepository.productTagOfProductAndTagIdAndMarker(product, tagId, marker);
    }

    public Integer getProductCounterByTagId(TagId tagId) {
        List<ProductTag> productTagList = (List<ProductTag>) productTagRepository.productTagsOfTagId(tagId);
        Set<Product> productSet = new HashSet<>(productTagList.size());
        productTagList.forEach(productTag -> productSet.add(productTag.product()));
        return productSet.size();
    }

    public List<ProductTag> listByTagId(TagId tagId) {
        return (List<ProductTag>) productTagRepository.productTagsOfTagId(tagId);
    }

    public List<ProductTag> listByProduct(Product product) {
        return (List<ProductTag>) productTagRepository.productTagsOfProduct(product);
    }

    public void add(ProductTag productTag) {
        productTagRepository.add(productTag);
    }

    public void remove(ProductTag productTag) {
        productTagRepository.remove(productTag);
    }

}
