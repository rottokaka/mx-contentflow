package org.mxframework.contentflow.representation.ccp.blog.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BlogModifyForm: 博客修改表单
 *
 * @author mx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogModifyForm {

    private String title;
    private String content;
    private String contentHtml;

}
