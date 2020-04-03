package org.mxframework.contentflow.representation.pmc.version.dto;

import lombok.Data;

/**
 * VersionBaseDTO: 版本基础[DTO]
 *
 * @author mx
 * @date 2019-10-13
 */
@Data
public class VersionBaseDTO {

    /**
     * id: ID
     */
    private Long id;

    /**
     * name: 名称
     */
    private String name;

    /**
     * description: 描述
     */
    private String description;

}
