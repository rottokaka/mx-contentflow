package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.sis.identity.Creator;

import java.util.Collection;

/**
 * @author mx
 */
public interface PersonTagGatherRepository {

    PersonTagGather personTagGatherOfNameAndCreator(String name, Creator creator);

    Collection<PersonTagGather> personTagGathersOfCreator(Creator creator);
}
