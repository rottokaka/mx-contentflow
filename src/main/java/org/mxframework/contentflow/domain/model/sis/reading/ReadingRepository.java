package org.mxframework.contentflow.domain.model.sis.reading;

import org.mxframework.contentflow.domain.model.sis.identity.Reader;
import org.mxframework.contentflow.domain.model.sis.product.Product;

import java.util.Collection;

/**
 * @author mx
 */
public interface ReadingRepository {

    Reading readingOfReaderAndProduct(Reader reader, Product product);

    Collection<Reading> readingOfProduct(Product product);

    void add(Reading reading);
}
