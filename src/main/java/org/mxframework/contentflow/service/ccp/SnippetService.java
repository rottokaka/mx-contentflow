package org.mxframework.contentflow.service.ccp;

import org.mxframework.contentflow.constant.ccp.ScopeConstant;
import org.mxframework.contentflow.domain.model.ccp.product.snippet.Snippet;
import org.mxframework.contentflow.domain.model.ccp.product.snippet.SnippetId;
import org.mxframework.contentflow.domain.model.ccp.product.snippet.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SnippetService: 片段接口
 *
 * @author mx
 */
@Service
public class SnippetService {

    @Autowired
    private SnippetRepository snippetRepository;

    public SnippetId nextIdentity() {
        return snippetRepository.nextIdentity();
    }

    public Snippet getBySnippetId(SnippetId snippetId) {
        return snippetRepository.snippetOfSnippetId(snippetId);
    }

    public void add(Snippet snippet) {
        snippetRepository.add(snippet);
    }

    public void update(Snippet snippet) {
        snippetRepository.add(snippet);
    }

    public void deleteBySnippetId(SnippetId snippetId) {
        snippetRepository.remove(snippetRepository.snippetOfSnippetId(snippetId));
    }

    public List<Snippet> listPublicSnippets() {
        return (List<Snippet>) snippetRepository.snippetsOfScope(ScopeConstant.SCOPE_PUBLIC);
    }
}
