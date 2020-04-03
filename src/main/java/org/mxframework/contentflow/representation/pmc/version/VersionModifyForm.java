package org.mxframework.contentflow.representation.pmc.version;

import lombok.Data;

/**
 * VersionModifyForm: 版本修改表单
 *
 * @author mx
 */
@Data
public class VersionModifyForm {

    private String name;
    private String description;

    public VersionModifyForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public VersionModifyForm() {}
}
