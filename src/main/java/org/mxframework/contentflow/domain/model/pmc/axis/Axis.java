package org.mxframework.contentflow.domain.model.pmc.axis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mxframework.contentflow.constant.pmc.AxisConstant;
import org.mxframework.contentflow.domain.model.IdentifiedValueObject;
import org.mxframework.contentflow.domain.model.pmc.product.Product;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Axis: 坐标
 *
 * <p>
 * 前提：产品唯一确定：productType,productId;
 * 唯一确定[1]: projectId,product 一个产品只存在一个项目；
 * 坐标（versionId, subjectId, sectionId）等都是辅助参考。
 * </p>
 *
 * @author mx
 */
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Axis extends IdentifiedValueObject {
    private static final long serialVersionUID = 1L;

    private final static String AXIS_FILLER_ID = AxisConstant.AXIS_DEFAULT_ID;

    @NotNull
    @Embedded
    private Product product;

    @NotNull
    @Embedded
    private ProjectId projectId;

    @Embedded
    private VersionId versionId;

    @Embedded
    private SubjectId subjectId;

    @Embedded
    private SectionId sectionId;

    public Axis(Product product
            , ProjectId projectId
            , VersionId versionId
            , SubjectId subjectId
            , SectionId sectionId) {
        this();
        this.setProduct(product);
        this.setProjectId(projectId);
        this.setVersionId(versionId);
        this.setSubjectId(subjectId);
        this.setSectionId(sectionId);
    }

    public Axis(Product product, ProjectId projectId, VersionId versionId, SubjectId subjectId) {
        this(product, projectId, versionId, subjectId, new SectionId(AXIS_FILLER_ID));
    }

    public Axis(Product product, ProjectId projectId, VersionId versionId) {
        this(product, projectId, versionId, new SubjectId(AXIS_FILLER_ID));
    }

    public Axis(Product product, ProjectId projectId) {
        this(product, projectId, new VersionId(AXIS_FILLER_ID));
    }

    public Axis(ProjectId projectId, VersionId versionId, SubjectId subjectId, SectionId sectionId) {
        this(null, projectId, versionId, subjectId, sectionId);
    }

    public Axis(ProjectId projectId, VersionId versionId, SubjectId subjectId) {
        this(projectId, versionId, subjectId, new SectionId(AXIS_FILLER_ID));
    }

    public Axis(ProjectId projectId, VersionId versionId) {
        this(projectId, versionId, new SubjectId(AXIS_FILLER_ID));
    }

    public Axis(ProjectId projectId) {
        this(projectId, new VersionId(AXIS_FILLER_ID));
    }

    protected Axis() {
        super();
    }

    public Product product() {
        return this.product;
    }

    public SectionId sectionId() {
        return this.sectionId;
    }

    public SubjectId subjectId() {
        return this.subjectId;
    }

    public VersionId versionId() {
        return this.versionId;
    }

    public ProjectId projectId() {
        return this.projectId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSectionId(SectionId sectionId) {
        if (sectionId != null) {
            this.sectionId = sectionId;
        } else {
            this.sectionId = new SectionId(AXIS_FILLER_ID);
        }
    }

    public void setSubjectId(SubjectId subjectId) {
        if (subjectId != null) {
            this.subjectId = subjectId;
        } else {
            this.subjectId = new SubjectId(AXIS_FILLER_ID);
        }
    }

    public void setVersionId(VersionId versionId) {
        if (versionId != null) {
            this.versionId = versionId;
        } else {
            this.versionId = new VersionId(AXIS_FILLER_ID);
        }
    }

    public void setProjectId(ProjectId projectId) {
        if (projectId != null) {
            this.projectId = projectId;
        } else {
            throw new IllegalArgumentException("项目ID不能为空");
        }
    }

    @Override
    public String toString() {
        return "Axis{" +
                "product=" + product +
                ", sectionId=" + sectionId +
                ", subjectId=" + subjectId +
                ", versionId=" + versionId +
                ", projectId=" + projectId +
                '}';
    }
}
