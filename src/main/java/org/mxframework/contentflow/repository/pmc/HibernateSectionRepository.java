package org.mxframework.contentflow.repository.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.section.Section;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionId;
import org.mxframework.contentflow.domain.model.pmc.project.section.SectionRepository;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.util.UUIDUtil;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernateSectionRepository implements SectionRepository {
    private final static String SECTION_ID_PREFIX = "pmc-section-";

    private final SectionJpaRepository sectionJpaRepository;

    public HibernateSectionRepository(SectionJpaRepository sectionJpaRepository) {
        this.sectionJpaRepository = sectionJpaRepository;
    }

    @Override
    public SectionId nextIdentity() {
        return new SectionId(SECTION_ID_PREFIX + UUIDUtil.getUuidPartOne());
    }

    @Override
    public Section sectionOfSectionId(SectionId sectionId) {
        return sectionJpaRepository.findBySectionId(sectionId);
    }

    @Override
    public Section sectionOfVersionIdAndName(VersionId versionId, String name) {
        return sectionJpaRepository.findByVersionIdAndName(versionId, name);
    }

    @Override
    public Collection<Section> sectionsOfVersionId(VersionId versionId) {
        return sectionJpaRepository.findAllByVersionId(versionId);
    }

    @Override
    public void add(Section section) {
        sectionJpaRepository.save(section);
    }

    @Override
    public void addAll(Collection<Section> sections) {
        sectionJpaRepository.saveAll(sections);
    }

    @Override
    public void remove(Section section) {
        sectionJpaRepository.delete(section);
    }

    @Override
    public void removeAll(Collection<Section> sections) {
        sectionJpaRepository.deleteAll(sections);
    }
}
