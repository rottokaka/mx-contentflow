package org.mxframework.contentflow.repository.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.subject.Subject;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * SubjectRepository: 主题仓库
 *
 * @author mx
 */
@Repository
public interface SubjectJpaRepository extends JpaRepository<Subject, Long> {

    Subject findBySubjectId(SubjectId subjectId);

    Collection<Subject> findAllByVersionId(VersionId versionId);
}
