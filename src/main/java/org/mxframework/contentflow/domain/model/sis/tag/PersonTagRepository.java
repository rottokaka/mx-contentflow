package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.sis.identity.Person;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public interface PersonTagRepository {

    PersonTag personTagOfPersonAndTagId(Person person, TagId tagId);

    Collection<PersonTag> personTagsOfPerson(Person person);

    Collection<PersonTag> personTagsOfTagId(TagId tagId);

    void add(PersonTag personTag);

    void remove(PersonTag personTag);
}
