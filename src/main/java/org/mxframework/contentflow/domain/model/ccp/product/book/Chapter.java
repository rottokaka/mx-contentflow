package org.mxframework.contentflow.domain.model.ccp.product.book;

import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;

import javax.persistence.*;

/**
 * Chapter: 篇章
 *
 * @author mx
 */
public class Chapter extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @Column
    private String name;
    @Column
    private String setDescription;

    @OneToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", foreignKey = @ForeignKey(name = "fk_part_chapter"))
    private Part part;

    // Constructors~
    // =================================================================================================================

    protected Chapter() {
        super();
    }

    public String name() {
        return this.name;
    }

    public String setDescription() {
        return this.setDescription;
    }

    public Part part() {
        return this.part;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSetDescription(String setDescription) {
        this.setDescription = setDescription;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
