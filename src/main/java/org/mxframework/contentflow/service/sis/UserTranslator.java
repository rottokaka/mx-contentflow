package org.mxframework.contentflow.service.sis;

import org.mxframework.contentflow.representation.iaa.dto.UserCardDTO;
import org.mxframework.contentflow.representation.iaa.vo.UserCardVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 */
@Service("sisUserTranslator")
public class UserTranslator {

    public List<UserCardVO> convertToCardVo(List<UserCardDTO> userCardDtoList) {
        List<UserCardVO> userCardVoList = new ArrayList<>(userCardDtoList.size());
        for (UserCardDTO userCardDTO : userCardDtoList) {
            UserCardVO userCardVo = new UserCardVO();
            BeanUtils.copyProperties(userCardDTO, userCardVo);
            userCardVoList.add(userCardVo);
        }
        return userCardVoList;
    }
}
