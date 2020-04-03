package org.mxframework.contentflow.service.ccp;

import org.mxframework.contentflow.constant.ccp.SnippetConstant;
import org.mxframework.contentflow.repository.ccp.SnippetJpaRepository;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author mx
 */
@Service
public class SnippetPagination {

    @Autowired
    private SnippetJpaRepository snippetJpaRepository;

    public Page<SnippetCardVO> pageByKeyword(Integer page, Integer size, String sort, String keyword) {
        Pageable pageable = null;
        if (SnippetConstant.SNIPPET_SORT_DEFAULT_CREATED_DESC.equals(sort)) {
            pageable = PageRequest.of(page, size, Sort.Direction.DESC, "gmtCreate");
        }
        if (SnippetConstant.SNIPPET_SORT_UPDATED_DESC.equals(sort)) {
            pageable = PageRequest.of(page, size, Sort.Direction.DESC, "gmtModified");
        }
        return pageByKeywordAndPageable(keyword, pageable);
    }

    private Page<SnippetCardVO> pageByKeywordAndPageable(String keyword, Pageable pageable) {
        if (keyword == null) {
            return snippetJpaRepository.findAllByScope(SnippetConstant.SNIPPET_NON_PRIVATE_DEFAULT, pageable);
        } else {
            return snippetJpaRepository.findAllByTitleContainingOrDescriptionContainingAndScope(keyword, keyword, SnippetConstant.SNIPPET_NON_PRIVATE_DEFAULT, pageable);
        }
    }
}
