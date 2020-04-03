package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.sis.identity.Creator;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Tag: 标签
 *
 * @author mx
 */
@Entity
public class Tag extends IdentifiedEntityObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Embedded
    private TagId tagId;

    @Embedded
    @NotNull(message = "创建者不能为空")
    private Creator creator;

    @Size(max = 20, message = "名称字符串长度不能超过‘20’")
    @Column(unique = true, columnDefinition = "VARCHAR(20) COMMENT '名称'")
    private String name;

    @Size(max = 300, message = "描述字符串长度不能超过‘300’")
    @Column(columnDefinition = "VARCHAR(300) COMMENT '描述'")
    private String description;

    @NotNull(message = "性质不能为空")
    @Column(nullable = false, columnDefinition = "TINYINT(1) UNSIGNED COMMENT '性质：0->公共标签；1->私人标签；2->标记标签；3->管理标签'")
    private Integer property;

    // constructors~
    // =================================================================================================================

    public Tag(TagId tagId, String name, Integer property, Creator creator, String description) {
        super();
        this.setTagId(tagId);
        this.setName(name);
        this.setProperty(property);
        this.setCreator(creator);
        this.setDescription(description);
    }

    public Tag(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public Tag(TagId tagId) {
        this(tagId, null, null, null, null);
    }

    protected Tag() {
        super();
    }

    public TagId tagId() {
        return this.tagId;
    }

    public void setTagId(TagId tagId) {
        this.tagId = tagId;
    }

    public String name() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer property() {
        return this.property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Creator creator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public String description() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Tag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", property=" + property +
                ", creator=" + creator +
                ", description='" + description + '\'' +
                '}';
    }
}
