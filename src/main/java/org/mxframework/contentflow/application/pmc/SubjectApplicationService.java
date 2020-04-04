package org.mxframework.contentflow.application.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.subject.Subject;
import org.mxframework.contentflow.domain.model.pmc.project.subject.SubjectId;
import org.mxframework.contentflow.representation.pmc.subject.form.SubjectCreateForm;
import org.mxframework.contentflow.representation.pmc.subject.vo.SubjectBaseVO;
import org.mxframework.contentflow.representation.pmc.subject.vo.SubjectItemVO;
import org.mxframework.contentflow.representation.pmc.subject.vo.SubjectManageVO;
import org.mxframework.contentflow.representation.pmc.subject.form.SubjectModifyForm;
import org.mxframework.contentflow.service.pmc.project.SubjectService;
import org.mxframework.contentflow.service.pmc.translator.SubjectTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class SubjectApplicationService {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectTranslator subjectTranslator;

    @Autowired
    private AxisApplicationService axisApplicationService;
    @Autowired
    private SectionApplicationService sectionApplicationService;

    public SubjectModifyForm getModifyFormBySubjectId(String subjectId) {
        Subject subject = subjectService.getBySubjectId(new SubjectId(subjectId));
        if (subject != null) {
            return subjectTranslator.convertToModifyVo(subject);
        } else {
            return null;
        }
    }

    public List<SubjectItemVO> listItemVoByVersionId(String versionId) {
        List<Subject> subjects = subjectService.listByVersionId(new VersionId(versionId));
        if (subjects != null && subjects.size() > 0) {
            return subjectTranslator.convertToItemVo(subjects);
        } else {
            return null;
        }
    }

    public List<SubjectManageVO> listManageVoByVersionId(String versionId) {
        List<Subject> subjectList = subjectService.listByVersionId(new VersionId(versionId));
        if (subjectList != null && subjectList.size() > 0) {
            return subjectTranslator.convertToManageVo(subjectList);
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public Subject post(SubjectCreateForm subjectCreateForm) {
        SubjectId subjectId = subjectService.nextIdentity();
        Subject subject = new Subject(subjectId, subjectCreateForm.getName());
        subject.setDescription(subjectCreateForm.getDescription());
        // 专题允许单独存在，如果版本ID不为空，则绑定
        String versionId = subjectCreateForm.getVersionId();
        if (!"".equals(versionId)) {
            subject.setVersionId(new VersionId(versionId));
            subject.setRank(subjectService.listByVersionId(new VersionId(versionId)).size() + 1);
        }
        subjectService.save(subject);
        return subjectService.getBySubjectId(subjectId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public SubjectBaseVO putBySubjectId(String subjectId, SubjectModifyForm subjectModifyForm) {
        Subject bySubjectId = subjectService.getBySubjectId(new SubjectId(subjectId));
        bySubjectId.setName(subjectModifyForm.getName());
        bySubjectId.setDescription(subjectModifyForm.getDescription());
        subjectService.update(bySubjectId);
        return subjectTranslator.convertToBaseVo(subjectService.getBySubjectId(new SubjectId(subjectId)));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteBySubjectId(String subjectId) {
        // 关联坐标
        axisApplicationService.deleteAllBySubjectId(subjectId);
        Subject subject = subjectService.getBySubjectId(new SubjectId(subjectId));
        subjectService.delete(subject);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByVersionId(String versionId) {
        // 关联专题
        List<Subject> subjectList = subjectService.listByVersionId(new VersionId(versionId));
        // 关联坐标
        List<String> subjectIds = new ArrayList<>(subjectList.size());
        subjectList.forEach(subject -> subjectIds.add(subject.subjectId().id()));
        axisApplicationService.deleteAllBySubjectIds(subjectIds);
        subjectService.deleteAll(subjectList);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByVersionIds(List<String> versionIds) {
        // 关联专题
        List<Subject> subjectList = new ArrayList<>(versionIds.size());
        for (String versionId : versionIds) {
            subjectList.addAll(subjectService.listByVersionId(new VersionId(versionId)));
        }
        // 关联坐标
        List<String> subjectIds = new ArrayList<>(subjectList.size());
        subjectList.forEach(subject -> subjectIds.add(subject.subjectId().id()));
        axisApplicationService.deleteAllBySubjectIds(subjectIds);
        subjectService.deleteAll(subjectList);
    }

}
