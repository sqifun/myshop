package com.sqi.my.shop.web.admin.web.controller;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.domain.TbUser;
import com.sqi.my.shop.web.admin.abstracts.AbstractBaseController;
import com.sqi.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 用户管理
 * @author sqi
 * @description: TODO
 * @date 2019-08-12 21:02
 */

@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser, TbUserService> {

    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;

        //id不为空，则从数据库获取
        if(id != null) {
            tbUser = service.getById(id);
        }

        else {
            tbUser = new TbUser();
        }

        return tbUser;
    }

    /**
     * 跳转到用户列表列
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "user_list";
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }

    /**
     * 保存用户信息
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser entity, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(entity);

        //保存成功
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }

        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if(StringUtils.isNoneBlank(ids)) {
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除数据成功");
        }

        else {
            baseResult = BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }

    /**
     * 显示用户详情
     * @param entity
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbUser entity) {
        return "user_detail";
    }


}
