package org.mxframework.contentflow.application.sis;

import org.mxframework.contentflow.application.ccp.BlogApplicationService;
import org.mxframework.contentflow.application.iaa.IdentityApplicationService;
import org.mxframework.contentflow.application.iaa.UserApplicationService;
import org.mxframework.contentflow.constant.sis.TagConstant;
import org.mxframework.contentflow.domain.model.ccp.product.ProductType;
import org.mxframework.contentflow.domain.model.sis.identity.Creator;
import org.mxframework.contentflow.domain.model.sis.identity.Marker;
import org.mxframework.contentflow.domain.model.sis.identity.Person;
import org.mxframework.contentflow.domain.model.sis.product.Product;
import org.mxframework.contentflow.domain.model.sis.tag.PersonTag;
import org.mxframework.contentflow.domain.model.sis.tag.ProductTag;
import org.mxframework.contentflow.domain.model.sis.tag.Tag;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.mxframework.contentflow.exception.TagException;
import org.mxframework.contentflow.exception.iaa.UserNotLoginedException;
import org.mxframework.contentflow.representation.ccp.blog.dto.BlogCardDTO;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogCardVO;
import org.mxframework.contentflow.representation.iaa.dto.UserCardDTO;
import org.mxframework.contentflow.representation.iaa.vo.UserCardVO;
import org.mxframework.contentflow.representation.sis.tag.dto.TagAtProductDTO;
import org.mxframework.contentflow.representation.sis.tag.dto.TagItemDTO;
import org.mxframework.contentflow.representation.sis.tag.form.ProductTagForm;
import org.mxframework.contentflow.representation.sis.tag.form.TagCreateForm;
import org.mxframework.contentflow.representation.sis.tag.form.TagModifyForm;
import org.mxframework.contentflow.representation.sis.tag.vo.*;
import org.mxframework.contentflow.service.sis.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mx
 */
@Service
public class TagApplicationService {

    @Autowired
    private ReadingApplicationService readingApplicationService;

    @Autowired
    private BlogApplicationService blogApplicationService;
    @Autowired
    private UserApplicationService userApplicationService;
    @Autowired
    private IdentityApplicationService identityApplicationService;

    @Autowired
    private TagService tagService;
    @Autowired
    private PersonTagService personTagService;
    @Autowired
    private ProductTagService productTagService;

    @Autowired
    private TagTranslator tagTranslator;
    @Autowired
    private UserTranslator userTranslator;
    @Autowired
    private BlogTranslator blogTranslator;

    public List<TagCardVO> list() {
        return tagTranslator.convertToCardVo(tagService.listPublic());
    }

    private Set<Tag> convertToTagSet(List<ProductTag> productTagList) {
        Set<Tag> tagSet = new HashSet<>(productTagList.size());
        productTagList.forEach(productTag -> tagSet.add(tagService.getByTagId(productTag.tagId())));
        return tagSet;
    }

    public List<TagItemDTO> listItemByIdentity(String identity) {
        List<PersonTag> personTagList = personTagService.listByPerson(new Person(identity));
        if (!personTagList.isEmpty()) {
            List<Tag> tagList = new ArrayList<>(personTagList.size());
            personTagList.forEach(personTag -> tagList.add(tagService.getByTagId(personTag.tagId())));
            return tagTranslator.convertToItemDto(tagList);
        } else {
            return null;
        }
    }

    /**
     * 列出关于产品的标签条目[DTO]
     *
     * @param productId   产品ID
     * @param productType 产品类型
     * @return 标签条目[DTO]
     */
    public List<TagItemDTO> listItemDtoByProduct(String productId, String productType) {
        List<ProductTag> productTagList = productTagService.listByProduct(new Product(productId, productType));
        return tagTranslator.convertToItemDto(convertToTagSet(productTagList));
    }

    public List<TagItemVO> listItemVoByProduct(String productId, String productType) {
        List<ProductTag> productTagList = productTagService.listByProduct(new Product(productId, productType));
        return tagTranslator.convertToItemVo(convertToTagSet(productTagList));
    }

