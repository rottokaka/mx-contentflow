package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Creator;
import org.mxframework.contentflow.domain.model.sis.tag.PersonTagGather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mx
 */
@Repository
public interface PersonTagGatherJpaRepository extends JpaRepository<PersonTagGather, Long> {

    PersonTagGather findByNameAndCreator(String name, Creator creator);

    List<PersonTagGather> findAllByCreator(Creator creator);
}
