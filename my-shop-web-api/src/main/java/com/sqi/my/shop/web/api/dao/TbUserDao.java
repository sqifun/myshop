package com.sqi.my.shop.web.api.dao;

import com.sqi.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 会员管理
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 16:40
 */

@Repository
public interface TbUserDao {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
