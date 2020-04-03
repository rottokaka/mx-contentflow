package org.mxframework.contentflow.domain.model.pmc.project.section;

import org.mxframework.contentflow.domain.model.AbstractValueObject;
import org.mxframework.contentflow.exception.ProjectException;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mx
 */
@Embeddable
public class SectionId extends AbstractValueObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "section_id_id")
    private String id;

    public SectionId(String id) {
        this();
        this.setId(id);
    }

    protected SectionId() {
        super();
    }

    public String id() {
        return this.id;
    }

    public void setId(String id) {
        if (id != null) {
            this.id = id;
        } else {
            throw new ProjectException("类型ID不能为空");
        }
    }
}
