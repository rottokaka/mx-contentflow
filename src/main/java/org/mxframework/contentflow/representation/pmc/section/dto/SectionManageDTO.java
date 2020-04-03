package org.mxframework.contentflow.representation.pmc.section.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.section.SectionBase;

/**
 * TypeManageDTO: 类型管理[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SectionManageDTO extends SectionBaseDTO {
    private static final long serialVersionUID = 1L;

    public SectionManageDTO(SectionBase sectionBase) {
        super(sectionBase);
    }

    public SectionManageDTO() {
    }
}
