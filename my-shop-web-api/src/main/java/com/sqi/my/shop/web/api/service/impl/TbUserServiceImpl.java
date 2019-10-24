package com.sqi.my.shop.web.api.service.impl;

import com.sqi.my.shop.domain.TbUser;
import com.sqi.my.shop.web.api.dao.TbUserDao;
import com.sqi.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-31 16:51
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {

        TbUser user = tbUserDao.login(tbUser);
        if (user != null) {
            //将明文密码加密
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());

            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
