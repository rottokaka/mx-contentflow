package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Creator;
import org.mxframework.contentflow.domain.model.sis.tag.PersonTagGather;
import org.mxframework.contentflow.domain.model.sis.tag.PersonTagGatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernatePersonTagGatherRepository implements PersonTagGatherRepository {

    @Autowired
    private PersonTagGatherJpaRepository personTagGatherJpaRepository;

    @Override
    public PersonTagGather personTagGatherOfNameAndCreator(String name, Creator creator) {
        return personTagGatherJpaRepository.findByNameAndCreator(name, creator);
    }

    @Override
    public Collection<PersonTagGather> personTagGathersOfCreator(Creator creator) {
        return personTagGatherJpaRepository.findAllByCreator(creator);
    }
}
