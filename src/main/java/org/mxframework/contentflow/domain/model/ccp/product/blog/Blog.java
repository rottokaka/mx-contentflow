package org.mxframework.contentflow.domain.model.ccp.product.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.mxframework.contentflow.domain.model.ccp.collaborator.Blogger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Blog: 博客
 *
 * @author mx
 */
@Entity
@AttributeOverrides({
        @AttributeOverride(
                name = "blogger.identity", column = @Column(name = "blogger_identity", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '博主身份'")
        ),
        @AttributeOverride(
                name = "blogger.emailAddress", column = @Column(name = "blogger_email_address", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '博主邮箱地址'")
        ),
        @AttributeOverride(
                name = "blogger.nickname", column = @Column(name = "blogger_nickname", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '博主昵称'")
        )
})
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Blog extends IdentifiedEntityObject {
    private static final long serialVersionUID = 1L;

    @Embedded
    private BlogId blogId;

    @Embedded
    private Blogger blogger;

    @Column(nullable = false, columnDefinition = "VARCHAR(200) COMMENT '标题'")
    private String title;

    @Column(columnDefinition = "VARCHAR(200) COMMENT '副标题'")
    private String subTitle;

    @Column(columnDefinition = "VARCHAR(1000) COMMENT '概要'")
    private String summary;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @NotNull(message = "内容不能为空")
    @Size(min = 2, max = 10000, message = "内容不能超出范围‘2~10000’")
    @Column(nullable = false, columnDefinition = "TEXT COMMENT '内容'")
    private String content;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(columnDefinition = "TEXT COMMENT '内容的HTML格式'")
    private String contentHtml;

    @NotNull(message = "博客范围不能为空")
    @Column(name = "scope", nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '范围'")
    private Integer scope;

    @NotNull(message = "收录是否被允许不能为空")
    @Column(name = "collection_not_allowed", nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '收录是否被允许'")
    private Integer collectionNotAllowed;

    @Column(name = "is_archived", columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '是否归档'")
    private Integer archived;

    @Column
    private String aboveBlogId;

    // constructors~
    // =================================================================================================================

    public Blog(BlogId blogId
            , Blogger blogger
            , String title
            , String subTitle
            , String summary
            , String content
            , String contentHtml
            , Integer scope
            , Integer collectionNotAllowed
            , Integer archived
            , String aboveBlogId) {
        this();
        this.setBlogId(blogId);
        this.setBlogger(blogger);
        this.setTitle(title);
        this.setSubTitle(subTitle);
        this.setSummary(summary);
        this.setContent(content);
        this.setContentHtml(contentHtml);
        this.setScope(scope);
        this.setCollectionNotAllowed(collectionNotAllowed);
        this.setArchived(archived);
        this.setAboveBlogId(aboveBlogId);
    }

    public Blog(BlogId blogId) {
        this(blogId, null, null, null, null, null, null, null, null, null, null);
    }

    protected Blog() {
        super();
    }

    public BlogId blogId() {
        return this.blogId;
    }

    public void setBlogId(BlogId blogId) {
        this.blogId = blogId;
    }

    public Blogger blogger() {
        return this.blogger;
    }

    public void setBlogger(Blogger blogger) {
        this.blogger = blogger;
    }

    public String title() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String subTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String summary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String content() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String contentHtml() {
        return this.contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public Integer scope() {
        return this.scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Integer collectionNotAllowed() {
        return this.collectionNotAllowed;
    }

    public void setCollectionNotAllowed(Integer collectionNotAllowed) {
        this.collectionNotAllowed = collectionNotAllowed;
    }

    public Integer archived() {
        return this.archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public String aboveBlogId() {
        return this.aboveBlogId;
    }

    public void setAboveBlogId(String aboveBlogId) {
        this.aboveBlogId = aboveBlogId;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogger=" + blogger +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", contentHtml='" + contentHtml + '\'' +
                ", scope=" + scope +
                ", collectionNotAllowed=" + collectionNotAllowed +
                ", archived=" + archived +
                ", aboveBlogId='" + aboveBlogId + '\'' +
                '}';
    }
}
