package org.mxframework.contentflow.service.sis;

import org.mxframework.contentflow.constant.sis.ReadingConstant;
import org.mxframework.contentflow.domain.model.sis.reading.Reading;
import org.mxframework.contentflow.representation.sis.reader.ReadingAtProductOutlineBase;
import org.mxframework.contentflow.representation.sis.reader.dto.ReadingAtProductOutlineDTO;
import org.mxframework.contentflow.representation.sis.reader.vo.ReadingAtProductOutlineVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mx
 */
@Service
public class ReadingTranslator {


    public ReadingAtProductOutlineBase convertToReadingAtProductOutlineBase(List<Reading> readingList) {
        int counterSum = 0;
        int likedCount = 0;
        int dislikedCount = 0;
        if (readingList != null && readingList.size() > 0) {
            for (Reading reading : readingList) {
                counterSum += reading.counter();
                if (ReadingConstant.READER_LIKED_YES.equals(reading.liked())) {
                    likedCount += 1;
                } else if (ReadingConstant.READER_DISLIKED_YES.equals(reading.disliked())) {
                    dislikedCount += 1;
                }
            }
        }
        ReadingAtProductOutlineBase readingAtProductOutlineBase = new ReadingAtProductOutlineBase();
        readingAtProductOutlineBase.setCounterSum(counterSum);
        if (likedCount >= ReadingConstant.READER_LIKED_BASE_FACTOR
                && dislikedCount >= ReadingConstant.READER_DISLIKED_BASE_FACTOR
                && (likedCount + dislikedCount) >= ReadingConstant.READER_LIKED_PERCENT_BASE_FACTOR) {
            int readPercent = likedCount * 100 / (likedCount + dislikedCount);
            readingAtProductOutlineBase.setLikePercent(readPercent + "%");
        } else {
            readingAtProductOutlineBase.setLikePercent(ReadingConstant.READER_LIKED_PERCENT_DEFAULT);
        }
        return readingAtProductOutlineBase;
    }


    public ReadingAtProductOutlineDTO convertToReaderAtProductDto(List<Reading> readingList) {
        ReadingAtProductOutlineBase readingAtProductOutlineBase = this.convertToReadingAtProductOutlineBase(readingList);
        return new ReadingAtProductOutlineDTO(readingAtProductOutlineBase.getCounterSum(), readingAtProductOutlineBase.getLikePercent());
    }

    public ReadingAtProductOutlineVO convertToReaderAtProductVo(List<Reading> readingList) {
        ReadingAtProductOutlineBase readingAtProductOutlineBase = this.convertToReadingAtProductOutlineBase(readingList);
        return new ReadingAtProductOutlineVO(readingAtProductOutlineBase.getCounterSum(), readingAtProductOutlineBase.getLikePercent());
    }
}
