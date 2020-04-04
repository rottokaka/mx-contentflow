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

    public Section getByVersionIdAndName(VersionId versionId, String name) {
        return sectionRepository.sectionOfVersionIdAndName(versionId, name);
    }

    public List<Section> listByVersionId(VersionId versinId) {
        return (List<Section>) sectionRepository.sectionsOfVersionId(versinId);
    }

    public void save(Section section) {
        sectionRepository.add(section);
    }

    public void update(Section section) {
        sectionRepository.add(section);
    }

    public void delete(Section section) {
        sectionRepository.remove(section);
    }

    public void deleteAll(List<Section> sectionList) {
        sectionRepository.removeAll(sectionList);
    }

}
