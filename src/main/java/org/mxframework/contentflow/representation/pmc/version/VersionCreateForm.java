package org.mxframework.contentflow.representation.pmc.version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionCreateForm {

    private String projectId;
    private String name;
    private String description;
}
