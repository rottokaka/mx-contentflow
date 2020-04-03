package org.mxframework.contentflow.repository.pmc.axis;

import org.mxframework.contentflow.domain.model.pmc.axis.Axis;
import org.mxframework.contentflow.domain.model.pmc.axis.AxisRepository;
import org.mxframework.contentflow.domain.model.pmc.product.Product;
import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernateAxisRepository implements AxisRepository {

    @Autowired
    private AxisJpaRepository axisJpaRepository;

    @Override
    public Axis axisOfProductAndProjectId(Product product, ProjectId projectId) {
        return axisJpaRepository.findByProductAndProjectId(product, projectId);
    }

    @Override
    public Collection<Axis> axesOfProduct(Product product) {
        return axisJpaRepository.findAllByProduct(product);
    }

    @Override
    public Collection<Axis> axesOfVersionIdAndSubjectIdAndSectionId(VersionId versionId, SubjectId subjectId, SectionId sectionId) {
        return axisJpaRepository.findAllBySectionIdAndSubjectIdAndVersionId(sectionId, subjectId, versionId);
    }

    @Override
    public Collection<Axis> axesOfProjectId(ProjectId projectId) {
        return axisJpaRepository.findAllByProjectId(projectId);
    }

    @Override
    public Collection<Axis> axesOfVersionId(VersionId versionId) {
        return axisJpaRepository.findAllByVersionId(versionId);
    }

    @Override
    public Collection<Axis> axesOfSubjectId(SubjectId subjectId) {
        return axisJpaRepository.findAllBySubjectId(subjectId);
    }

    @Override
    public Collection<Axis> axesOfSectionId(SectionId sectionId) {
        return axisJpaRepository.findAllBySectionId(sectionId);
    }

    @Override
    public void add(Axis axis) {
        axisJpaRepository.save(axis);
    }

    @Override
    public void addAll(Collection<Axis> axes) {
        axisJpaRepository.saveAll(axes);
    }

    @Override
    public void remove(Axis axis) {
        axisJpaRepository.delete(axis);
    }

    @Override
    public void removeAll(Collection<Axis> axes) {
        axisJpaRepository.deleteAll(axes);
    }
}
