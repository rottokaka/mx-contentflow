package org.mxframework.contentflow.domain.model.pmc.project.section;

import org.mxframework.contentflow.domain.model.IdentifiedValueObject;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Section: 类型
 *
 * @author mx
 */
@Entity
public class Section extends IdentifiedValueObject {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "类型ID不能为空")
    @Embedded
    private SectionId sectionId;

    @NotNull(message = "类型名称不能为空")
    @Column
    private String name;

    @Column
    private String description;

    @NotNull(message = "版本ID不能为空")
    @Embedded
    private VersionId versionId;

    @Column
    private Integer rank;

    public Section(SectionId sectionId
            , VersionId versionId
            , String name
            , String description
            , Integer rank) {
        this();
        this.setSectionId(sectionId);
        this.setVersionId(versionId);
        this.setName(name);
        this.setDescription(description);
        this.setRank(rank);
    }

    public Section(SectionId sectionId, VersionId versionId, String name) {
        this(sectionId, versionId, name, null, null);
    }

    protected Section() {
        super();
    }

    public SectionId sectionId() {
        return this.sectionId;
    }

    public VersionId versionId() {
        return this.versionId;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public Integer rank() {
        return this.rank;
    }

    public void setSectionId(SectionId sectionId) {
        this.sectionId = sectionId;
    }

    public void setVersionId(VersionId versionId) {
        this.versionId = versionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        if (description != null && !"".equals(description.trim())) {
            this.description = description;
        } else {
            this.description = this.name();
        }
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", versionId=" + versionId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rank=" + rank +
                '}';
    }
}
