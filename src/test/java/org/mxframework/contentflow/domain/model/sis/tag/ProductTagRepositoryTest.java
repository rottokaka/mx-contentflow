package org.mxframework.contentflow.domain.model.sis.tag;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mxframework.contentflow.domain.model.sis.identity.Marker;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTagRepositoryTest {

    @Autowired
    private ProductTagRepository productTagRepository;

    @Test
    public void productTagOfProductAndTagIdAndMarker() {
        ProductTag productTag = productTagRepository.productTagOfProductAndTagIdAndMarker(
                new Product("ccp-blog-b8c1f041", "PRODUCT_BLOG")
                , new TagId("sis-tag-4a0c8878")
                , new Marker("guest"));
        Assert.assertNotNull(productTag);
    }
}