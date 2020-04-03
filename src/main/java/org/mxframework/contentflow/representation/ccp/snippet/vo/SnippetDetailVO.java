package org.mxframework.contentflow.representation.ccp.snippet.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SnippetDetailVO: 片段详情[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SnippetDetailVO extends SnippetBaseVO {
    /**
     * hasAccess: 是否拥有访问（修改，删除等）权限
     */
    private Boolean hasAccess;

}
