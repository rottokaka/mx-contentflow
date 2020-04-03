package org.mxframework.contentflow.domain.model.ccp.product.book;

import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Book: 图书
 *
 * @author mx
 */
@Entity
public class Book extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @Embedded
    private BookId bookId;
    private String summary;

    public Book(BookId bookId, String summary) {
        this();
        this.setBookId(bookId);
        this.setSummary(summary);
    }

    protected Book() {
        super();
    }

    public BookId bookId() {
        return this.bookId;
    }

    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    public String summary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
