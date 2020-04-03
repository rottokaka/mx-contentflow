package org.mxframework.contentflow.representation.pmc.subject;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author mx
 */
@Data
public class SubjectBase {

    private String subjectId;
    private String versionId;
    private String name;
    private String description;
    private Integer rank;

    public SubjectBase(SubjectBase subjectBase) {
        BeanUtils.copyProperties(subjectBase, this);
    }

    public SubjectBase() {
    }
}
