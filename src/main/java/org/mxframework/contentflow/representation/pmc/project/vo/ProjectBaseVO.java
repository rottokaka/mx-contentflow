package org.mxframework.contentflow.representation.pmc.project.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.project.ProjectBase;

/**
 * ProjectBaseVO: 项目基础[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectBaseVO extends ProjectBase {

    public ProjectBaseVO(ProjectBase projectBase) {
        super(projectBase);
    }

    public ProjectBaseVO() {
    }

}
