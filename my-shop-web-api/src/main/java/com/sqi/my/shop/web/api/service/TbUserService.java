package com.sqi.my.shop.web.api.service;

import com.sqi.my.shop.domain.TbUser;

/**
 * 会员管理
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 16:50
 */

public interface TbUserService {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
