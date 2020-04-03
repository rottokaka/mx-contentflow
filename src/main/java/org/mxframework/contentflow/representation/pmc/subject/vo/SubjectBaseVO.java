package org.mxframework.contentflow.representation.pmc.subject.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.subject.SubjectBase;

/**
 * SubjectBaseVO: 专题基础[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectBaseVO extends SubjectBase {

    public SubjectBaseVO(SubjectBase subjectBase) {
        super(subjectBase);
    }

    public SubjectBaseVO() {
    }
}
