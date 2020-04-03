package org.mxframework.contentflow.representation.pmc.project.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.project.ProjectBase;

import java.io.Serializable;

/**
 * ProjectBaseDTO: 项目基础[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectBaseDTO extends ProjectBase implements Serializable {
    private static final long serialVersionUID = 1L;

    public ProjectBaseDTO(ProjectBase projectBase) {
        super(projectBase);
    }

    public ProjectBaseDTO() {
    }

}
