package org.mxframework.contentflow.service.iaa;

import org.mxframework.contentflow.representation.ccp.blog.dto.BlogCardDTO;
import org.mxframework.contentflow.representation.ccp.blog.vo.BlogCardVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service("iaaBlogTranslator")
public class BlogTranslator {

    @Autowired
    private TagTranslator tagTranslator;
    @Autowired
    private ReaderTranslator readerTranslator;

    private BlogCardVO convertToCardVo(BlogCardDTO blogCardDto) {
        if (blogCardDto != null) {
            BlogCardVO cardVo = new BlogCardVO();
            BeanUtils.copyProperties(blogCardDto, cardVo);
            cardVo.setTagItemVoList(tagTranslator.convertToItemVo(blogCardDto.getTagItemDtoList()));
            cardVo.setReaderAtProductOutlineVo(readerTranslator.convertToVo(blogCardDto.getReadingAtProductOutlineDto()));
            return cardVo;
        } else {
            return null;
        }
    }

    public List<BlogCardVO> convertToCardVo(List<BlogCardDTO> blogCardDtos) {
        if (blogCardDtos != null && blogCardDtos.size() > 0) {
            List<BlogCardVO> cardVos = new ArrayList<>(blogCardDtos.size());
            blogCardDtos.forEach(blogCardDto -> cardVos.add(this.convertToCardVo(blogCardDto)));
            return cardVos;
        } else {
            return null;
        }
    }
}
