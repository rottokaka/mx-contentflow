package org.mxframework.contentflow.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MenuVO: 菜单[VO]
 *
 * @author mx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {

    /**
     * name: 名称
     */
    private String name;
    /**
     * url: 地址
     */
    private String url;
}
