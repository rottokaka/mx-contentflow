package org.mxframework.contentflow.representation.ccp.snippet.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SnippetCardVO extends SnippetBaseVO {

    /**
     * 构造函数
     * 用于JPA返回自定义对象
     *
     * @param snippetId       片段ID
     * @param creatorIdentity 创建者身份标识
     * @param title           标题
     * @param description     描述
     * @param content         内容
     * @param contentHtml     内容HTML格式
     * @param gmtCreate       创建时间
     * @param gmtModified     修改时间
     * @see org.mxframework.contentflow.repository.ccp.SnippetJpaRepository#findAllByScope(Integer, Pageable)
     * @see org.mxframework.contentflow.repository.ccp.SnippetJpaRepository#findAllByTitleContainingOrDescriptionContainingAndScope(String, String, Integer, Pageable)
     */
    public SnippetCardVO(String snippetId
            , String creatorIdentity
            , String title
            , String description
            , String content
            , String contentHtml
            , Date gmtCreate
            , Date gmtModified) {
        super(snippetId, creatorIdentity, title, description, content, contentHtml, gmtCreate, gmtModified);
    }
}
