package org.mxframework.contentflow.representation.pmc.project;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author mx
 */
@Data
public class ProjectBase {

    private String projectId;
    private String creatorIdentity;
    private String name;
    private String description;
    private String avatar;
    private String website;
    private Integer property;
    private Integer scope;
    private Integer contributionNotAllowed;
    private Integer archived;
    private Integer pinned;

    private String aboveProjectId;

    public ProjectBase(ProjectBase projectBase) {
        BeanUtils.copyProperties(projectBase, this);
    }

    public ProjectBase() {
    }
}
