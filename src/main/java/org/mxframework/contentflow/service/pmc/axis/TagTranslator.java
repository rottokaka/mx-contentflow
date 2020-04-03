package org.mxframework.contentflow.service.pmc.axis;

import org.mxframework.contentflow.representation.sis.tag.dto.TagItemDTO;
import org.mxframework.contentflow.representation.sis.tag.vo.TagItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service("pmcTagTranslator")
public class TagTranslator {

    public TagItemVO convertToItemVo(TagItemDTO tagItemDto) {
        if (tagItemDto != null) {
            TagItemVO tagItemVo = new TagItemVO();
            BeanUtils.copyProperties(tagItemDto, tagItemVo);
            return tagItemVo;
        } else {
            return null;
        }
    }
    public List<TagItemVO> convertToItemVo(List<TagItemDTO> tagItemDtos) {
        if (tagItemDtos != null && tagItemDtos.size()>0) {
            List<TagItemVO> tagItemVos = new ArrayList<>(tagItemDtos.size());
            tagItemDtos.forEach(tagItemDto -> tagItemVos.add(this.convertToItemVo(tagItemDto)));
            return tagItemVos;
        } else {
            return null;
        }
    }
}
