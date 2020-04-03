package org.mxframework.contentflow.representation.ccp.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.domain.model.pmc.project.Project;
import org.mxframework.contentflow.domain.model.iaa.User;

import java.io.Serializable;
import java.util.List;

/**
 * BookVO: 图书[VO]
 *
 * @author mx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Project project;
    private Blog blog;
    private List<Tag> tagList;
    private List<User> userList;
}
