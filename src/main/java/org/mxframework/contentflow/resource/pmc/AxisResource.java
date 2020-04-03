package org.mxframework.contentflow.resource.pmc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.pmc.AxisApplicationService;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author mx
 */
@Api("Axis RESTful API")
@RestController
@RequestMapping("/axes")
public class AxisResource {
    private static final Logger logger = LoggerFactory.getLogger(AxisResource.class);

    @Autowired
    private AxisApplicationService axisApplicationService;

    /**
     * 更新坐标，非项目级别
     *
     * @param products 产品集合
     * @param axis     坐标
     * @param pattern  更新模式
     * @return 结果[VO]
     */
    @ApiOperation("更新坐标，非项目级别")
    @PutMapping("/products")
    @PreAuthorize("hasRole('USER')")
    public ResultVO putAxis(@ApiParam("目标坐标") String axis
            , @ApiParam("产品集合") String products
            , @ApiParam("模式") String pattern) {
        axisApplicationService.putAxisOfProducts(axis, products, pattern);
        return ResultUtil.success();
    }

    /**
     * 新建坐标，项目级别，产品收录
     *
     * @param productId 产品ID
     * @param projectId 项目ID
     * @return 结果[VO]
     */
    @ApiOperation("新建坐标，项目级别，产品收录")
    @PostMapping("{productId}")
    public ResultVO postOnCollection(@ApiParam("产品ID") @PathVariable String productId
            , @ApiParam("项目ID") String projectId) {
        return ResultUtil.success(axisApplicationService.postByProductIdAndProductId(productId, projectId));
    }

    /**
     * 删除坐标，项目级别，产品收录
     *
     * @param productId 产品ID
     * @param projectId 项目ID
     * @return 结果[VO]
     */
    @ApiOperation("删除坐标，项目级别，产品收录")
    @DeleteMapping("{productId}")
    public ResultVO deleteOnCollection(@ApiParam("产品ID") @PathVariable String productId
            , @ApiParam("项目ID") String projectId) {
        axisApplicationService.deleteByProductIdAndProjectId(productId, projectId);
        return ResultUtil.success();
    }

    /**
     * 新建坐标，项目级别，项目投稿
     *
     * @param projectId 项目ID
     * @param products  产品集合
     * @return 结果[VO]
     */
    @ApiOperation("新建坐标，项目级别，项目投稿")
    @PostMapping("{projectId}/products")
    public ResultVO postOnContribution(@ApiParam("项目ID") @PathVariable String projectId
            , @ApiParam("产品集合") String products) {
        return ResultUtil.success(axisApplicationService.postAxesOfProjectAndProducts(projectId, products));
    }

    /**
     * 删除坐标，项目级别，项目投稿
     *
     * @param projectId 项目ID
     * @param products  产品集合[JSON]
     * @return 结果[VO]
     */
    @ApiOperation("删除坐标，项目级别，项目投稿")
    @DeleteMapping("{projectId}/products")
    public ResultVO deleteOnProjectLevel(@PathVariable String projectId, String products) {
        axisApplicationService.deleteOnProjectLevel(projectId, products);
        return ResultUtil.success();
    }

}
