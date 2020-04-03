package org.mxframework.contentflow.representation.sis.tag.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.domain.model.iaa.User;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * TagCardDTO: 标签卡片[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagCardDTO extends TagBaseDTO {

    /**
     * userList: 用户集合
     */
    private List<User> userList;

    /**
     * blogList: 博客集合
     */
    private List<Blog> blogList;

    public TagCardDTO(Tag tag) {
        BeanUtils.copyProperties(tag, this);
    }

}
