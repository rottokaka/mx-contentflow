package org.mxframework.contentflow.domain.model.pmc.axis;

import org.mxframework.contentflow.domain.model.pmc.product.Product;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;

import java.util.Collection;

/**
 * @author mx
 */
public interface AxisRepository {

    Axis axisOfProductAndProjectId(Product product, ProjectId projectId);

    Collection<Axis> axesOfProduct(Product product);

    Collection<Axis> axesOfVersionIdAndSubjectIdAndSectionId(VersionId versionId, SubjectId subjectId, SectionId sectionId);

    Collection<Axis> axesOfProjectId(ProjectId projectId);

    Collection<Axis> axesOfVersionId(VersionId versionId);

    Collection<Axis> axesOfSubjectId(SubjectId subjectId);

    Collection<Axis> axesOfSectionId(SectionId sectionId);

    void add(Axis axis);

    void addAll(Collection<Axis> axes);

    void remove(Axis axis);

    void removeAll(Collection<Axis> axes);

}
