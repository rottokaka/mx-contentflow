package org.mxframework.contentflow.service.pmc.translator;

import org.mxframework.contentflow.domain.model.pmc.project.section.Section;
import org.mxframework.contentflow.representation.pmc.section.SectionBase;
import org.mxframework.contentflow.representation.pmc.section.vo.SectionItemVO;
import org.mxframework.contentflow.representation.pmc.section.vo.SectionManageVO;
import org.mxframework.contentflow.representation.pmc.section.form.SectionModifyForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class SectionTranslator {

    private SectionBase convertToBase(Section section) {
        SectionBase sectionBase = new SectionBase();
        sectionBase.setSectionId(section.sectionId().id());
        sectionBase.setName(section.name());
        sectionBase.setDescription(section.description());
        sectionBase.setVersionId(section.versionId().id());
        sectionBase.setRank(section.rank());
        return sectionBase;
    }

    public List<SectionItemVO> convertToItemVo(List<Section> sectionList) {
        List<SectionItemVO> sectionItemVoList = new ArrayList<>(sectionList.size());
        sectionList.forEach(section -> sectionItemVoList.add(new SectionItemVO(convertToBase(section))));
        return sectionItemVoList;
    }

    public List<SectionManageVO> convertToManageVo(List<Section> sectionList) {
        List<SectionManageVO> sectionManageVoList = new ArrayList<>(sectionList.size());
        sectionList.forEach(section -> sectionManageVoList.add(new SectionManageVO(convertToBase(section))));
        return sectionManageVoList;
    }

    public SectionModifyForm convertToModifyForm(Section section) {
        return new SectionModifyForm(section.name(), section.description());
    }
}
