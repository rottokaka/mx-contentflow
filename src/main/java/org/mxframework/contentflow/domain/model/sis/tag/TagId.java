package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.AbstractValueObject;
import org.mxframework.contentflow.exception.TagException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author mx
 */
@Embeddable
public class TagId extends AbstractValueObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "tag_id_id", nullable = false, columnDefinition = "VARCHAR(64) COMMENT '标签ID'")
    private String id;

    public TagId(String id) {
        this.setId(id);
    }

    protected TagId() {
        super();
    }

    public String id() {
        return this.id;
    }

    public void setId(String id) {
        if (id.isEmpty()) {
            throw new TagException("标签ID不能为空");
        } else {
            this.id = id;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof TagId) {
            TagId other = (TagId) obj;
            return other.id.equals(this.id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "TagId{" +
                "id='" + id + '\'' +
                '}';
    }
}
