package org.mxframework.contentflow.representation.ccp.blog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.ccp.blog.BlogBase;

/**
 * BlogBaseVO: 博客基本[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogBaseVO extends BlogBase {

    public BlogBaseVO(BlogBase blogBase) {
        super(blogBase);
    }

    public BlogBaseVO() {
    }
}
