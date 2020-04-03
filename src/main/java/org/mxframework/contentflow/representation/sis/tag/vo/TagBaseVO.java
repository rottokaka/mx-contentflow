package org.mxframework.contentflow.representation.sis.tag.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.sis.tag.TagBase;

/**
 * TagBaseVO: 标签基本视图对象
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagBaseVO extends TagBase {

    public TagBaseVO(TagBase tagBase) {
        super(tagBase);
    }

    public TagBaseVO() {

    }
}
