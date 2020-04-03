package org.mxframework.contentflow.resource.sis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mxframework.contentflow.application.sis.TagApplicationService;
import org.mxframework.contentflow.representation.ResultVO;
import org.mxframework.contentflow.representation.sis.tag.form.ProductTagForm;
import org.mxframework.contentflow.representation.sis.tag.form.TagCreateForm;
import org.mxframework.contentflow.representation.sis.tag.form.TagModifyForm;
import org.mxframework.contentflow.representation.sis.tag.form.TagsOfProductInputForm;
import org.mxframework.contentflow.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mx
 */
@Api("Tag RESTful API")
@RestController
@RequestMapping("tags")
public class TagResource {

    @Autowired
    private TagApplicationService tagApplicationService;

    /**
     * 列出标签
     *
     * @return 结果[VO]
     */
    @ApiOperation("列出标签")
    @GetMapping
    public ResultVO list() {
        return ResultUtil.success(tagApplicationService.list());
    }

    /**
     * 列出标签
     *
     * @param property 标签性质
     * @return 标签条目[VO]集合
     */
    @ApiOperation("列出标签，通过性质")
    @GetMapping("property")
    public ResultVO listItemByProperty(@ApiParam("性质") String property) {
        return ResultUtil.success(tagApplicationService.getItemByProperty(property));
    }

    /**
     * 新建标签
     *
     * @param tagCreateForm 标签插入表单
     * @return 结果[VO]
     */
    @ApiOperation("新建标签")
    @PostMapping
    public ResultVO post(@ApiParam("标签创建表单") @Valid @RequestBody TagCreateForm tagCreateForm) {
        return ResultUtil.success(tagApplicationService.post(tagCreateForm));
    }

    /**
     * 查找标签，通过标签ID
     *
     * @param tagId 标签ID
     * @return 结果[VO]
     */
    @ApiOperation("获取标签")
    @GetMapping("{tagId}")
    public ResultVO getByTagId(@ApiParam("标签ID") @PathVariable String tagId) {
        return ResultUtil.success(tagApplicationService.getByTagId(tagId));
    }

    /**
     * 更新标签，通过标签ID
     *
     * @param tagId         标签ID
     * @param tagModifyForm 标签更新表单
     * @return 结果[VO]
     */
    @ApiOperation("更新标签")
    @PutMapping("{tagId}")
    public ResultVO putByTagId(@ApiParam("标签ID") @PathVariable String tagId
            , @ApiParam("标签修改表单") @Valid @RequestBody TagModifyForm tagModifyForm) {
        tagApplicationService.updateByTagId(tagId, tagModifyForm);
        return ResultUtil.success();
    }

    /**
     * 删除标签，通过标签ID
     *
     * @param tagId 标签ID
     * @return 结果[VO]
     */
    @ApiOperation("删除标签")
    @DeleteMapping("{tagId}")
    public ResultVO deleteByTagId(@ApiParam("标签ID") @PathVariable String tagId) {
        tagApplicationService.deleteByTagId(tagId);
        return ResultUtil.success();
    }

    /**
     * 更新标签，通过标签博客输入[VO]
     *
     * @param tagsOfProductInputForm 标签博客输入[VO]
     * @return 结果[VO]
     */
    @ApiOperation("新建产品标签，多个标签")
    @PostMapping("products")
    public ResultVO updateTagsOfProduct(@ApiParam("产品标签输入表单") @Valid @RequestBody TagsOfProductInputForm tagsOfProductInputForm) {
        tagApplicationService.addTagsToProduct(tagsOfProductInputForm.getTags(), tagsOfProductInputForm.getProductId(), tagsOfProductInputForm.getProductType());
        return ResultUtil.success();
    }

    /**
     * 新建产品标签
     *
     * @param productId      产品ID
     * @param productTagForm 产品标签表单
     * @return 结果[VO]
     */
    @ApiOperation("新建产品标签")
    @PostMapping("product/{productId}")
    public ResultVO post(@ApiParam("产品ID") @PathVariable String productId
            , @ApiParam("产品标签表单") @Valid @RequestBody ProductTagForm productTagForm) {
        return ResultUtil.success(tagApplicationService.postProductTagByProductId(productId, productTagForm));
    }

    /**
     * 删除标记，通过标签ID
     *
     * @param productId   产品ID
     * @param productType 产品类型
     * @param tagId       标签ID
     * @return 结果[VO]
     */
    @ApiOperation("删除产品标签")
    @DeleteMapping("product/{productId}")
    public ResultVO deleteByProductIdAndTagId(@ApiParam("产品ID") @PathVariable String productId
            , @ApiParam("产品类型") String productType
            , @ApiParam("标签ID") String tagId) {
        tagApplicationService.deleteByProductAndTagId(productId, productType, tagId);
        return ResultUtil.success();
    }

}
