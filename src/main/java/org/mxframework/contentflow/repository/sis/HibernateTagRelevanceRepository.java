package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.tag.TagRelevance;
import org.mxframework.contentflow.domain.model.sis.tag.TagRelevanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author mx
 */
@Repository
public class HibernateTagRelevanceRepository implements TagRelevanceRepository {

    @Autowired
    private TagRelevanceJpaRepository tagRelevanceJpaRepository;

    @Override
    public void add(TagRelevance tagRelevance) {
        tagRelevanceJpaRepository.save(tagRelevance);
    }
}
