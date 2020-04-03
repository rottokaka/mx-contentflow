package org.mxframework.contentflow.representation;

import lombok.Data;

/**
 * ResultVO: 结果[VO]
 *
 * @author mx
 */
@Data
public class ResultVO {

    private Boolean valid;
    private Integer code;
    private String message;
    private Object data;
}
