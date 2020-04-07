package org.mxframework.contentflow.service.pmc.translator;

import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.representation.pmc.version.VersionBase;
import org.mxframework.contentflow.representation.pmc.version.VersionModifyForm;
import org.mxframework.contentflow.representation.pmc.version.vo.VersionBaseVO;
import org.mxframework.contentflow.representation.pmc.version.vo.VersionItemVO;
import org.mxframework.contentflow.representation.pmc.version.vo.VersionManageVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class VersionTranslator {

    private VersionBase convertToBase(Version version) {
        VersionBase versionBase = new VersionBase();
        versionBase.setVersionId(version.versionId().id());
        versionBase.setName(version.name());
        versionBase.setDescription(version.description());
        versionBase.setProjectId(version.projectId().id());
        versionBase.setRank(version.rank());
        return versionBase;
    }

    public VersionBaseVO convertToBaseVo(Version version) {
        return new VersionBaseVO(convertToBase(version));
    }

    public VersionItemVO convertToItemVo(Version version) {
        return new VersionItemVO(convertToBase(version));
    }

    public List<VersionItemVO> convertToItemVo(List<Version> versionList) {
        List<VersionItemVO> versionItemVos = new ArrayList<>(versionList.size());
        versionList.forEach(version -> versionItemVos.add(this.convertToItemVo(version)));
        return versionItemVos;
    }

    public List<VersionManageVO> convertToManageVO(List<Version> versionList) {
        List<VersionManageVO> versionManageVoList = new ArrayList<>(versionList.size());
        versionList.forEach(version -> versionManageVoList.add(new VersionManageVO(version.versionId().id(), version.name(), version.description())));
        return versionManageVoList;
    }

    public VersionModifyForm convertToModifyForm(Version version) {
        return new VersionModifyForm(version.name(), version.description());
    }

}
