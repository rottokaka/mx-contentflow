package org.mxframework.contentflow.domain.model.pmc.project.subject;


import org.mxframework.contentflow.domain.model.IdentifiedValueObject;
import org.mxframework.contentflow.domain.model.pmc.product.Product;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * @author mx
 */
@Entity
public class SubjetProduct extends IdentifiedValueObject {

    @Embedded
    private SubjectId subjectId;
    @Embedded
    private Product product;

    @Column
    private Integer verified;
}
