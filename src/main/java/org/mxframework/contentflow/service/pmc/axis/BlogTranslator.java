package org.mxframework.contentflow.service.pmc.axis;

import org.mxframework.contentflow.domain.model.ccp.product.ProductType;
import org.mxframework.contentflow.representation.ccp.blog.dto.BlogCardDTO;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogCardVO;
import org.mxframework.contentflow.representation.pmc.ProductAtAxisVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service("pmcBlogTranslator")
public class BlogTranslator {

    @Autowired
    private TagTranslator tagTranslator;
    @Autowired
    private ReaderTranslator readerTranslator;

    public BlogCardVO convertToCardVo(BlogCardDTO blogCardDto) {
        if (blogCardDto != null) {
            BlogCardVO cardVo = new BlogCardVO();
            BeanUtils.copyProperties(blogCardDto, cardVo);
            cardVo.setTagItemVoList(tagTranslator.convertToItemVo(blogCardDto.getTagItemDtoList()));
            cardVo.setReaderAtProductOutlineVo(readerTranslator.convertToReaderVo(blogCardDto.getReadingAtProductOutlineDto()));
            return cardVo;
        }  else {
            return null;
        }
    }
    public List<BlogCardVO> convertToCardVo(List<BlogCardDTO> blogCardDtoList) {
        if (blogCardDtoList != null && blogCardDtoList.size() > 0) {
            List<BlogCardVO> blogCardVoList = new ArrayList<>(blogCardDtoList.size());
            blogCardDtoList.forEach(blogCardDto -> blogCardVoList.add(this.convertToCardVo(blogCardDto)));
            return blogCardVoList;
        } else {
            return null;
        }
    }

    public ProductAtAxisVO convertToProductAtAxisVo(BlogCardDTO blogCardDto) {
        if (blogCardDto != null) {
            ProductAtAxisVO productAtAxisVo = new ProductAtAxisVO();
            productAtAxisVo.setTitle(blogCardDto.getTitle());
            productAtAxisVo.setContent(blogCardDto.getContent());
            productAtAxisVo.setProductId(blogCardDto.getBlogId());
            productAtAxisVo.setProductType(ProductType.PRODUCT_BLOG.toString().toUpperCase());
            return productAtAxisVo;
        } else {
            return null;
        }
    }
    public List<ProductAtAxisVO> convertToProductAtAxisVo(List<BlogCardDTO> blogCardDtos) {
        if (blogCardDtos != null && blogCardDtos.size() > 0) {
            List<ProductAtAxisVO> productAtAxisVos = new ArrayList<>(blogCardDtos.size());
            blogCardDtos.forEach(blogCardDTO -> productAtAxisVos.add(this.convertToProductAtAxisVo(blogCardDTO)));
            return productAtAxisVos;
        } else {
            return null;
        }
    }
}
