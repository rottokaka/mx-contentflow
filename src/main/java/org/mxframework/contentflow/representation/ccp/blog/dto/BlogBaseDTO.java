package org.mxframework.contentflow.representation.ccp.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.ccp.blog.BlogBase;

import java.io.Serializable;

/**
 * BlogBaseDTO: 博客基础[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogBaseDTO extends BlogBase implements Serializable {
    private static final long serialVersionUID = 1L;

    public BlogBaseDTO(BlogBase blogBase) {
        super(blogBase);
    }

    public BlogBaseDTO() {

    }
}
