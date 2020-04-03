package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.constant.sis.TagConstant;
import org.mxframework.contentflow.domain.model.sis.identity.Creator;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.mxframework.contentflow.domain.model.sis.tag.TagRepository;
import org.mxframework.contentflow.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernateTagRepository implements TagRepository {
    private static final Logger logger = LoggerFactory.getLogger(HibernateTagRepository.class);

    private final static String TAG_ID_PREFIX = "sis-tag-";

    private final TagJpaRepository tagJpaRepository;

    public HibernateTagRepository(TagJpaRepository tagJpaRepository) {
        this.tagJpaRepository = tagJpaRepository;
    }

    @Override
    public TagId nextIdentity() {
        return new TagId(TAG_ID_PREFIX + UUIDUtil.getUuidPartOne());
    }

    @Override
    public Tag tagOfTagId(TagId tagId) {
        return tagJpaRepository.findByTagId(tagId);
    }

    @Override
    public Tag tagOfNameAndProperty(String name, Integer property) {
        return tagJpaRepository.findByNameAndProperty(name, TagConstant.TAG_PROPERTY_PRIVATE_MARKUP);
    }

    @Override
    public Tag tagOfName(String name) {
        return tagJpaRepository.findByName(name);
    }

    @Override
    public Collection<Tag> tagsOfProperty(Integer property) {
        return tagJpaRepository.findAllByProperty(property);
    }

    @Override
    public Collection<Tag> tagsOfCreator(Creator creator) {
        return tagJpaRepository.findAllByCreator(creator);
    }

    @Override
    public Collection<Tag> tagsOfCreatorAndProperty(Creator creator, Integer property) {
        return tagJpaRepository.findAllByPropertyAndCreator(property, creator);
    }

    @Override
    public Collection<Tag> tags() {
        return tagJpaRepository.findAll();
    }


    @Override
    public void add(Tag tag) {
        tagJpaRepository.save(tag);
    }

    @Override
    public void remove(Tag tag) {
        tagJpaRepository.delete(tag);
    }
}
