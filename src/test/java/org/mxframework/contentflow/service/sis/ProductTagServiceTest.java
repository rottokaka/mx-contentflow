package org.mxframework.contentflow.service.sis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mxframework.contentflow.domain.model.sis.identity.Marker;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.tag.ProductTag;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTagServiceTest {

    @Autowired
    private ProductTagService productTagService;

    @Test
    public void getByProductAndTagIdAndMarker() {

        ProductTag byProductAndTagIdAndMarker = productTagService.getByProductAndTagIdAndMarker(new Product("ccp-blog-61886c17", "PRODUCT_BLOG")
                , new TagId("sis-tag-12ff5c6e")
                , new Marker("user"));
        Assert.assertNotNull(byProductAndTagIdAndMarker);
    }
}