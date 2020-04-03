package org.mxframework.contentflow.representation.sis.tag.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.representation.sis.tag.TagBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TagItemDTO: 标签条目[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagItemDTO extends TagBaseDTO {
    private static final long serialVersionUID = 1L;

    public TagItemDTO(TagBase tagBase) {
        super(tagBase);
    }

    public TagItemDTO(){

    }

    public static TagItemDTO convert(Tag tag) {
        if (tag != null) {
            TagItemDTO tagItemDTO = new TagItemDTO();
            tagItemDTO.setGmtCreate(tag.gmtCreate());
            tagItemDTO.setGmtModified(tag.gmtModified());
            tagItemDTO.setTagId(tag.tagId().id());
            tagItemDTO.setName(tag.name());
            tagItemDTO.setDescription(tag.description());
            tagItemDTO.setProperty(tag.property());
            tagItemDTO.setCreatorIdentity(tag.creator().identity());
            return tagItemDTO;
        } else {
            return null;
        }
    }

    public static List<TagItemDTO> convert(Collection<Tag> tags) {
        List<TagItemDTO> tagItemDtoList = new ArrayList<>();
        if (!tags.isEmpty()) {
            tags.forEach(tag -> tagItemDtoList.add(TagItemDTO.convert(tag)));
        }
        return tagItemDtoList;
    }
}
