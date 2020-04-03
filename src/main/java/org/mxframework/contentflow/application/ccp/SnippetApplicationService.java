package org.mxframework.contentflow.application.ccp;

import org.mxframework.contentflow.application.iaa.IdentityApplicationService;
import org.mxframework.contentflow.domain.model.ccp.collaborator.Creator;
import org.mxframework.contentflow.domain.model.ccp.product.snippet.Snippet;
import org.mxframework.contentflow.domain.model.ccp.product.snippet.SnippetId;
import org.mxframework.contentflow.exception.NotFoundException;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetCardVO;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetCreateForm;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetDetailVO;
import org.mxframework.contentflow.representation.ccp.snippet.vo.SnippetModifyForm;
import org.mxframework.contentflow.service.ccp.SnippetPagination;
import org.mxframework.contentflow.service.ccp.SnippetService;
import org.mxframework.contentflow.service.ccp.SnippetTranslator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mx
 */
@Service
public class SnippetApplicationService {

    @Autowired
    private IdentityApplicationService identityApplicationService;

    @Autowired
    private SnippetService snippetService;
    @Autowired
    private SnippetPagination snippetPagination;
    @Autowired
    private SnippetTranslator snippetTranslator;

    public Snippet getBySnippetId(String snippetId) {
        Snippet bySnippetId = snippetService.getBySnippetId(new SnippetId(snippetId));
        if (bySnippetId != null) {
            return bySnippetId;
        } else {
            throw new NotFoundException("片段ID不存在：" + snippetId);
        }
    }

    public SnippetDetailVO getDetailBySnippetId(String snippetId) {
        return snippetTranslator.convertToDetailVo(snippetService.getBySnippetId(new SnippetId(snippetId)));
    }

    public SnippetModifyForm getModifyVoBy(String snippetId) {
        return snippetTranslator.convertToModifyVo(snippetService.getBySnippetId(new SnippetId(snippetId)));
    }

    public List<Snippet> listPublicSnippets() {
        return snippetService.listPublicSnippets();
    }

    @Transactional(rollbackFor = {Exception.class})
    public Snippet post(SnippetCreateForm snippetCreateForm) {
        SnippetId snippetId = snippetService.nextIdentity();
        Snippet snippet = new Snippet(snippetId);
        BeanUtils.copyProperties(snippetCreateForm, snippet, "snippetId");
        snippet.setCreator(new Creator(identityApplicationService.identity()));
        snippetService.add(snippet);
        return snippetService.getBySnippetId(snippetId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Snippet updateBySnippetId(String snippetId, SnippetModifyForm modifyVo) {
        Snippet snippet = snippetService.getBySnippetId(new SnippetId(snippetId));
        // 可修改属性
        snippet.setTitle(modifyVo.getTitle());
        snippet.setDescription(modifyVo.getDescription());
        snippet.setContent(modifyVo.getContent());
        snippet.setContentHtml(modifyVo.getContentHtml());
        snippet.setScope(modifyVo.getScope());
        snippetService.update(snippet);
        return snippetService.getBySnippetId(snippet.snippetId());
    }

    public void deleteBySnippetId(String snippetId) {
        snippetService.deleteBySnippetId(new SnippetId(snippetId));
    }

    public Page<SnippetCardVO> pageByKeyword(int page, int size, String sort, String keyword) {
        return snippetPagination.pageByKeyword(page, size, sort, keyword);
    }
}
