package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Reader;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.reading.Reading;
import org.mxframework.contentflow.domain.model.sis.reading.ReadingRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernateReadingRepository implements ReadingRepository {

    private final ReadingJpaRepository readingJpaRepository;

    public HibernateReadingRepository(ReadingJpaRepository readingJpaRepository) {
        this.readingJpaRepository = readingJpaRepository;
    }

    @Override
    public Reading readingOfReaderAndProduct(Reader reader, Product product) {
        return readingJpaRepository.findByReaderAndProduct(reader, product);
    }

    @Override
    public Collection<Reading> readingOfProduct(Product product) {
        return readingJpaRepository.findAllByProduct(product);
    }

    @Override
    public void add(Reading reading) {
        readingJpaRepository.save(reading);
    }
}
