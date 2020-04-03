package org.mxframework.contentflow.application.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.representation.pmc.version.VersionCreateForm;
import org.mxframework.contentflow.representation.pmc.version.VersionModifyForm;
import org.mxframework.contentflow.representation.pmc.version.vo.VersionItemVO;
import org.mxframework.contentflow.representation.pmc.version.vo.VersionManageVO;
import org.mxframework.contentflow.service.pmc.project.VersionService;
import org.mxframework.contentflow.service.pmc.translator.VersionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class VersionApplicationService {

    @Autowired
    private VersionService versionService;
    @Autowired
    private VersionTranslator versionTranslator;

    @Autowired
    private AxisApplicationService axisApplicationService;
    @Autowired
    private SubjectApplicationService subjectApplicationService;
    @Autowired
    private SectionApplicationService sectionApplicationService;

    public VersionId nextIdentity() {
        return versionService.nextIdentity();
    }

    public Version getByVersionId(String versionId) {
        return versionService.getByVersionId(new VersionId(versionId));
    }

    public VersionItemVO getTtemVoByVersionId(String versionId) {
        return versionTranslator.convertToItemVo(this.getByVersionId(versionId));
    }

    public Version getDefaultByProjectId(String projectId) {
        return versionService.getDefaultByProjectId(new ProjectId(projectId));
    }

    public VersionModifyForm getModifyFormByVersionId(String versionId) {
        Version version = versionService.getByVersionId(new VersionId(versionId));
        if (version != null) {
            return versionTranslator.convertToModifyForm(version);
        } else {
            return null;
        }
    }


    public List<Version> listByProjectId(String projectId) {
        return versionService.listByProjectId(new ProjectId(projectId));
    }


    public List<VersionItemVO> listItemVoByProjectId(String projectId) {
        List<Version> versionList = versionService.listByProjectId(new ProjectId(projectId));
        if (versionList != null && versionList.size() > 0) {
            return versionTranslator.convertToItemVo(versionList);
        } else {
            return null;
        }
    }

    public List<VersionManageVO> listManageVoByProjectId(String projectId) {
        List<Version> versionList = versionService.listByProjectId(new ProjectId(projectId));
        if (versionList != null && versionList.size() > 0) {
            return versionTranslator.convertToManageVO(versionList);
        } else {
            return null;
        }
    }

    public void add(Version version) {
        versionService.add(version);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Version post(VersionCreateForm versionCreateForm) {
        VersionId versionId = versionService.nextIdentity();
        ProjectId projectId = new ProjectId(versionCreateForm.getProjectId());
        Version version = new Version(versionId, projectId, versionCreateForm.getName());
        version.setDescription(versionCreateForm.getDescription());
        version.setRank(versionService.listByProjectId(projectId).size() + 1);
        versionService.add(version);
        return versionService.getByVersionId(versionId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Version updateByVersionId(VersionId versionId, VersionModifyForm versionModifyForm) {
        Version ofVersionId = versionService.getByVersionId(versionId);
        ofVersionId.setName(versionModifyForm.getName());
        ofVersionId.setDescription(versionModifyForm.getDescription());
        versionService.add(ofVersionId);
        return ofVersionId;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteByVersionId(String versionId) {
        // 关联版本
        Version version = versionService.getByVersionId(new VersionId(versionId));
        // 1. 关联坐标
        axisApplicationService.deleteAllByVersionId(versionId);
        // 2. 关联专题
        subjectApplicationService.deleteAllByVersionId(versionId);
        // 3. 管理类型
        sectionApplicationService.deleteAllByVersionId(versionId);
        versionService.delete(version);
    }

    public void deleteAllByProjectId(String projectId) {
        // 关联版本
        List<Version> versionList = versionService.listByProjectId(new ProjectId(projectId));
        // 1. 关联坐标
        List<String> versionIds = new ArrayList<>(versionList.size());
        versionList.forEach(version -> versionIds.add(version.versionId().id()));
        axisApplicationService.deleteAllByVersionIds(versionIds);
        // 关联专题
        subjectApplicationService.deleteAllByVersionIds(versionIds);
        // 关联类型
        sectionApplicationService.deleteAllByVersionIds(versionIds);
        versionService.deleteAll(versionList);
    }

}
