package org.mxframework.contentflow.representation.pmc.version.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * VersionItemDTO: 版本条目[DTO]
 *
 * @author mx
 * @date 2019-10-13
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VersionItemDTO extends VersionBaseDTO {

    public static VersionItemDTO convert(Version version) {
        VersionItemDTO versionItemDTO = new VersionItemDTO();
        BeanUtils.copyProperties(version, versionItemDTO);
        return versionItemDTO;
    }

    public static List<VersionItemDTO> convert(List<Version> versionList) {
        List<VersionItemDTO> versionItemDtoList = new ArrayList<>(versionList.size());
        versionList.forEach(version -> versionItemDtoList.add(VersionItemDTO.convert(version)));
        return versionItemDtoList;
    }
}
