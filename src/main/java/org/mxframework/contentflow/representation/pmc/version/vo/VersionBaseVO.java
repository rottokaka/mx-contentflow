package org.mxframework.contentflow.representation.pmc.version.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.version.VersionBase;

/**
 * VersionBaseDTO: 版本基础[DTO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VersionBaseVO extends VersionBase {

    public VersionBaseVO(VersionBase versionBase) {super(versionBase);}
}
