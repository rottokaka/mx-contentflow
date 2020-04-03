package org.mxframework.contentflow.representation.ccp.snippet;

import lombok.Data;

import java.util.Date;

/**
 * @author mx
 */
@Data
public class SnippetBase {

    private String snippetId;
    private String creatorIdentity;
    private String title;
    private String description;
    private String content;
    private String contentHtml;
    private Date gmtCreate;
    private Date gmtModified;

    public SnippetBase(String snippetId
            , String creatorIdentity
            , String title
            , String description
            , String content
            , String contentHtml
            , Date gmtCreate
            , Date gmtModified) {
        this.snippetId = snippetId;
        this.creatorIdentity = creatorIdentity;
        this.title = title;
        this.description = description;
        this.content = content;
        this.contentHtml = contentHtml;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public SnippetBase() {
    }
}
