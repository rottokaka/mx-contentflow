package org.mxframework.contentflow.representation.ccp.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.ccp.blog.BlogBase;

/**
 * BlogManageDTO: 博客管理DTO
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogManageDTO extends BlogBaseDTO {
    private static final long serialVersionUID = 1L;

    public BlogManageDTO(BlogBase blogBase) {
        super(blogBase);
    }

    public BlogManageDTO() {
    }
}
