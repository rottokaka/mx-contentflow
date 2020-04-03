package org.mxframework.contentflow.domain.model.ccp.product.snippet;

import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.ccp.collaborator.Creator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Snippet: 片段
 *
 * @author mx
 */
@Entity
public class Snippet extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "片段ID不能为空")
    @Embedded
    private SnippetId snippetId;
    @NotNull(message = "创建者不能为空")
    @Embedded
    private Creator creator;

    @NotNull(message = "标题不能为空")
    @Size(min = 2, max = 200, message = "标题字符串长度不能超出范围‘2~20’")
    @Column(nullable = false, columnDefinition = "VARCHAR(200) COMMENT '标题'")
    private String title;

    @NotNull(message = "内容不能为空")
    @Size(min = 2, max = 10000, message = "内容不能超出范围‘2~10000’")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT COMMENT '内容'")
    private String content;

    @Size(max = 300, message = "描述字符串长度不能超过‘300’")
    @Column(columnDefinition = "VARCHAR(300) COMMENT '描述'")
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(columnDefinition = "TEXT COMMENT '内容的HTML格式'")
    private String contentHtml;

    @Column(name = "scope", nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '范围'")
    private Integer scope;

    public Snippet(SnippetId snippetId
            , Creator creator
            , String title
            , String content
            , String contentHtml
            , String description
            , Integer scope) {
        this();
        this.setSnippetId(snippetId);
        this.setCreator(creator);
        this.setTitle(title);
        this.setContent(content);
        this.setContentHtml(contentHtml);
        this.setDescription(description);
        this.setScope(scope);
    }

    public Snippet(SnippetId snippetId, Creator creator, String title, String description) {
        this(snippetId, creator, title, description, null, null, null);
    }

    public Snippet(SnippetId snippetId) {
        this(snippetId, null, null, null);
    }

    protected Snippet() {
        super();
    }

    public SnippetId snippetId() {
        return this.snippetId;
    }

    public Creator creator() {
        return this.creator;
    }

    public String title() {
        return this.title;
    }

    public String content() {
        return this.content;
    }

    public String description() {
        return this.description;
    }

    public String contentHtml() {
        return this.contentHtml;
    }

    public Integer scope() {
        return this.scope;
    }

    public void setSnippetId(SnippetId snippetId) {
        this.snippetId = snippetId;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public void setScope(Integer scope) {
        if (scope != null) {
            this.scope = scope;
        } else {
            scope = ScopeConstant.SCOPE_PRIVATE;
        }
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "snippetId=" + snippetId +
                ", creator=" + creator +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", contentHtml='" + contentHtml + '\'' +
                ", description='" + description + '\'' +
                ", scope=" + scope +
                '}';
    }
}
