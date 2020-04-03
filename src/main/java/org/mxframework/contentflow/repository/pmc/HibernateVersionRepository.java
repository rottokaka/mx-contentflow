package org.mxframework.contentflow.repository.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionRepository;
import org.mxframework.contentflow.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mx
 */
@Component
public class HibernateVersionRepository implements VersionRepository {

    private final static String VERSION_ID_PREFIX = "pmc-project-";

    @Autowired
    private VersionJpaRepository versionJpaRepository;

    @Override
    public VersionId nextIdentity() {
        return new VersionId(VERSION_ID_PREFIX + UUIDUtil.getUuidPartOne());
    }

    @Override
    public Version versionOfVersionId(VersionId versionId) {
        return versionJpaRepository.findByVersionId(versionId);
    }

    @Override
    public Collection<Version> versionsOfProjectId(ProjectId projectId) {
        return versionJpaRepository.findAllByProjectId(projectId);
    }

    @Override
    public void add(Version version) {
        versionJpaRepository.save(version);
    }

    @Override
    public void remove(Version version) {
        versionJpaRepository.delete(version);
    }

    @Override
    public void removeAll(Collection<Version> versions) {
        versionJpaRepository.deleteAll(versions);
    }
}
