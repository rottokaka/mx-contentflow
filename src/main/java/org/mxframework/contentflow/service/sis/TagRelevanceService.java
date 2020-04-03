package org.mxframework.contentflow.service.sis;

import org.mxframework.contentflow.constant.sis.TagConstant;
import org.mxframework.contentflow.domain.model.sis.tag.TagRelevance;
import org.mxframework.contentflow.domain.model.sis.tag.TagRelevanceRepository;
import org.mxframework.contentflow.repository.sis.TagRelevanceJpaRepository;
import org.mxframework.contentflow.representation.sis.tag.TagRelevantBase;
import org.mxframework.contentflow.representation.sis.tag.dto.TagRelevantDTO;
import org.mxframework.contentflow.representation.sis.tag.vo.TagRelevantVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author mx
 */
@Service
public class TagRelevanceService {

    @Autowired
    private TagRelevanceRepository tagRelevanceRepository;
    @Autowired
    private TagRelevanceJpaRepository tagRelevanceJpaRepository;

    /**
     * 获取目标标签的Map集合
     *
     * @param targetName 目标标签名称
     * @return 标签名称和关联总数的Map集合
     */
    private Map<String, Integer> fetchSourceMapByTagName(String targetName) {
        List<TagRelevance> tagRelevanceList = this.listByTagsContaining(targetName);
        Map<String, Integer> tagNameSumMap = new HashMap<>(16);
        for (TagRelevance tagRelevance : tagRelevanceList) {
            String tags = tagRelevance.tags();
            String[] strings = StringUtils.delimitedListToStringArray(tags, TagConstant.TAGS_SEPARATOR);
            for (String tagName : strings) {
                if (!targetName.equalsIgnoreCase(tagName)) {
                    //
                    if (!tagNameSumMap.containsKey(tagName)) {
                        tagNameSumMap.put(tagName, 1);
                    } else {
                        Integer sum = tagNameSumMap.get(tagName);
                        // 前++是先自加再使用而后++是先使用再自加！
                        tagNameSumMap.put(tagName, ++sum);
                    }
                }
            }
        }
        return tagNameSumMap;
    }

    public List<TagRelevantBase> fetchTagRelevantBase(Map<String, Integer> tagNameSumMap) {
        // 排序
        List<Map.Entry<String, Integer>> mapList = new ArrayList<>(tagNameSumMap.entrySet());
        mapList.sort(Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed());
        List<TagRelevantBase> tagRelevantBaseList = new ArrayList<>();
        mapList.forEach(tagNameSum -> tagRelevantBaseList.add(new TagRelevantBase(tagNameSum.getKey(), tagNameSum.getValue())));
        return tagRelevantBaseList;
    }


    public List<TagRelevantDTO> listTagRelevantDtoByTagName(String tagName) {
        List<TagRelevantBase> tagRelevantBaseList = this.fetchTagRelevantBase(fetchSourceMapByTagName(tagName));
        List<TagRelevantDTO> tagRelevantDtoList = new ArrayList<>(tagRelevantBaseList.size());
        tagRelevantBaseList.forEach(tagRelevantBase -> tagRelevantDtoList.add(new TagRelevantDTO(tagRelevantBase)));
        return tagRelevantDtoList;
    }

    public List<TagRelevantVO> listTagRelevantVoByTagName(String tagName) {
        List<TagRelevantBase> tagRelevantBaseList = this.fetchTagRelevantBase(fetchSourceMapByTagName(tagName));
        List<TagRelevantVO> tagRelevantVoList = new ArrayList<>(tagRelevantBaseList.size());
        tagRelevantBaseList.forEach(tagRelevantBase -> tagRelevantVoList.add(new TagRelevantVO(tagRelevantBase)));
        return tagRelevantVoList;
    }

    public List<TagRelevance> listByTagsContaining(String tagName) {
        return tagRelevanceJpaRepository.findAllByTagsContaining(tagName);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void add(TagRelevance tagRelevance) {
        tagRelevanceRepository.add(tagRelevance);
    }
}
