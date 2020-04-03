package org.mxframework.contentflow.representation.sis.tag.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.sis.tag.TagBase;
import org.mxframework.contentflow.representation.sis.tag.dto.TagCardDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TagCardVO 博客卡片视图对象
 *
 * @author mx
 * @date 2019-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagCardVO extends TagBaseVO {

    private Integer productCounter;
    private Integer personCounter;

    public TagCardVO(TagBase tagBase) {
        super(tagBase);
    }

    public TagCardVO() {

    }

    public static TagCardVO convert(TagCardDTO tagCardDTO) {
        TagCardVO tagCardVO = new TagCardVO();
        BeanUtils.copyProperties(tagCardDTO, tagCardVO);
        tagCardVO.personCounter = tagCardDTO.getUserList().size();
        tagCardVO.productCounter = tagCardDTO.getBlogList().size();
        return tagCardVO;
    }

    public static List<TagCardVO> convert(List<TagCardDTO> tagCardDtoList) {
        List<TagCardVO> tagCardVoList = new ArrayList<>();
        tagCardDtoList.forEach(tagCardDTO -> tagCardVoList.add(TagCardVO.convert(tagCardDTO)));
        return tagCardVoList;
    }
}
