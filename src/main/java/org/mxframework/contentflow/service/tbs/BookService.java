package org.mxframework.contentflow.service.tbs;

import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.domain.model.iaa.User;
import org.mxframework.contentflow.domain.model.pmc.axis.Axis;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.representation.ccp.book.BookVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * BookService: 图书接口
 *
 * @author mx
 */
@Service
public class BookService {

    public BookVO getByAxisSet(List<Axis> axisList) {
        BookVO bookVo = new BookVO();
        Blog blog = new Blog(null);
        StringBuilder content = new StringBuilder();
        // 确定博客的作者和标签
        List<User> userList = new ArrayList<>();
        List<Tag> tagList = new ArrayList<>();
        for (Axis axis : axisList) {
            // TODO
            /*content.append(BlogConstant.BLOG_CONTENT_H2.replaceAll("H2", axis.getDescription()));*/
        }
        blog.setContent(content.toString());
        bookVo.setBlog(blog);
        bookVo.setUserList(userList);
        bookVo.setTagList(tagList);
        return bookVo;
    }
}
