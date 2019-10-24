package com.sqi.my.shop.web.admin.web.controller;

import com.sqi.my.shop.commons.constant.ConstantUtils;
import com.sqi.my.shop.domain.TbUser;
import com.sqi.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-11 14:29
 */

@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转登陆页面
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    /**
     * 登陆逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email,
                        @RequestParam(required = true) String password,
                        HttpServletRequest httpServletRequest,
                        Model model) {

        TbUser tbUser = tbUserService.login(email, password);

        //登陆失败
        if (tbUser == null) {
            model.addAttribute("message", "用户名密码错误，请重新输入");
            return this.login();
        }

        //登陆成功
        else {
            //将登陆信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }

    }

    /**
     * 注销
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return login();
    }

}
