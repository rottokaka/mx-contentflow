package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.IdentifiedValueObject;
import org.mxframework.contentflow.domain.model.sis.identity.Creator;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * @author mx
 */
@Entity
public class PersonTagGather extends IdentifiedValueObject {

    @Column
    private String name;
    @Column
    private String description;
    @Embedded
    private Creator creator;

    public PersonTagGather(String name, String description, Creator creator) {
        this();
        this.setName(name);
        this.setDescription(description);
        this.setCreator(creator);
    }

    protected PersonTagGather() {
        super();
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public Creator creator() {
        return this.creator;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "PersonTagGather{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                '}';
    }
}
