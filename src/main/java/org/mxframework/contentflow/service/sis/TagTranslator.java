package org.mxframework.contentflow.service.sis;

import org.mxframework.contentflow.constant.sis.TagConstant;
import org.mxframework.contentflow.domain.model.sis.identity.Marker;
import org.mxframework.contentflow.domain.model.sis.tag.PersonTag;
import org.mxframework.contentflow.domain.model.sis.tag.ProductTag;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.mxframework.contentflow.representation.sis.tag.TagBase;
import org.mxframework.contentflow.representation.sis.tag.dto.TagAtProductDTO;
import org.mxframework.contentflow.representation.sis.tag.dto.TagItemDTO;
import org.mxframework.contentflow.representation.sis.tag.form.TagModifyForm;
import org.mxframework.contentflow.representation.sis.tag.vo.*;
import org.mxframework.contentflow.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author mx
 */
@Service
public class TagTranslator {

    @Autowired
    private TagService tagService;
    @Autowired
    private PersonTagService personTagService;
    @Autowired
    private ProductTagService productTagService;
    @Autowired
    private TagRelevanceService tagRelevanceService;

    public List<TagAtProductDTO> convertToTagAtProductDto(List<ProductTag> productTagList) {
        // 1. 遍历博客标签集合 (tagIdIdentity) 构建标签ID-用户IdentityMap集合 (Map<TagId, Set<String>)
        Map<TagId, Set<String>> tagIdIdentity = new HashMap<>(16);
        if (productTagList != null) {
            for (ProductTag productTag : productTagList) {
                TagId tagId = productTag.tagId();
                Marker marker = productTag.marker();
                Set<String> identitySet;
                if (!tagIdIdentity.containsKey(tagId)) {
                    identitySet = new HashSet<>(1);
                } else {
                    identitySet = tagIdIdentity.get(tagId);
                }
                identitySet.add(marker.identity());
                tagIdIdentity.put(tagId, identitySet);
            }
            // 2. 遍历集合，通过用户集合是否包含当前用户来确定当前用户是否使用该标签名称标记过博客
            List<TagAtProductDTO> tagAtProductDtoList = new ArrayList<>();
            for (TagId tagId : tagIdIdentity.keySet()) {
                TagAtProductDTO tagAtProductDTO = new TagAtProductDTO();
                tagAtProductDTO.setTagId(tagId.id());
                tagAtProductDTO.setTagName(tagService.getByTagId(tagId).name());
                Set<String> identitySet = tagIdIdentity.get(tagId);
                // 如果用户集合包含当前用户，则用户使用该标签标记过博客，否则没有标记过
                if (SecurityUtil.getPrincipal() != null && identitySet.contains(SecurityUtil.getPrincipal().getUsername())) {
                    tagAtProductDTO.setLabeled(TagConstant.TAG_OF_BLOG_IS_LABELED_YES);
                } else {
                    tagAtProductDTO.setLabeled(TagConstant.TAG_OF_BLOG_IS_LABELED_NOT);
                }
                tagAtProductDTO.setCounter(identitySet.size());
                tagAtProductDtoList.add(tagAtProductDTO);
            }
            return tagAtProductDtoList;
        } else {
            return null;
        }
    }

    public TagModifyForm convertToModifyVo(PersonTag tagPersonOfIdAndPersonTag) {
        return null;
    }

    public List<TagPersonManageVO> convertToTagPersonManageVo(List<PersonTag> personTagList) {
        if (personTagList != null && personTagList.size() > 0) {
            List<TagPersonManageVO> tagPersonManageVoList = new ArrayList<>(personTagList.size());
            for (PersonTag personTag : personTagList) {
                TagPersonManageVO tagPersonManageVo = new TagPersonManageVO();
                tagPersonManageVo.setTagId(personTag.tagId().id());
                tagPersonManageVo.setPersonIdentity(personTag.person().identity());
                Tag tag = tagService.getByTagId(personTag.tagId());
                tagPersonManageVo.setTagName(tag.name());
                tagPersonManageVo.setTagDescription(tag.description());
                tagPersonManageVo.setProperty(tag.property());
                tagPersonManageVoList.add(tagPersonManageVo);
            }
            return tagPersonManageVoList;
        } else {
            return null;
        }
    }

    public List<TagPersonItemVO> convertToTagPersonItemVo(List<PersonTag> personTagList) {
        if (personTagList != null && personTagList.size() > 0) {
            List<TagPersonItemVO> tagPersonItemVoList = new ArrayList<>(personTagList.size());
            for (PersonTag personTag : personTagList) {
                TagPersonItemVO tagPersonItemVo = new TagPersonItemVO();
                tagPersonItemVo.setTagId(personTag.tagId().id());
                tagPersonItemVo.setPersonIdentity(personTag.person().identity());
                Tag tag = tagService.getByTagId(personTag.tagId());
                tagPersonItemVo.setTagName(tag.name());
                tagPersonItemVo.setTagDescription(tag.description());
                tagPersonItemVo.setProperty(tag.property());
                tagPersonItemVoList.add(tagPersonItemVo);
            }
            return tagPersonItemVoList;
        } else {
            return null;
        }
    }

    private TagBase convertToTagBase(Tag tag) {
        TagBase tagBase = new TagBase();
        tagBase.setTagId(tag.tagId().id());
        tagBase.setCreatorIdentity(tag.creator().identity());
        tagBase.setName(tag.name());
        tagBase.setDescription(tag.description());
        tagBase.setProperty(tag.property());
        tagBase.setGmtCreate(tag.gmtCreate());
        tagBase.setGmtModified(tag.gmtModified());
        return tagBase;
    }

    public TagDetailVO convertToDetailVo(Tag tag) {
        TagDetailVO tagDetailVo = new TagDetailVO(convertToTagBase(tag));
        tagDetailVo.setTagRelevantVoList(tagRelevanceService.listTagRelevantVoByTagName(tag.name()));
        return tagDetailVo;
    }

    public List<TagCardVO> convertToCardVo(List<Tag> tagList) {
        if (tagList != null && tagList.size() > 0) {
            List<TagCardVO> tagCardVoList = new ArrayList<>(tagList.size());
            for (Tag tag : tagList) {
                TagCardVO tagCardVo = new TagCardVO(this.convertToTagBase(tag));
                tagCardVo.setProductCounter(productTagService.getProductCounterByTagId(tag.tagId()));
                tagCardVo.setPersonCounter(personTagService.getPersonCounterByTagId(tag.tagId()));
                tagCardVoList.add(tagCardVo);
            }
            return tagCardVoList;
        } else {
            return null;
        }
    }

    public List<TagItemDTO> convertToItemDto(Collection<Tag> tagCollection) {
        List<TagItemDTO> tagItemDtoList = new ArrayList<>(tagCollection.size());
        tagCollection.forEach(tag -> tagItemDtoList.add(new TagItemDTO(this.convertToTagBase(tag))));
        return tagItemDtoList;
    }

    public List<TagItemVO> convertToItemVo(Set<Tag> tagSet) {
        List<TagItemVO> tagItemVoList = new ArrayList<>(tagSet.size());
        tagSet.forEach(tag -> tagItemVoList.add(new TagItemVO(this.convertToTagBase(tag))));
        return tagItemVoList;
    }
}
