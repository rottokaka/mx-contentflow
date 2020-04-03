package org.mxframework.contentflow.representation.sis.tag;

import lombok.Data;

import java.util.Date;

/**
 * @author mx
 */
@Data
public class TagBase {

    private String tagId;
    private String creatorIdentity;
    private String name;
    private String description;
    private Integer property;
    private Date gmtCreate;
    private Date gmtModified;

    public TagBase(String tagId, String creatorIdentity, String name, String description, Integer property, Date gmtCreate, Date gmtModified) {
        this.tagId = tagId;
        this.creatorIdentity = creatorIdentity;
        this.name = name;
        this.description = description;
        this.property = property;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public TagBase(TagBase tagBase) {
        this.tagId = tagBase.getTagId();
        this.creatorIdentity = tagBase.getCreatorIdentity();
        this.name = tagBase.getName();
        this.description = tagBase.getDescription();
        this.property = tagBase.getProperty();
        this.gmtCreate = tagBase.getGmtCreate();
        this.gmtModified = tagBase.getGmtModified();
    }

    public TagBase() {
    }
}
