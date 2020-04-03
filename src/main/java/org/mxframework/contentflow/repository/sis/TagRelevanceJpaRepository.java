package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.tag.TagRelevance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mx
 */
@Repository
public interface TagRelevanceJpaRepository extends JpaRepository<TagRelevance, Long> {

    /**
     * 查找所有标签关联，通过标签名称
     *
     * @param tagName 标签名称
     * @return 标签关联集合
     */
    List<TagRelevance> findAllByTagsContaining(String tagName);
}
