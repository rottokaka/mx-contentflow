package org.mxframework.contentflow.repository.ccp;

import org.mxframework.contentflow.domain.model.ccp.product.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookRepository: 图书仓库
 *
 * @author m
 * @date 2018-12-25
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
