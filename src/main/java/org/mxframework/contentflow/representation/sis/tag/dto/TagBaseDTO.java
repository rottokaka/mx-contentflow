package org.mxframework.contentflow.representation.sis.tag.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.sis.tag.TagBase;

import java.io.Serializable;

/**
 * TagBaseDTO: 标签基础[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagBaseDTO extends TagBase implements Serializable {
    private static final long serialVersionUID = 1L;

    public TagBaseDTO(TagBase tagBase) {
        super(tagBase);
    }
    public TagBaseDTO() {}

}
