package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.sis.identity.Person;

import javax.persistence.*;

/**
 * @author mx
 */
@Entity
public class PersonTag extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @Embedded
    private Person person;
    @Embedded
    private TagId tagId;

    @OneToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_tag_gather_id")
    private PersonTagGather personTagGather;

    public PersonTag(Person person, TagId tagId) {
        this();
        this.setPerson(person);
        this.setTagId(tagId);
    }

    protected PersonTag() {
        super();
    }

    public Person person() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TagId tagId() {
        return this.tagId;
    }

    public void setTagId(TagId tagId) {
        this.tagId = tagId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "PersonTag{" +
                "tagId=" + tagId +
                ", person=" + person +
                '}';
    }
}
