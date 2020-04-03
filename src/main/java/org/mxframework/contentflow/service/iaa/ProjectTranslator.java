package org.mxframework.contentflow.service.iaa;

import org.mxframework.contentflow.representation.pmc.project.dto.ProjectItemDTO;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service("iaaProjectTranslator")
public class ProjectTranslator {

    public List<ProjectItemVO> convertToItemVo(List<ProjectItemDTO> projectItemDtoList) {
        List<ProjectItemVO> projectItemVoList = new ArrayList<>(projectItemDtoList.size());
        for (ProjectItemDTO projectItemDto : projectItemDtoList) {
            ProjectItemVO projectItemVo = new ProjectItemVO();
            BeanUtils.copyProperties(projectItemDto, projectItemVo);
            projectItemVoList.add(projectItemVo);
        }
        return projectItemVoList;
    }

}
