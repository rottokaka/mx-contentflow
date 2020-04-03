package org.mxframework.contentflow.resource.sis;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.sis.ReadingApplicationService;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.sis.reader.vo.ReadingAtProductOutlineVO;
import org.mxframework.contentflow.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mx
 */
@RestController
@RequestMapping("readings")
public class ReadingResource {

    @Autowired
    private ReadingApplicationService readingApplicationService;

    /**
     * 获取产品阅读概要
     * <p>
     * 产品阅读[VO] {@link ReadingAtProductOutlineVO}
     * </p>
     *
     * @param productId   产品ID
     * @param productType 产品类型
     * @return 结果[VO]
     */
    @ApiOperation("获取产品阅读概要")
    @GetMapping("/product")
    public ResultVO getByProduct(@ApiParam("产品ID") String productId, @ApiParam("产品类型") String productType) {
        return ResultUtil.success(readingApplicationService.getReaderAtProductOutlineVoByProduct(productId, productType));
    }

    /**
     * 获取读者阅读状态
     *
     * @param productId   产品ID
     * @param productType 产品类型
     * @return 结果[VO]
     */
    @ApiOperation("获取读者阅读状态")
    @GetMapping
    public ResultVO getByReaderAndProduct(@ApiParam("产品ID") String productId, @ApiParam("产品类型") String productType) {
        return ResultUtil.success(readingApplicationService.getOfReaderByProduct(productId, productType));
    }

    /**
     * 更新阅读状态
     * <p>
     * 阅读状态 READER_STATUS_LIKE; READER_STATUS_DISLIKE
     * </p>
     *
     * @param productId   产品ID
     * @param productType 产品类型
     * @param status      状态
     * @return 结果[VO]
     */
    @ApiOperation("更新读者阅读状态")
    @PutMapping
    public ResultVO updateByProduct(@ApiParam("产品ID") String productId
            , @ApiParam("产品类型") String productType
            , @ApiParam("阅读表态") String status) {
        return ResultUtil.success(readingApplicationService.updateOfReaderByProduct(productId, productType, status));
    }

}
