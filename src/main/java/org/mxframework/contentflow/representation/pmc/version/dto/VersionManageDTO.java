package org.mxframework.contentflow.representation.pmc.version.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * VersionManageDTO: 版本管理[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VersionManageDTO extends VersionBaseDTO {

    /**
     * notAllowUpdate: 不允许更新
     */
    private Boolean notAllowUpdate;

    /**
     * notAllowDelete: 不允许删除
     * <p>
     * 默认
     * </p>
     */
    private Boolean notAllowDelete;

    // ~Constructors
    // =================================================================================================================

    /**
     * 默认构造器，部分属性初始化，允许修改和删除
     */
    public VersionManageDTO() {
        this.notAllowUpdate = false;
        this.notAllowDelete = false;
    }

    /**
     * 转换：版本转换为版本管理[DTO]
     * <p>单个</p>
     *
     * @param version 版本
     * @return <code>VersionManageDTO</code> 版本管理[DTO]
     */
    public static VersionManageDTO convert(Version version) {
        VersionManageDTO versionManageDTO = new VersionManageDTO();
        BeanUtils.copyProperties(version, versionManageDTO);
        return versionManageDTO;
    }

    /**
     * 转换：版本转换为版本管理[DTO]
     * <p>集合</p>
     *
     * @param versionList 版本集合
     * @return <code>VersionManageDTO</code> 版本管理[DTO]
     */
    public static List<VersionManageDTO> convert(List<Version> versionList) {
        List<VersionManageDTO> versionManageDtoList = new ArrayList<>(versionList.size());
        versionList.forEach(version -> versionManageDtoList.add(VersionManageDTO.convert(version)));
        return versionManageDtoList;
    }
}
