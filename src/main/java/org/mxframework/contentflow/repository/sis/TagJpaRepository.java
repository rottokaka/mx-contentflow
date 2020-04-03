package org.mxframework.contentflow.repository.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Creator;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mx
 */
@Repository
public interface TagJpaRepository extends JpaRepository<Tag, Long> {


    Tag findByTagId(TagId tagId);

    /**
     * 查找所有标签，通过名称
     *
     * @param name 名称
     * @return 标签集合
     */
    List<Tag> findAllByName(String name);

    List<Tag> findAllByProperty(Integer property);

    /**
     * 查找所有标签，通过性质和创建者
     *
     * @param property 性质
     * @param creator  创建者
     * @return 标签集合
     */
    List<Tag> findAllByPropertyAndCreator(Integer property, Creator creator);

    /**
     * 查找所有标签，通过创建者
     *
     * @param creator 创建者
     * @return 标签集合
     */
    List<Tag> findAllByCreator(Creator creator);

    /**
     * 查找标签，通过名称和方式
     *
     * @param name 名称
     * @return 标签
     */
    Tag findByName(String name);

    Tag findByNameAndProperty(String name, Integer tagPropertyPrivateMarkup);
}
