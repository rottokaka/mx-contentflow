package org.mxframework.contentflow.representation.sis.reader;

import lombok.Data;

/**
 * @author mx
 */
@Data
public class ReadingAtProductOutlineBase {

    private Integer counterSum;
    private String likePercent;

    public ReadingAtProductOutlineBase(Integer counterSum, String likePercent) {
        this.counterSum = counterSum;
        this.likePercent = likePercent;
    }

    public ReadingAtProductOutlineBase(){
    }
}
