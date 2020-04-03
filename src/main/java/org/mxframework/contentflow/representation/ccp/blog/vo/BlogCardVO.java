package org.mxframework.contentflow.representation.ccp.blog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.ccp.blog.BlogBase;
import org.mxframework.contentflow.representation.sis.reader.vo.ReadingAtProductOutlineVO;
import org.mxframework.contentflow.representation.sis.tag.vo.TagItemVO;

import java.util.List;

/**
 * BlogCardVO: 博客卡片[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogCardVO extends BlogBaseVO {

    private ReadingAtProductOutlineVO readerAtProductOutlineVo;
    private List<TagItemVO> tagItemVoList;

    public BlogCardVO(BlogBase blogBase) {
        super(blogBase);
    }

    public BlogCardVO() {
    }
}
