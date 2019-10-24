package com.sqi.my.shop.web.admin.web.controller;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.domain.TbContentCategory;
import com.sqi.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.sqi.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 * @author sqi
 * @description: TODO
 * @date 2019-08-20 21:44
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory, TbContentCategoryService> {

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id) {
        TbContentCategory tbContentCategory = null;

        //id不为空，则从数据库获取
        if(id != null) {
            tbContentCategory = service.getById(id);
        }

        else {
            tbContentCategory = new TbContentCategory();
        }

        return tbContentCategory;
    }

    /**
     * 跳转到内容列表页
     * @param model
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = service.selectAll();

        sortList(sourceList, targetList, 0L);

        model.addAttribute("tbContentCategories", targetList);
        return "content_category_list";
    }

    /**
     * 跳转到内容表单页
     * @param entity
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbContentCategory entity) {
        return "content_category_form";
    }

    /**
     * 保存
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory entity, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(entity);

        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        }

        else {
            model.addAttribute("baseResult", baseResult);
            return form(entity);
        }
    }

    /**
     * 树形结构
     * @param id
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id) {
        if (id == null) {
            id = 0L;
        }
        return service.selectByPid(id);
    }

}
