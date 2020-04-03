package org.mxframework.contentflow.representation.sis.tag.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.sis.tag.TagBase;

/**
 * TagItemVO: 标签条目[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagItemVO extends TagBaseVO {

    public TagItemVO(TagBase tagBase) {
        super(tagBase);
    }

    public TagItemVO() {
    }
}
