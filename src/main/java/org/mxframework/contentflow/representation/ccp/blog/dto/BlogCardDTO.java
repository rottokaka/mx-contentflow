package org.mxframework.contentflow.representation.ccp.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.ccp.blog.BlogBase;
import org.mxframework.contentflow.representation.sis.reader.dto.ReadingAtProductOutlineDTO;
import org.mxframework.contentflow.representation.sis.tag.dto.TagItemDTO;

import java.util.List;

/**
 * BlogCardDTO: 博客卡片[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogCardDTO extends BlogBaseDTO {
    private static final long serialVersionUID = 1L;

    private ReadingAtProductOutlineDTO readingAtProductOutlineDto;
    private List<TagItemDTO> tagItemDtoList;

    public BlogCardDTO(BlogBase blogBase) {
        super(blogBase);
    }

    public BlogCardDTO() {
    }
}
