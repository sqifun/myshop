package com.sqi.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-11 14:29
 */

@Controller
public class MainController {

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }

}
