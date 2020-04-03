package org.mxframework.contentflow.representation.sis.tag;

import lombok.Data;

/**
 * @author mx
 */
@Data
public class TagPersonBase {

    private String tagId;
    private String tagName;
    private String tagDescription;
    private Integer property;
    private String personIdentity;
}
