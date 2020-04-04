package org.mxframework.contentflow.service.pmc.project;

import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.Subject;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectRepository;
import org.mxframework.contentflow.representation.pmc.subject.vo.SubjectBaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SubjectService: 专题服务
 *
 * @author mx
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectId nextIdentity() {
        return subjectRepository.nextIdentity();
    }

    public Subject getBySubjectId(SubjectId subjectId) {
        return subjectRepository.subjectOfSubjectId(subjectId);
    }

    public List<Subject> listByVersionId(VersionId versionId) {
        return (List<Subject>) subjectRepository.subjectsOfVersionId(versionId);
    }

    public void save(Subject subject) {
        subjectRepository.add(subject);
    }

    public void update(Subject subject) {
        subjectRepository.add(subject);
    }

    public void delete(Subject subject) {
        subjectRepository.remove(subject);
    }

    public void deleteAll(List<Subject> subjectList) {
        subjectRepository.removeAll(subjectList);
    }

}
