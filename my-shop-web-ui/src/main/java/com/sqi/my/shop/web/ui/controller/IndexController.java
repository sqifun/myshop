package com.sqi.my.shop.web.ui.controller;

import com.sqi.my.shop.web.ui.api.ContentsApi;
import com.sqi.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 09:52
 */
@Controller
public class IndexController {

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        //请求幻灯片
        requestContentsPPT(model);

        return "index";
    }

    /**
     * 请求幻灯片
     * @param model
     */
    private void requestContentsPPT(Model model) {
        List<TbContent> tbContents = ContentsApi.ppt();
        model.addAttribute("ppt", tbContents);
    }


}
