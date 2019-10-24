package com.sqi.my.shop.web.admin.dao;

import com.sqi.my.shop.commons.persistence.BaseDao;
import com.sqi.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-11 20:13
 */

@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    TbUser getByEmail(String email);

}
