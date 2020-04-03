package org.mxframework.contentflow.representation.sis.reader;

import lombok.Data;

/**
 * @author mx
 */
@Data
public class ReadingBase {

    private String productId;
    private String productType;
    private String readerIdentity;
    private Integer liked;
    private Integer disliked;
    private Integer counter;
}
