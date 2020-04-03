package org.mxframework.contentflow.application.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.section.Section;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.representation.pmc.section.form.SectionCreateForm;
import org.mxframework.contentflow.representation.pmc.section.form.SectionModifyForm;
import org.mxframework.contentflow.representation.pmc.section.vo.SectionItemVO;
import org.mxframework.contentflow.representation.pmc.section.vo.SectionManageVO;
import org.mxframework.contentflow.service.pmc.project.SectionService;
import org.mxframework.contentflow.service.pmc.project.SubjectService;
import org.mxframework.contentflow.service.pmc.translator.SectionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class SectionApplicationService {

    @Autowired
    private AxisApplicationService axisApplicationService;

    @Autowired
    private SectionService sectionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SectionTranslator sectionTranslator;

    public SectionModifyForm getModifyFormBySectionId(String sectionId) {
        Section bySectionId = sectionService.getBySectionId(new SectionId(sectionId));
        if (sectionId != null) {
            return sectionTranslator.convertToModifyForm(bySectionId);
        } else {
            return null;
        }
    }

    public List<SectionItemVO> listItemVoByVersionId(String versionId) {
        List<Section> sectionList = sectionService.listByVersionId(new VersionId(versionId));
        if (!sectionList.isEmpty()) {
            return sectionTranslator.convertToItemVo(sectionList);
        } else {
            return null;
        }
    }

    public List<SectionManageVO> listManageVoByVersionId(String versionId) {
        List<Section> sectionList = sectionService.listByVersionId(new VersionId(versionId));
        if (sectionList != null && sectionList.size() > 0) {
            return sectionTranslator.convertToManageVo(sectionList);
        } else {
            return null;
        }
    }


    @Transactional(rollbackFor = {Exception.class})
    public Section post(SectionCreateForm sectionCreateForm) {
        SectionId sectionId = sectionService.nextIdentity();
        VersionId versionId = new VersionId(sectionCreateForm.getVersionId());
        Section section = new Section(sectionId, versionId, sectionCreateForm.getName());
        section.setRank(sectionService.listByVersionId(versionId).size() + 1);
        sectionService.add(section);
        return sectionService.getBySectionId(sectionId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteBySectionId(String sectionId) {
        Section section = sectionService.getBySectionId(new SectionId(sectionId));
        // 关联坐标
        axisApplicationService.deleteAllBySectionId(sectionId);
        sectionService.delete(section);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByVersionId(String versionId) {
        // 关联类型
        List<Section> sectionList = sectionService.listByVersionId(new VersionId(versionId));
        List<String> sectionIds = new ArrayList<>(sectionList.size());
        sectionList.forEach(section -> sectionIds.add(section.sectionId().id()));
        // 关联坐标
        axisApplicationService.deleteAllBySectionIds(sectionIds);
        sectionService.deleteAll(sectionList);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByVersionIds(List<String> versionIds) {
        // 关联类型
        List<Section> sectionList = new ArrayList<>(versionIds.size());
        versionIds.forEach(versionId -> sectionList.addAll(sectionService.listByVersionId(new VersionId(versionId))));
        // 关联坐标
        List<String> sectionIds = new ArrayList<>(sectionList.size());
        sectionList.forEach(section -> sectionIds.add(section.sectionId().id()));
        axisApplicationService.deleteAllBySectionIds(sectionIds);
        sectionService.deleteAll(sectionList);
    }

}
