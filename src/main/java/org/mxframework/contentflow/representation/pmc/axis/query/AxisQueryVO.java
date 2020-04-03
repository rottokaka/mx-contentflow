package org.mxframework.contentflow.representation.pmc.axis.query;

import lombok.Data;
import org.mxframework.contentflow.domain.model.ccp.product.ProductType;

import javax.validation.constraints.NotNull;

/**
 * AxisQueryVO: 坐标查询[VO]
 *
 * @author mx
 */
@Data
public class AxisQueryVO {

    private String sectionId;
    private String subjectId;
    private String versionId;

    @NotNull(message = "项目ID不能为空")
    private String projectId;

    public String getProductType() {
        return ProductType.PRODUCT_BLOG.toString().toUpperCase();
    }
}
