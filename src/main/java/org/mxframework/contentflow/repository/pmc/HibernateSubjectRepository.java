package org.mxframework.contentflow.repository.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.subject.Subject;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectRepository;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.util.UUIDUtil;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernateSubjectRepository implements SubjectRepository {

    private final static String SUBJECT_ID_PREFIX = "pmc-subject-";

    private final SubjectJpaRepository subjectJpaRepository;

    public HibernateSubjectRepository(SubjectJpaRepository subjectJpaRepository) {
        this.subjectJpaRepository = subjectJpaRepository;
    }

    @Override
    public SubjectId nextIdentity() {
        return new SubjectId(SUBJECT_ID_PREFIX + UUIDUtil.getUuidPartOne());
    }

    @Override
    public Subject subjectOfSubjectId(SubjectId subjectId) {
        return subjectJpaRepository.findBySubjectId(subjectId);
    }

    @Override
    public Collection<Subject> subjectsOfVersionId(VersionId versionId) {
        return subjectJpaRepository.findAllByVersionId(versionId);
    }

    @Override
    public void add(Subject subject) {
        subjectJpaRepository.save(subject);
    }

    @Override
    public void addAll(Collection<Subject> subjects) {
        subjectJpaRepository.saveAll(subjects);
    }

    @Override
    public void remove(Subject subject) {
        subjectJpaRepository.delete(subject);
    }

    @Override
    public void removeAll(Collection<Subject> subjects) {
        subjectJpaRepository.deleteAll(subjects);
    }
}
