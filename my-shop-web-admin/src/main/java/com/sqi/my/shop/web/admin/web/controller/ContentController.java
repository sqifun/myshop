package com.sqi.my.shop.web.admin.web.controller;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.domain.TbContent;
import com.sqi.my.shop.web.admin.abstracts.AbstractBaseController;
import com.sqi.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 内容管理
 * @author sqi
 * @description: TODO
 * @date 2019-08-21 21:38
 */
@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent, TbContentService> {


    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;

        //id不为空，则从数据库获取
        if(id != null) {
            tbContent = service.getById(id);
        }

        else {
            tbContent = new TbContent();
        }

        return tbContent;
    }


    /**
     * 跳转到内容列表页
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "content_list";
    }

    /**
     * 跳转内容表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }

    /**
     * 保存信息
     * @param tbContent
     * @param model
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContent);

        //保存成功
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        }

        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }
    }

    /**
     * 删除信息
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if(StringUtils.isNoneBlank(ids)) {
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除内容成功");
        }

        else {
            baseResult = BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }

    /**
     * 显示详情
     * @param tbContent
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent tbContent) {
        return "content_detail";
    }

}
