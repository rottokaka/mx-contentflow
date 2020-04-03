package org.mxframework.contentflow.representation.pmc.version;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * VersionBase: 版本基础
 *
 * @author mx
 */
@Data
public class VersionBase {

    private String versionId;
    private String name;
    private String description;
    private String projectId;
    private Integer rank;

    public VersionBase(VersionBase versionBase) {
        BeanUtils.copyProperties(versionBase, this);
    }

    public VersionBase() {}
}
