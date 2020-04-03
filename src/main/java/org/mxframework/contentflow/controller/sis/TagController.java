package org.mxframework.contentflow.controller.sis;

import org.mxframework.contentflow.application.sis.TagApplicationService;
import org.mxframework.contentflow.domain.model.ccp.product.ProductType;
import org.mxframework.contentflow.representation.sis.tag.form.TagModifyForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * TagController: 标签控制器
 *
 * @author mx
 */
@Controller
@RequestMapping("tag")
public class TagController {
    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagApplicationService tagApplicationService;

    /**
     * 获取标签主页页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping
    public ModelAndView getIndex(Model model) {
        model.addAttribute("tagCardVoList", tagApplicationService.list());
        return new ModelAndView("tag/index", "tagModel", model);
    }

    /**
     * 获取标签新建页面
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("create")
    public ModelAndView getCreate(Model model) {
        return new ModelAndView("tag/create", "tagModel", model);
    }

    /**
     * 获取标签新建页面
     * <p>输入框</p>
     *
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("createInInput")
    public ModelAndView getCreateInInput(Model model) {
        return new ModelAndView("tag/create_input", "tagModel", model);
    }

    /**
     * 获取标签详情页面
     *
     * @param tagId 标签ID
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("{tagId}/detail")
    public ModelAndView getTagDetail(@PathVariable String tagId, Model model) {
        model.addAttribute("flag", "tag");
        model.addAttribute("tagDetailVo", tagApplicationService.getDetailByTagId(tagId));
        model.addAttribute("userCardVoList", tagApplicationService.listUserCardVoByTagId(tagId));
        model.addAttribute("blogCardVoList", tagApplicationService.listBlogCardVoByTagId(tagId));
        return new ModelAndView("tag/detail", "tagModel", model);
    }

    /**
     * 获取标签修改页面
     *
     * @param tagId 标签ID
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("{tagId}/modify")
    public ModelAndView getTagModify(@PathVariable String tagId, Model model) {
        TagModifyForm tagModifyForm = tagApplicationService.getModifyFormByTagId(tagId);
        model.addAttribute("tagUpdateVO", tagModifyForm);
        return new ModelAndView("tag/modify", "tagModel", model);
    }

    /**
     * 获取标签用户页面
     *
     * @param tagId 标签ID
     * @param model 模型
     * @return 模型视图
     */
    @GetMapping("{tagId}/person")
    public ModelAndView getTagPerson(@PathVariable String tagId, Model model) {
        logger.info("获取标签用户页面");
        model.addAttribute("userCardVoList", tagApplicationService.listUserCardVoByTagId(tagId));
        return new ModelAndView("tag/person/card-list", "personModel", model);
    }

    /**
     * 获取标签产品页面
     *
     * @param tagId       标签ID
     * @param productType 产品类型
     * @return 模型视图
     */
    @GetMapping("{tagId}/product")
    public ModelAndView getTagProduct(@PathVariable String tagId, String productType, Model model) {
        logger.info("获取标签产品页面");
        switch (ProductType.valueOf(productType)) {
            case PRODUCT_SNIPPET:
                // TODO
            default:
                model.addAttribute("blogCardVoList", tagApplicationService.listBlogCardVoByTagId(tagId));
                return new ModelAndView("tag/product/blog/card-list", "productModel", model);
        }
    }

}
