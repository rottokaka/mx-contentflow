package org.mxframework.contentflow.domain.model.pmc.project.section;

import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;

import java.util.Collection;

/**
 * @author mx
 */
public interface SectionRepository {

    SectionId nextIdentity();

    Section sectionOfSectionId(SectionId sectionId);

    Section sectionOfVersionIdAndName(VersionId versionId, String name);

    Collection<Section> sectionsOfVersionId(VersionId versionId);

    void add(Section section);

    void addAll(Collection<Section> sections);

    void remove(Section section);

    void removeAll(Collection<Section> sections);
}
