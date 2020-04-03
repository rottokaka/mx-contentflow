package org.mxframework.contentflow.service.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Reader;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.reading.Reading;
import org.mxframework.contentflow.domain.model.sis.reading.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mx
 */
@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    public Reading getByReaderAndProduct(Reader reader, Product product) {
        return readingRepository.readingOfReaderAndProduct(reader, product);
    }

    public List<Reading> listByProduct(Product product) {
        return (List<Reading>) readingRepository.readingOfProduct(product);
    }

    public void add(Reading reading) {
        readingRepository.add(reading);
    }

    public void update(Reading reading) {
        readingRepository.add(reading);
    }
}
