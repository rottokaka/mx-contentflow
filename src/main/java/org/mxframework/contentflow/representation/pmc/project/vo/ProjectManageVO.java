package org.mxframework.contentflow.representation.pmc.project.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.project.ProjectBase;

/**
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectManageVO extends ProjectBaseVO {

    public ProjectManageVO(ProjectBase projectBase) {
        super(projectBase);
    }

    public ProjectManageVO() {
    }
}