    public List<TagAtProductDTO> listTagAtProductDto(String productId, String productType) {
        List<ProductTag> productTagList = tagService.listTagMarkerProductOfProduct(new Product(productId, productType));
        return tagTranslator.convertToTagAtProductDto(productTagList);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void addTagsToProduct(String tags, String productId, String productType) {
        tagService.addTagsToProduct(tags, new Product(productId, productType));
    }

    public List<UserCardVO> listUserCardVoByTagId(String tagId) {
        List<PersonTag> personTagList = tagService.listIdentitiesOfTagId(new TagId(tagId));
        List<String> identities = new ArrayList<>(personTagList.size());
        personTagList.forEach(tagPerson -> identities.add(tagPerson.person().identity()));
        List<UserCardDTO> userCardDtoList = userApplicationService.listCardByIdentities(identities);
        if (userCardDtoList != null && userCardDtoList.size() > 0) {
            return userTranslator.convertToCardVo(userCardDtoList);
        } else {
            return null;
        }
    }

    public List<BlogCardVO> listBlogCardVoByTagId(String tagId) {
        List<ProductTag> productTagList = productTagService.listByTagId(new TagId(tagId));
        List<String> blogIds = new ArrayList<>(productTagList.size());
        for (ProductTag productTag : productTagList) {
            Product product = productTag.product();
            if (ProductType.PRODUCT_BLOG.equals(ProductType.valueOf(product.type()))) {
                blogIds.add(product.id());
            }
        }
        List<BlogCardDTO> blogCardDtoList = blogApplicationService.listCardByBlogIdList(blogIds);
        List<BlogCardVO> blogCardVoList = blogTranslator.convertToCardVo(blogCardDtoList);
        // 补充数据，完整对象
        for (BlogCardVO blogCardVo : blogCardVoList) {
            blogCardVo.setReaderAtProductOutlineVo(readingApplicationService
                    .getReaderAtProductOutlineVoByProduct(blogCardVo.getBlogId(), ProductType.PRODUCT_BLOG.toString().toUpperCase()));
            blogCardVo.setTagItemVoList(this.listItemVoByProduct(blogCardVo.getBlogId(), ProductType.PRODUCT_BLOG.toString().toUpperCase()));
        }
        return blogCardVoList;
    }

    @Transactional(rollbackFor = {Exception.class})
    public PersonTag post(TagCreateForm tagCreateForm) {
        Tag byName = tagService.getByName(tagCreateForm.getName());
        // 1. 判断标签池中是否存在该标签，通过表情名称
        if (byName != null) {
            // 1.1. 添加个人标签库
            personTagService.add(new PersonTag(new Person(identity()), byName.tagId()));
            return personTagService.getByPersonAndTagId(new Person(identity()), byName.tagId());
        } else {
            // 1.2.1. 添加公共标签池
            TagId tagId = tagService.nextIdentity();
            tagService.add(new Tag(tagId
                    , tagCreateForm.getName()
                    , tagCreateForm.getProperty()
                    , new Creator(identity())
                    , tagCreateForm.getDescription()));
            // 1.2.2. 添加个人标签库
            personTagService.add(new PersonTag(new Person(identity()), tagId));
            return personTagService.getByPersonAndTagId(new Person(identity()), tagId);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public ProductTag postProductTagByProductId(String productId, ProductTagForm productTagForm) {
        String identity = identityApplicationService.identity();
        TagId tagId = new TagId(productTagForm.getTagId());
        // 先保存个人标签库
        Person person = new Person(identity);
        PersonTag personTag = personTagService.getByPersonAndTagId(person, tagId);
        if (personTag == null) {
            personTagService.add(new PersonTag(person, tagId));
        }
        // 后保存产品标签记录
        Product product = new Product(productId, productTagForm.getProductType());
        Marker marker = new Marker(identity);
        ProductTag byProductAndTagIdAndMarker = productTagService.getByProductAndTagIdAndMarker(product, tagId, marker);
        if (byProductAndTagIdAndMarker != null) {
            throw new TagException("已标记该产品");
        } else {
            ProductTag productTag = new ProductTag(new Product(productId, productTagForm.getProductType()), tagId, new Marker(identity));
            productTagService.add(productTag);
            return productTag;
        }
    }

    public TagModifyForm getModifyFormByTagId(String tagId) {
        return tagTranslator.convertToModifyVo(personTagService.getByPersonAndTagId(new Person(identity()), new TagId(tagId)));
    }

    private String identity() {
        String identity = identityApplicationService.identity();
        if (identity == null) {
            throw new UserNotLoginedException("登录失效，请重新登录");
        }
        return identity;
    }

    public Object getByTagId(String tagId) {
        return null;
    }

    public void updateByTagId(String tagId, TagModifyForm tagModifyForm) {

    }

    public void deleteByTagId(String tagId) {
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteByProductAndTagId(String productId, String productType, String tagId) {
        ProductTag productTag = productTagService.getByProductAndTagIdAndMarker(new Product(productId, productType)
                , new TagId(tagId)
                , new Marker(identityApplicationService.identity()));
        if (productTag != null) {
            productTagService.remove(productTag);
        } else {
            throw new TagException("标记已删除");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByProduct(String id, String type) {
        tagService.deleteAllByProduct(id, type);
    }

    public List<TagPersonItemVO> getItemByProperty(String property) {
        List<PersonTag> personTagList = personTagService.listByPerson(new Person(identity()));
        // 当前标签目的是标记，获取管理标签
        if (TagConstant.TAG_PROPERTY_PRIVATE_MARKUP == Integer.parseInt(property)) {
            personTagList.removeIf(tagPerson -> TagConstant.TAG_PROPERTY_PRIVATE_MARKUP.equals(tagService.getByTagId(tagPerson.tagId()).property()));
        }
        // 当前标签目的是管理，获取标记标签
        if (TagConstant.TAG_PROPERTY_PRIVATE_MANAGE == Integer.parseInt(property)) {
            personTagList.removeIf(tagPerson -> TagConstant.TAG_PROPERTY_PRIVATE_MANAGE.equals(tagService.getByTagId(tagPerson.tagId()).property()));
        }
        return tagTranslator.convertToTagPersonItemVo(personTagList);

    }

    public List<TagPersonManageVO> listTagPersonManageVo() {
        return tagTranslator.convertToTagPersonManageVo(personTagService.listByPerson(new Person(identity())));
    }

    public TagDetailVO getDetailByTagId(String tagId) {
        return tagTranslator.convertToDetailVo(tagService.getByTagId(new TagId(tagId)));
    }
}
