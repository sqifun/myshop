package com.sqi.my.shop.web.ui.controller;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.commons.utils.EmailSendUtils;
import com.sqi.my.shop.web.ui.api.UsersApi;
import com.sqi.my.shop.web.ui.constant.SystemConstants;
import com.sqi.my.shop.web.ui.dto.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 18:58
 */
@Controller
public class LoginController {

    @Autowired
    private EmailSendUtils emailSendUtils;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登录
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {
        //验证验证码
        if (!UsersApi.checkVerification(tbUser, request)) {
            model.addAttribute("baseResult", BaseResult.fail("验证码输入错误，请重新输入"));
            return "login";
        }

        TbUser user = UsersApi.login(tbUser);

        //登录失败
        if (user == null) {
            model.addAttribute("baseResult", BaseResult.fail("用户名或密码错误，请重新输入"));
            return "login";
        }

        //登录成功
        else {
            emailSendUtils.send("用户登录", String.format("用户【%s】登录 Myshop", user.getUsername()), "s6119q@163.com");
            //将会员信息放入 Session
            request.getSession().setAttribute(SystemConstants.SESSION_USER_KEY, user);
            return "redirect:/index";
        }
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }
}
