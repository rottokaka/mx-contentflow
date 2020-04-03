package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Person;
import org.mxframework.contentflow.domain.model.sis.tag.PersonTag;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mx
 */
@Repository
public interface PersonTagJpaRepository extends JpaRepository<PersonTag, Long> {

    PersonTag findByTagIdAndPerson(TagId tagId, Person person);

    List<PersonTag> findAllByTagId(TagId tagId);

    List<PersonTag> findAllByPerson(Person person);
}
