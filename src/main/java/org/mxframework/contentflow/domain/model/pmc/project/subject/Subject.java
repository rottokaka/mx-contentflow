package org.mxframework.contentflow.domain.model.pmc.project.subject;

import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Subject: 主题
 *
 * @author mx
 */
@Entity
public class Subject extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "专题ID不能为空")
    @Embedded
    private SubjectId subjectId;

    @NotNull(message = "专题名称不能为空")
    @Column
    private String name;

    @Column
    private String description;

    /**
     * 专题可单独存在，允许版本ID为空
     */
    @Embedded
    private VersionId versionId;
    @Column
    private Integer rank;

    public Subject(SubjectId subjectId
            , String name
            , String description
            , Integer rank
            , VersionId versionId) {
        this();
        this.setSubjectId(subjectId);
        this.setName(name);
        this.setDescription(description);
        this.setRank(rank);
        this.setVersionId(versionId);
    }

    public Subject(SubjectId subjectId, String name) {
        this(subjectId, name, null, null, null);
    }

    protected Subject() {
        super();
    }

    public SubjectId subjectId() {
        return this.subjectId;
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

    public VersionId versionId() {
        return this.versionId;
    }

    public void setSubjectId(SubjectId subjectId) {
        this.subjectId = subjectId;
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

    public void setVersionId(VersionId versionId) {
        this.versionId = versionId;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rank=" + rank +
                ", versionId=" + versionId +
                '}';
    }
}
