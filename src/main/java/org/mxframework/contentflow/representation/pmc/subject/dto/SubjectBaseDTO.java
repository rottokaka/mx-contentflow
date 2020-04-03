package org.mxframework.contentflow.representation.pmc.subject.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.subject.SubjectBase;

import java.io.Serializable;

/**
 * SubjectBaseDTO: 专题基础[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectBaseDTO extends SubjectBase implements Serializable {
    private static final long serialVersionUID = 1L;

    public SubjectBaseDTO(SubjectBase subjectBase) {
        super(subjectBase);
    }

    public SubjectBaseDTO() {
    }
}
