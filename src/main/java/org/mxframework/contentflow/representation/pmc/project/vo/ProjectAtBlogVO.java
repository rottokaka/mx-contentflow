package org.mxframework.contentflow.representation.pmc.project.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mxframework.contentflow.representation.pmc.project.ProjectBase;

/**
 * ProjectAtBlogVO: 博客话题[VO]
 *
 * @author mx
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectAtBlogVO extends ProjectBaseVO {

    /**
     * collected: 是否收录
     * aspect: #2
     */
    private Integer collected;

    public ProjectAtBlogVO (ProjectBase projectBase) {super(projectBase);}
}
