package org.mxframework.contentflow.representation.sis.tag.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.sis.tag.TagBase;

import java.util.List;

/**
 * TagDetailVO: 标签详情[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagDetailVO extends TagBaseVO {
    private List<TagRelevantVO> tagRelevantVoList;

    public TagDetailVO(TagBase tagBase) {
        super(tagBase);
    }

    public TagDetailVO() {
    }
}
