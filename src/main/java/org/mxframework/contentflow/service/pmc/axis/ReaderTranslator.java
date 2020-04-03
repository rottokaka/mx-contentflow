package org.mxframework.contentflow.service.pmc.axis;

import org.mxframework.contentflow.representation.sis.reader.dto.ReadingAtProductOutlineDTO;
import org.mxframework.contentflow.representation.sis.reader.vo.ReadingAtProductOutlineVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author mx
 */
@Service("pmcReaderTranslator")
public class ReaderTranslator {

    public ReadingAtProductOutlineVO convertToReaderVo(ReadingAtProductOutlineDTO readingAtProductOutlineDto) {
        if (readingAtProductOutlineDto != null) {
            ReadingAtProductOutlineVO readingAtProductOutlineVo = new ReadingAtProductOutlineVO();
            BeanUtils.copyProperties(readingAtProductOutlineDto, readingAtProductOutlineVo);
            return readingAtProductOutlineVo;
        } else {
            return null;
        }
    }
}
