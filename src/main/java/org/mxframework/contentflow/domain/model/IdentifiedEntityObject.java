package org.mxframework.contentflow.domain.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author mx
 */
@MappedSuperclass
public class IdentifiedEntityObject extends IdentifiedDomainObject {
    private static final long serialVersionUID = 1L;

    @CreationTimestamp
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date gmtCreate;

    @UpdateTimestamp
    @Column(columnDefinition = "DATETIME COMMENT '更新时间'")
    private Date gmtModified;

    public IdentifiedEntityObject(Timestamp gmtCreate, Timestamp gmtModified) {
        this();
        this.setGmtCreate(gmtCreate);
        this.setGmtModified(gmtModified);
    }

    protected IdentifiedEntityObject() {
        super();
    }

    public Date gmtCreate() {
        return this.gmtCreate;
    }

    public Date gmtModified() {
        return this.gmtModified;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "IdentifiedEntityObject{" +
                "gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
