package org.mxframework.contentflow.representation.sis.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TagIndexVO: 标签主页视图对象
 *
 * @author mx
 * @date 2019-10-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagIndexVO {

    /**
     * tagCardVoList: 标签卡片视图对象集合
     */
    private List<TagCardVO> tagCardVoList;
}
