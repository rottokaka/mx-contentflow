package org.mxframework.contentflow.representation.ccp.blog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.ccp.blog.BlogBase;

/**
 * BlogDetailVO: 博客详情[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogDetailVO extends BlogBaseVO {

    private Boolean authored;

    public BlogDetailVO(BlogBase blogBase) {super(blogBase);}

    public BlogDetailVO() {}
}
