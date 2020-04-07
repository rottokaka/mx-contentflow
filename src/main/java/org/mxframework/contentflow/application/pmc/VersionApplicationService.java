package org.mxframework.contentflow.application.pmc;

import org.mxframework.contentflow.domain.model.pmc.project.ProjectId;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.domain.model.pmc.project.version.VersionId;
import org.mxframework.contentflow.exception.MxException;
import org.mxframework.contentflow.representation.pmc.version.VersionCreateForm;
import org.mxframework.contentflow.representation.pmc.version.VersionModifyForm;
import org.mxframework.contentflow.representation.pmc.version.vo.VersionBaseVO;
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

    @Transactional(rollbackFor = {Exception.class})
    public void save(Version version) {
        versionService.save(version);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Version post(VersionCreateForm versionCreateForm) {
        Version byProjectIdAndName = versionService.getByProjectIdAndName(new ProjectId(versionCreateForm.getProjectId())
                , versionCreateForm.getName());
        if (byProjectIdAndName != null) {
            // TODO Exception Handle
            throw new MxException("版本已创建！");
        }
        VersionId versionId = versionService.nextIdentity();
        ProjectId projectId = new ProjectId(versionCreateForm.getProjectId());
        Version version = new Version(versionId, projectId, versionCreateForm.getName());
        version.setDescription(versionCreateForm.getDescription());
        version.setRank(versionService.listByProjectId(projectId).size() + 1);
        versionService.save(version);
        return versionService.getByVersionId(versionId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public VersionBaseVO putByVersionId(VersionId versionId, VersionModifyForm versionModifyForm) {
        Version byVersionId = versionService.getByVersionId(versionId);
        // 可修改的版本字段，名称及其描述，参考 VersionModifyForm
        // 1. 修改描述
        if (byVersionId.name().equals(versionModifyForm.getName())) {
            byVersionId.setDescription(versionModifyForm.getDescription());
        }
        // 2. 修改名称
        else {
            // 判断新的名称是否已经存在
            Version byProjectIdAndName = versionService.getByProjectIdAndName(byVersionId.projectId()
                    , versionModifyForm.getName());
            if (byProjectIdAndName != null) {
                // TODO Exception Handle
                throw new MxException("版本已存在！");
            } else {
                byVersionId.setName(versionModifyForm.getName());
                byVersionId.setDescription(versionModifyForm.getDescription());
            }
        }
        versionService.update(byVersionId);
        return versionTranslator.convertToBaseVo(byVersionId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteByVersionId(String versionId) {
        // 关联版本
        Version byVersionId = versionService.getByVersionId(new VersionId(versionId));
        if (byVersionId == null) {
            // TODO Exception Handle
            throw new MxException("版本已删除！");
        }
        List<Version> versionList = versionService.listByProjectId(byVersionId.projectId());
        if (versionList == null || versionList.size() <= 1) {
            throw new MxException("至少保留一个版本！");
        }
        // 1. 关联坐标
        axisApplicationService.deleteAllByVersionId(versionId);
        // 2. 关联专题
        subjectApplicationService.deleteAllByVersionId(versionId);
        // 3. 管理类型
        sectionApplicationService.deleteAllByVersionId(versionId);
        versionService.delete(byVersionId);
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
