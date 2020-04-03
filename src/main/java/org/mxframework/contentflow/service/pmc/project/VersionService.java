package org.mxframework.contentflow.service.pmc.project;

import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * VersionService: 版本接口
 *
 * @author mx
 */
@Service
public class VersionService {

    @Autowired
    private VersionRepository versionRepository;

    public VersionId nextIdentity() {
        return versionRepository.nextIdentity();
    }

    public Version getByVersionId(VersionId versionId) {
        return versionRepository.versionOfVersionId(versionId);
    }

    public Version getDefaultByProjectId(ProjectId projectId) {
        List<Version> versions = (List<Version>) versionRepository.versionsOfProjectId(projectId);
        return versions.get(0);
    }

    public List<Version> listByProjectId(ProjectId projectId) {
        return (List<Version>) versionRepository.versionsOfProjectId(projectId);
    }

    public void add(Version version) {
        versionRepository.add(version);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void delete(Version version) {
        versionRepository.remove(version);
    }

    public void deleteAll(List<Version> versionList) {
        versionRepository.removeAll(versionList);
    }
}
