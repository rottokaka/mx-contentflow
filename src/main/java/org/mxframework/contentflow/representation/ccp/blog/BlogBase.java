package org.mxframework.contentflow.representation.ccp.blog;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author mx
 */
@Data
public class BlogBase {

    private String blogId;
    private String bloggerIdentity;
    private String title;
    private String subTitle;
    private String summary;
    private String content;
    private String contentHtml;
    private Integer scope;
    private Integer collectionNotAllowed;
    private Integer archived;
    private Date gmtCreate;
    private Date gmtModified;

    private String aboveBlogId;

    public BlogBase(BlogBase blogBase) {
        BeanUtils.copyProperties(blogBase, this);
    }

    public BlogBase() {

    }

}
