package org.mxframework.contentflow.representation.pmc.subject.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.subject.SubjectBase;

/**
 * SubjectManageVO: 专题管理[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectManageVO extends SubjectBaseVO {

    public SubjectManageVO(SubjectBase subjectBase) {
        super(subjectBase);
    }

    public SubjectManageVO() {
    }
}
