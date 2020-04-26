package org.mxframework.contentflow.service.ccp.translator;

import org.mxframework.contentflow.constant.DataLayout;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.representation.ccp.blog.BlogBase;
import org.mxframework.contentflow.representation.ccp.blog.dto.BlogBaseDTO;
import org.mxframework.contentflow.representation.ccp.blog.dto.BlogCardDTO;
import org.mxframework.contentflow.representation.ccp.blog.dto.BlogDetailDTO;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogConfigModifyForm;
import org.mxframework.contentflow.representation.ccp.blog.form.BlogModifyForm;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogBaseVO;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogCardVO;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogDetailVO;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogManageVO;
import org.mxframework.contentflow.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service
public class BlogTranslator {

    @Autowired
    private TagTranslator tagTranslator;
    @Autowired
    private ReaderTranslator readerTranslator;

    private BlogBase convertToBase(Blog blog) {
        if (blog != null) {
            BlogBase blogBase = new BlogBase();
            blogBase.setGmtCreate(blog.gmtCreate());
            blogBase.setGmtModified(blog.gmtModified());
            blogBase.setBlogId(blog.blogId().id());
            blogBase.setBloggerIdentity(blog.blogger().identify());
            blogBase.setTitle(blog.title());
            blogBase.setSubTitle(blog.subTitle());
            blogBase.setSummary(blog.summary());
            blogBase.setContent(blog.content());
            blogBase.setContentHtml(blog.contentHtml());
            blogBase.setScope(blog.scope());
            blogBase.setCollectionNotAllowed(blog.collectionNotAllowed());
            blogBase.setArchived(blog.archived());
            blogBase.setAboveBlogId(blog.aboveBlogId());
            return blogBase;
        } else {
            return null;
        }
    }

    private BlogBaseDTO convertToBaseDto(Blog blog) {
        if (blog != null) {
            BlogBaseDTO blogBaseDTO = new BlogBaseDTO();
            BeanUtils.copyProperties(this.convertToBase(blog), blogBaseDTO);
            return blogBaseDTO;
        } else {
            return null;
        }
    }

    public List<BlogBaseDTO> convertToBaseDto(List<Blog> blogList) {
        List<BlogBaseDTO> blogBaseDtos = new ArrayList<>(blogList.size());
        blogList.forEach(blog -> blogBaseDtos.add(convertToBaseDto(blog)));
        return blogBaseDtos;
    }

    public BlogBaseVO convertToBaseVo(Blog blog) {
        BlogBaseVO blogBaseVo = new BlogBaseVO();
        BeanUtils.copyProperties(this.convertToBase(blog), blogBaseVo);
        return blogBaseVo;
    }

    public List<BlogBaseVO> convertToBaseVo(List<Blog> blogList) {
        List<BlogBaseVO> baseVoList = new ArrayList<>(blogList.size());
        blogList.forEach(blog -> baseVoList.add(this.convertToBaseVo(blog)));
        return baseVoList;
    }

    private BlogCardDTO convertToCardDto(Blog blog) {
        BlogCardDTO blogCardDto = new BlogCardDTO();
        BlogBaseDTO blogBaseDto = this.convertToBaseDto(blog);
        BeanUtils.copyProperties(blogBaseDto, blogCardDto);
        return blogCardDto;
    }

    public List<BlogCardDTO> convertToCardDto(List<Blog> blogList) {
        List<BlogCardDTO> blogCardDtoList = new ArrayList<>();
        blogList.forEach(blog -> blogCardDtoList.add(this.convertToCardDto(blog)));
        return blogCardDtoList;
    }


    public BlogCardVO convertToCardVo(Blog blog) {
        BlogCardVO blogCardVo = new BlogCardVO();
        BeanUtils.copyProperties(this.convertToBaseVo(blog), blogCardVo);
        return blogCardVo;
    }

    public List<BlogCardVO> convertToCardVo(List<Blog> blogList) {
        List<BlogCardVO> blogCardVoList = new ArrayList<>(blogList.size());
        blogList.forEach(blog -> blogCardVoList.add(this.convertToCardVo(blog)));
        return blogCardVoList;
    }

    public BlogConfigModifyForm convertToConfigModifyForm(Blog blog) {
        return new BlogConfigModifyForm(blog.title(), blog.scope(), blog.collectionNotAllowed());
    }

    public BlogDetailVO convertToDetailVo(Blog blog) {
        BlogDetailVO blogDetailVo = new BlogDetailVO(convertToBase(blog));
        blogDetailVo.setAuthored(SecurityUtil.isPrincipal(blog.blogger().identify()));
        return blogDetailVo;
    }

    public List<BlogDetailVO> convertToDetailVo(List<Blog> blogList) {
        return null;
    }

    public List<? extends BlogBaseDTO> convertToDto(List<Blog> blogList, DataLayout layout) {
        switch (layout) {
            case DATA_LAYOUT_CARD:
                return this.convertToCardDto(blogList);
            case DATA_LAYOUT_DETAIL:
                return this.convertToDetailDto(blogList);
            default:
                return this.convertToBaseDto(blogList);
        }
    }

    public List<BlogManageVO> convertToManageVo(List<Blog> blogList) {
        List<BlogManageVO> blogManageVoList = new ArrayList<>();
        for (Blog blog : blogList) {
            blogManageVoList.add(new BlogManageVO(convertToBase(blog)));
        }
        return blogManageVoList;
    }

    public BlogModifyForm convertToModifyForm(Blog blog) {
        return new BlogModifyForm(blog.title(), blog.content(), blog.contentHtml());
    }

    public List<? extends BlogBaseVO> convertToVo(List<Blog> blogList, DataLayout layout) {
        switch (layout) {
            case DATA_LAYOUT_CARD:
                return this.convertToCardVo(blogList);
            case DATA_LAYOUT_DETAIL:
                return this.convertToDetailVo(blogList);
            default:
                return this.convertToBaseVo(blogList);
        }
    }

    private List<BlogDetailDTO> convertToDetailDto(List<Blog> blogList) {
        return null;
    }

}
