package org.mxframework.contentflow.representation.ccp.blog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mxframework.contentflow.representation.ccp.blog.dto.BlogManageDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * BlogManageVO: 博客管理[VO]
 *
 * @author mx
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BlogManageVO extends BlogBaseVO {

    public static BlogManageVO convert(BlogManageDTO blogManageDTO) {
        BlogManageVO blogManageVO = new BlogManageVO();
        BeanUtils.copyProperties(blogManageDTO, blogManageVO);
        return blogManageVO;
    }

    public static List<BlogManageVO> convert(List<BlogManageDTO> blogManageDtoList) {
        List<BlogManageVO> blogManageVoList = new ArrayList<>();
        blogManageDtoList.forEach(blogManageDTO -> blogManageVoList.add(BlogManageVO.convert(blogManageDTO)));
        return blogManageVoList;
    }
}
