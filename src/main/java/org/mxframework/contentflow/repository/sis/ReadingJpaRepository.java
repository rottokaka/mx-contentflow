package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Reader;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.reading.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mx
 */
@Repository
public interface ReadingJpaRepository extends JpaRepository<Reading, Long> {

    List<Reading> findAllByProduct(Product product);

    Reading findByReaderAndProduct(Reader reader, Product product);
}
