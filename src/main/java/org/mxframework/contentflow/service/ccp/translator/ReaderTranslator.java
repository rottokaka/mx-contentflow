package org.mxframework.contentflow.service.ccp.translator;

import org.mxframework.contentflow.representation.sis.reader.dto.ReadingAtProductOutlineDTO;
import org.mxframework.contentflow.representation.sis.reader.vo.ReadingAtProductOutlineVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author mx
 */
@Service("ccpReaderTranslator")
public class ReaderTranslator {

    public ReadingAtProductOutlineVO convertToReaderAtProductVo(ReadingAtProductOutlineDTO readingAtProductOutlineDto) {
        ReadingAtProductOutlineVO readingAtProductOutlineVo = new ReadingAtProductOutlineVO();
        BeanUtils.copyProperties(readingAtProductOutlineDto, readingAtProductOutlineVo);
        return readingAtProductOutlineVo;
    }
}
