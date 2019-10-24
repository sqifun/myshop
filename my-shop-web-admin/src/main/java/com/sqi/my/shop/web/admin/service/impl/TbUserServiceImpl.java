package com.sqi.my.shop.web.admin.service.impl;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.commons.validator.BeanValidator;
import com.sqi.my.shop.domain.TbUser;
import com.sqi.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.sqi.my.shop.web.admin.dao.TbUserDao;
import com.sqi.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-11 21:37
 */

@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements TbUserService {

    @Override
    public BaseResult save(TbUser tbUser) {

        String validator = BeanValidator.validator(tbUser);
        //验证不通过
        if(validator != null) {
            return BaseResult.fail(validator);
        }
        else {
            tbUser.setUpdated(new Date());
            //新增用户
            if(tbUser.getId() == null) {
                //密码加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                dao.insert(tbUser);
            }

            //编辑用户
            else {
                update(tbUser);
            }

            return BaseResult.success("保存用户信息成功");
        }

    }


    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
        if (tbUser != null) {
            //明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            if(md5Password.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }

}
