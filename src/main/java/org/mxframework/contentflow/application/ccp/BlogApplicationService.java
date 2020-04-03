package org.mxframework.contentflow.application.ccp;

import com.google.common.base.Strings;
import org.mxframework.contentflow.application.iaa.IdentityApplicationService;
import org.mxframework.contentflow.application.sis.ReadingApplicationService;
import org.mxframework.contentflow.application.sis.TagApplicationService;
import org.mxframework.contentflow.constant.DataLayout;
import org.mxframework.contentflow.constant.ccp.BlogConstant;
import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.domain.model.ccp.collaborator.Blogger;
import org.mxframework.contentflow.domain.model.ccp.product.ProductType;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogId;
import org.mxframework.contentflow.representation.ccp.blog.dto.BlogCardDTO;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogConfigModifyForm;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogCreateForm;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogModifyForm;
import org.mxframework.contentflow.representation.ccp.blog.vo.*;
import org.mxframework.contentflow.representation.sis.reader.dto.ReadingAtProductOutlineDTO;
import org.mxframework.contentflow.representation.sis.tag.dto.TagAtProductDTO;
import org.mxframework.contentflow.representation.sis.tag.dto.TagItemDTO;
import org.mxframework.contentflow.representation.sis.tag.vo.TagAtProductVO;
import org.mxframework.contentflow.service.ccp.BlogPagination;
import org.mxframework.contentflow.service.ccp.BlogService;
import org.mxframework.contentflow.service.ccp.translator.BlogTranslator;
import org.mxframework.contentflow.service.ccp.translator.ReaderTranslator;
import org.mxframework.contentflow.service.ccp.translator.TagTranslator;
import org.mxframework.contentflow.util.BlogUtil;
import org.mxframework.contentflow.util.DataLayoutUtil;
import org.mxframework.contentflow.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class BlogApplicationService {

    @Autowired
    private ReadingApplicationService readingApplicationService;
    @Autowired
    private TagApplicationService tagApplicationService;
    @Autowired
    private IdentityApplicationService identityApplicationService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTranslator blogTranslator;
    @Autowired
    private TagTranslator tagTranslator;
    @Autowired
    private ReaderTranslator readerTranslator;
    @Autowired
    private BlogPagination blogPagination;

    public boolean collectionNotAllow(String blogId) {
        return BlogConstant.BLOG_NOT_ALLOW_COLLECT_TRUE.equals(blogService.getByBlogId(new BlogId(blogId)).collectionNotAllowed());
    }

    public Blog getByBlogId(String blogId) {
        return blogService.getByBlogId(new BlogId(blogId));
    }

    public BlogVO<? extends BlogBaseVO> getBaseByBlogId(String blogId, String layout) {
        Blog blog = blogService.getByBlogId(new BlogId(blogId));
        DataLayout dataLayout = DataLayoutUtil.layout(layout);
        switch (dataLayout) {
            case DATA_LAYOUT_CARD:
                return new BlogVO<>(blogTranslator.convertToCardVo(blog));
            case DATA_LAYOUT_DETAIL:
                return new BlogVO<>(blogTranslator.convertToDetailVo(blog));
            default:
                return new BlogVO<>(blogTranslator.convertToBaseVo(blog));
        }
    }

    public BlogDetailVO getDetailByBlogId(String blogId) {
        return blogTranslator.convertToDetailVo(blogService.getByBlogId(new BlogId(blogId)));
    }

    public List<? extends BlogBaseVO> listVo(String layout) {
        List<Blog> blogList = blogService.listByScope(ScopeConstant.SCOPE_PUBLIC);
        DataLayout dataLayout = DataLayoutUtil.layout(layout);
        switch (dataLayout) {
            case DATA_LAYOUT_DETAIL:
                return blogTranslator.convertToDetailVo(blogList);
            case DATA_LAYOUT_CARD:
                return blogTranslator.convertToCardVo(blogList);
            default:
                return blogTranslator.convertToBaseVo(blogList);
        }
    }

    public List<BlogCardDTO> listCardDtoByBlogIdList(List<String> blogIds) {
        if (blogIds != null && blogIds.size() > 0) {
            List<BlogId> blogIdList = new ArrayList<>(blogIds.size());
            blogIds.forEach(blogId -> blogIdList.add(new BlogId(blogId)));
            List<Blog> blogList = blogService.listByBlogIdList(blogIdList);
            List<BlogCardDTO> blogCardDtos = blogTranslator.convertToCardDto(blogList);
            // 补充数据，完整对象
            for (BlogCardDTO blogCardDto : blogCardDtos) {
                blogCardDto.setReadingAtProductOutlineDto(readingApplicationService.getReaderAtProductOutlineDtoByProduct(blogCardDto.getBlogId()
                        , ProductType.PRODUCT_BLOG.toString().toUpperCase()));
                blogCardDto.setTagItemDtoList(tagApplicationService.listItemDtoByProduct(blogCardDto.getBlogId()
                        , ProductType.PRODUCT_BLOG.toString().toUpperCase()));
            }
            return blogCardDtos;
        } else {
            return null;
        }
    }

    public List<BlogCardDTO> listCardDtoByIdentity(String identity) {
        List<BlogCardDTO> blogCardDtos = blogTranslator.convertToCardDto(blogService.listByBlogger(new Blogger(identity)));
        // 补充数据，完整对象
        for (BlogCardDTO blogCardDto : blogCardDtos) {
            blogCardDto.setReadingAtProductOutlineDto(readingApplicationService.getReaderAtProductOutlineDtoByProduct(blogCardDto.getBlogId()
                    , ProductType.PRODUCT_BLOG.toString().toUpperCase()));
            blogCardDto.setTagItemDtoList(tagApplicationService.listItemDtoByProduct(blogCardDto.getBlogId()
                    , ProductType.PRODUCT_BLOG.toString().toUpperCase()));
        }
        return blogCardDtos;
    }

    public List<BlogCardVO> listCardVoByIdentity(String username) {
        List<Blog> blogList;
        Blogger blogger = new Blogger(username);
        if (SecurityUtil.isPrincipal(username)) {
            // 列出博主未归档的博客
            blogList = blogService.listByBloggerAndArchived(blogger, BlogConstant.BLOG_ARCHIVED_NOT_DEFAULT);
        } else {
            // 列出博客公开的博客
            blogList = blogService.listByBloggerAndScope(blogger, ScopeConstant.SCOPE_PUBLIC);
        }
        List<BlogCardVO> blogCardVoList = blogTranslator.convertToCardVo(blogList);
        // 补充数据，完整对象
        if (blogCardVoList != null && blogCardVoList.size() > 0) {
            for (BlogCardVO blogCardVo : blogCardVoList) {

                ReadingAtProductOutlineDTO readerAtProductOutlineDtoByProduct = readingApplicationService
                        .getReaderAtProductOutlineDtoByProduct(blogCardVo.getBlogId(), ProductType.PRODUCT_BLOG.toString().toUpperCase());
                if (readerAtProductOutlineDtoByProduct != null) {
                    blogCardVo.setReaderAtProductOutlineVo(readerTranslator.convertToReaderAtProductVo(readerAtProductOutlineDtoByProduct));
                }

                List<TagItemDTO> tagItemDtoList = tagApplicationService.listItemDtoByProduct(blogCardVo.getBlogId()
                        , ProductType.PRODUCT_BLOG.toString().toUpperCase());
                if (tagItemDtoList != null && tagItemDtoList.size() > 0) {
                    blogCardVo.setTagItemVoList(tagTranslator.convertToItemVo(tagItemDtoList));
                }
            }
        }

        return blogCardVoList;
    }

    public List<BlogCardDTO> listCardByBlogIdList(List<String> blogIds) {
        List<BlogId> blogIdList = new ArrayList<>(blogIds.size());
        blogIds.forEach(blogId -> blogIdList.add(new BlogId(blogId)));
        List<BlogCardDTO> blogCardDtoList = blogTranslator.convertToCardDto(blogService.listCardByBlogIdList(blogIdList));
        // 补充数据，完整对象
        for (BlogCardDTO blogCardDto : blogCardDtoList) {
            blogCardDto.setReadingAtProductOutlineDto(readingApplicationService
                    .getReaderAtProductOutlineDtoByProduct(blogCardDto.getBlogId(), ProductType.PRODUCT_BLOG.toString().toUpperCase()));
            blogCardDto.setTagItemDtoList(tagApplicationService.listItemDtoByProduct(blogCardDto.getBlogId(), ProductType.PRODUCT_BLOG.toString().toUpperCase()));
        }
        return blogCardDtoList;
    }

    public List<BlogManageVO> listManageVo() {
        List<Blog> blogList = blogService.listByBlogger(new Blogger(identityApplicationService.identity()));
        if (blogList != null && blogList.size() > 0) {
            return blogTranslator.convertToManageVo(blogList);
        } else {
            return null;
        }
    }

    public List<BlogCardVO> fillBlogCardVo(List<BlogCardVO> blogCardVoList) {
        // 补全数据，完整对象
        for (BlogCardVO cardVo : blogCardVoList) {
            List<TagItemDTO> tagItemDtos = tagApplicationService.listItemDtoByProduct(cardVo.getBlogId()
                    , ProductType.PRODUCT_BLOG.toString().toUpperCase());

            cardVo.setTagItemVoList(tagTranslator.convertToItemVo(tagItemDtos));
            ReadingAtProductOutlineDTO readingAtProductOutlineDto = readingApplicationService.getReaderAtProductOutlineDtoByProduct(cardVo.getBlogId()
                    , ProductType.PRODUCT_BLOG.toString().toUpperCase());
            cardVo.setReaderAtProductOutlineVo(readerTranslator.convertToReaderAtProductVo(readingAtProductOutlineDto));
        }
        return blogCardVoList;
    }

    public List<BlogCardVO> listPublicBlogs() {
        List<Blog> blogList = blogService.listByScope(ScopeConstant.SCOPE_PUBLIC);
        if (blogList != null && blogList.size() > 0) {
            return fillBlogCardVo(blogTranslator.convertToCardVo(blogList));
        } else {
            return null;
        }
    }

    public List<BlogCardVO> listCardByBlogList(List<Blog> blogList) {
        return fillBlogCardVo(blogTranslator.convertToCardVo(blogList));
    }

    public List<TagAtProductVO> listTagOfBlog(String blogId) {
        List<TagAtProductDTO> tagAtProductDtoList = tagApplicationService.listTagAtProductDto(blogId, ProductType.PRODUCT_BLOG.toString().toUpperCase());
        return tagTranslator.convertToTagAtProductVo(tagAtProductDtoList);
    }

    public Page<Blog> pageByKeyword(String keyword, Pageable pageable) {
        return blogPagination.pageByKeyword(keyword, pageable);
    }

    @Transactional(rollbackFor = {Exception.class})
    public BlogBaseVO post(BlogCreateForm blogCreateForm) {
        BlogId blogId = blogService.nextIdentity();
        Blog blog = new Blog(blogId);
        blog.setBlogger(new Blogger(identityApplicationService.identity()));
        // 构建博客对象，通过视图
        BeanUtils.copyProperties(blogCreateForm, blog);
        // 默认配置
        blog.setArchived(BlogConstant.BLOG_ARCHIVED_NOT_DEFAULT);
        blog.setAboveBlogId(BlogConstant.BLOG_ABOVEID_DEFAULT);
        // 处理博客内容，提取概要
        BlogUtil.handleContent(blog);
        blogService.add(blog);
        // 处理标签，如果标签字符串不为空
        if (Strings.emptyToNull(blogCreateForm.getTags()) != null) {
            // TODO Event Publish
            tagApplicationService.addTagsToProduct(blogCreateForm.getTags(), blogId.id(), ProductType.PRODUCT_BLOG.toString().toUpperCase());
        }
        return blogTranslator.convertToBaseVo(blogService.getByBlogId(blogId));
    }

    @Transactional(rollbackFor = {Exception.class})
    public Blog putBlogByBlogId(BlogId blogId, BlogModifyForm blogModifyForm) {
        Blog byCode = blogService.getByBlogId(blogId);
        byCode.setTitle("".equals(blogModifyForm.getTitle())
                ? BlogUtil.getRegexResult(BlogConstant.BLOG_PATTERN_TITLE, blogModifyForm.getContent())
                : blogModifyForm.getTitle());
        byCode.setSummary(BlogUtil.getRegexResult(BlogConstant.BLOG_PATTERN_SUMMARY, blogModifyForm.getContent()));
        byCode.setContent(blogModifyForm.getContent());
        byCode.setContentHtml(blogModifyForm.getContentHtml());
        return blogService.updateBlog(byCode);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void patchConfig(BlogId blogId, BlogConfigModifyForm configUpdateVo) {
        Blog blog = blogService.getByBlogId(blogId);
        // 可修改配置
        blog.setScope(configUpdateVo.getScope());
        blog.setCollectionNotAllowed(configUpdateVo.getCollectionNotAllowed());
        blogService.add(blog);
    }


    @Transactional(rollbackFor = {Exception.class})
    public void patchArchivedByBlogId(List<String> blogIds, Integer archived) {
        if (blogIds != null && blogIds.size() > 0) {
            List<BlogId> blogIdList = new ArrayList<>(blogIds.size());
            blogIds.forEach(blogId -> blogIdList.add(new BlogId(blogId)));
            blogService.patchAllArchived(blogIdList, archived);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteById(String blogId) {
        Blog blog = blogService.getByBlogId(new BlogId(blogId));
        // 删除关联标签
        // TODO Event Publish
        tagApplicationService.deleteAllByProduct(blog.blogId().id(), ProductType.PRODUCT_BLOG.toString().toUpperCase());
        blogService.deleteByBlogId(new BlogId(blogId));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteAllByBlogger(String identity) {
        blogService.removeAll(blogService.listByBlogger(new Blogger(identity)));
    }

}
