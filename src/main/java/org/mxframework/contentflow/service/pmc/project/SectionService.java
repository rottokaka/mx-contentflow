package org.mxframework.contentflow.service.pmc.project;

import org.mxframework.contentflow.domain.model.pmc.project.section.Section;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionRepository;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SectionService: 类型接口
 *
 * @author mx
 */
@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public SectionId nextIdentity() {
        return sectionRepository.nextIdentity();
    }

    public Section getBySectionId(SectionId sectionId) {
        return sectionRepository.sectionOfSectionId(sectionId);
    }

    public List<Section> listByVersionId(VersionId versinId) {
        return (List<Section>) sectionRepository.sectionsOfVersionId(versinId);
    }

    public void add(Section section) {
        sectionRepository.add(section);
    }

    public void addAll(List<Section> sections) {
        sectionRepository.addAll(sections);
    }

    public void delete(Section section) {
        sectionRepository.remove(section);
    }

    public void deleteAll(List<Section> sectionList) {
        sectionRepository.removeAll(sectionList);
    }

}
