package org.mxframework.contentflow.representation.pmc.section.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.section.SectionBase;

/**
 * SectionBaseVO: 类型基础[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SectionBaseVO extends SectionBase {

    public SectionBaseVO(SectionBase sectionBase) {
        super(sectionBase);
    }

    public SectionBaseVO() {
    }
}
