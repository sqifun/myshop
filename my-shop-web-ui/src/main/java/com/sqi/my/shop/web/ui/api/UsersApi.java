package com.sqi.my.shop.web.ui.api;

import com.google.code.kaptcha.Constants;
import com.sqi.my.shop.commons.utils.HttpClientUtils;
import com.sqi.my.shop.commons.utils.MapperUtils;
import com.sqi.my.shop.web.ui.dto.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 会员管理接口
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 18:30
 */

public class UsersApi {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    public static TbUser login(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", tbUser.getUsername()));
        params.add(new BasicNameValuePair("password", tbUser.getPassword()));

        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));

        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);

        return user;
    }

    /**
     * 检查验证码
     * @param tbUser
     * @param request
     * @return
     */
    public static boolean checkVerification(TbUser tbUser, HttpServletRequest request) {
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(verification, tbUser.getVerification())) {
            return true;
        }
        return false;
    }
}
