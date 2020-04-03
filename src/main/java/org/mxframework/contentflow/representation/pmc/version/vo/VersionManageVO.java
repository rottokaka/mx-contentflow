package org.mxframework.contentflow.representation.pmc.version.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mxframework.contentflow.representation.pmc.version.dto.VersionManageDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * VersionManageVO: 版本管理[VO]
 *
 * @author mx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionManageVO  {

    private String versionId;
    private String name;
    private String description;

    /**
     * 转化：版本管理[DTO]转化为版本管理[VO]
     * <p>单个</p>
     *
     * @param versionManageDTO 版本管理[DTO]
     * @return 版本管理[VO]
     */
    public static VersionManageVO convert(VersionManageDTO versionManageDTO) {
        VersionManageVO versionManageVO = new VersionManageVO();
        BeanUtils.copyProperties(versionManageDTO, versionManageVO);
        return versionManageVO;
    }

    /**
     * 转化：版本管理[DTO]转化为版本管理[VO]
     * <p>集合</p>
     *
     * @param versionManageDtoList 版本管理[DTO]集合
     * @return 版本管理[VO]集合
     */
    public static List<VersionManageVO> convert(List<VersionManageDTO> versionManageDtoList) {
        List<VersionManageVO> versionManageVoList = new ArrayList<>(versionManageDtoList.size());
        versionManageDtoList.forEach(versionManageDTO -> versionManageVoList.add(VersionManageVO.convert(versionManageDTO)));
        return versionManageVoList;
    }

}
