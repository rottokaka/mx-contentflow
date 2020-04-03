package org.mxframework.contentflow.service.sis;

import org.mxframework.contentflow.constant.sis.TagConstant;
import org.mxframework.contentflow.domain.model.sis.identity.Creator;
import org.mxframework.contentflow.domain.model.sis.identity.Marker;
import org.mxframework.contentflow.domain.model.sis.identity.Person;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.tag.*;
import org.mxframework.contentflow.representation.sis.tag.dto.TagDetailDTO;
import org.mxframework.contentflow.representation.sis.tag.dto.TagItemDTO;
import org.mxframework.contentflow.representation.sis.tag.form.TagModifyForm;
import org.mxframework.contentflow.service.iaa.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mx
 */
@Service
public class TagService {
    private static final Logger logger = LoggerFactory.getLogger(TagService.class);

    private final TagRepository tagRepository;
    private final PersonTagRepository personTagRepository;
    private final ProductTagRepository productTagRepository;
    private final TagRelevanceService tagRelevanceService;
    private final UserService userService;

    public TagService(TagRepository tagRepository
            , PersonTagRepository personTagRepository
            , ProductTagRepository productTagRepository
            , TagRelevanceService tagRelevanceService
            , UserService userService) {
        this.tagRepository = tagRepository;
        this.personTagRepository = personTagRepository;
        this.productTagRepository = productTagRepository;
        this.userService = userService;
        this.tagRelevanceService = tagRelevanceService;
    }

    public TagId nextIdentity() {
        return tagRepository.nextIdentity();
    }

    public TagDetailDTO getTagDetailByTagId(String tagId) {
        Tag tag = tagRepository.tagOfTagId(new TagId(tagId));
        TagDetailDTO tagDetailDTO = new TagDetailDTO(tag);

        return tagDetailDTO;
    }

    public Tag getByTagId(TagId tagId) {
        return tagRepository.tagOfTagId(tagId);
    }

    public Tag getByName(String name) {
        return tagRepository.tagOfName(name);
    }

    public List<ProductTag> listTagMarkerProductOfProduct(Product product) {
        return (List<ProductTag>) productTagRepository.productTagsOfProduct(product);
    }

    public List<TagItemDTO> listItemByCreator(Creator creator) {
        return TagItemDTO.convert(this.selectAllByCreator(creator));
    }

    public List<ProductTag> listItemByProduct(Product product) {
        return (List<ProductTag>) productTagRepository.productTagsOfProduct(product);
    }

    public List<PersonTag> listIdentitiesOfTagId(TagId tagId) {
        return (List<PersonTag>) personTagRepository.personTagsOfTagId(tagId);
    }

    public List<Tag> selectAll() {
        return (List<Tag>) tagRepository.tags();
    }

    public List<Tag> listByCreatorAndProperty(Creator creator, Integer property) {
        return (List<Tag>) tagRepository.tagsOfCreatorAndProperty(creator, property);
    }

    public List<Tag> selectAllByCreator(Creator creator) {
        return (List<Tag>) tagRepository.tagsOfCreator(creator);
    }

    public void add(Tag tag) {
        tagRepository.add(tag);
    }

    public void update(Tag tag) {
        tagRepository.add(tag);
    }

    public void saveAllTagsToProduct(String tags, Product product) {
        for (String name : tags.split(TagConstant.TAGS_SEPARATOR)) {
            addTagToProduct(name, product);
        }
        // 后续处理-MQ TODO
        // 标签关联处理
        tagRelevanceService.add(new TagRelevance(tags, new Product(product.id(), TagConstant.TAG_TYPE_BLOG)));
    }

    private void addTagToProduct(String name, Product product) {
        // 保存至标签池
        TagId tagId;
        Tag ofName = tagRepository.tagOfName(name);
        if (ofName != null) {
            tagId = ofName.tagId();
        } else {
            tagId = tagRepository.nextIdentity();
            tagRepository.add(new Tag(tagId, name, TagConstant.TAG_PROPERTY_PRIVATE_MARKUP, new Creator(userService.identity()), name));
        }
        // 保存至个人标签库
        PersonTag personTag = personTagRepository.personTagOfPersonAndTagId(new Person(userService.identity()), tagId);
        if (personTag == null) {
            personTagRepository.add(new PersonTag(new Person(userService.identity()), tagId));
        }
        // 保存至标记者标记标签至产品
        productTagRepository.add(new ProductTag(product, tagId, new Marker(userService.identity())));
    }

    public void updateByTagId(String tagId, TagModifyForm tagModifyForm) {
        Tag byId = getByTagId(new TagId(tagId));
        byId.setName(tagModifyForm.getName());
        byId.setDescription(tagModifyForm.getDescription());
        tagRepository.add(byId);
    }

    public void addTagsToProduct(String tags, Product product) {
        for (String tagName : tags.split(TagConstant.TAGS_SEPARATOR)) {
            addTagToProduct(tagName, product);
        }
    }

    public void removeByTagId(String tagId) {
        tagRepository.remove(tagRepository.tagOfTagId(new TagId(tagId)));
    }

    public void deleteAllByProduct(String productId, String productType) {
        productTagRepository.removeAll(productTagRepository.productTagsOfProduct(new Product(productId, productType)));
    }

    public List<Tag> listPublic() {
        return (List<Tag>) tagRepository.tagsOfProperty(TagConstant.TAG_PROPERTY_PRIVATE_MARKUP);
    }
}
