package org.mxframework.contentflow.domain.model.ccp.markdown;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.domain.model.AbstractValueObject;

/**
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Segment extends AbstractValueObject {

    private String content;
    private String title;
    private Integer level;
    private Integer index;

}
