package org.mxframework.contentflow.domain.model.vfs;

import org.mxframework.contentflow.domain.model.AbstractValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mx
 */
@Embeddable
public class VirtualFileId extends AbstractValueObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "virtual_file_id_id")
    private String id;

    public VirtualFileId(String id) {
        this();
        this.setId(id);
    }

    protected VirtualFileId() {
        super();
    }

    public String id() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
