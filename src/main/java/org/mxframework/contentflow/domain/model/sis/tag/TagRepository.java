package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.sis.identity.Creator;

import java.util.Collection;

/**
 * @author mx
 */
public interface TagRepository {

    TagId nextIdentity();

    Tag tagOfTagId(TagId tagId);

    Tag tagOfNameAndProperty(String name, Integer property);

    Tag tagOfName(String name);

    Collection<Tag> tagsOfProperty(Integer property);

    Collection<Tag> tagsOfCreator(Creator creator);

    Collection<Tag> tagsOfCreatorAndProperty(Creator creator, Integer property);

    Collection<Tag> tags();

    void add(Tag tag);

    void remove(Tag tag);
}
