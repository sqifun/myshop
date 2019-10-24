package com.sqi.my.shop.web.admin.service;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.commons.dto.PageInfo;
import com.sqi.my.shop.commons.persistence.BaseService;
import com.sqi.my.shop.domain.TbUser;

import java.util.List;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-11 21:36
 */

public interface TbUserService extends BaseService<TbUser> {

    /**
     * 用户登陆
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);

}
