package com.sqi.my.shop.web.admin.service.impl;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.commons.validator.BeanValidator;
import com.sqi.my.shop.domain.TbContent;
import com.sqi.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.sqi.my.shop.web.admin.dao.TbContentDao;
import com.sqi.my.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-21 20:31
 */
@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {


    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }

        //验证通过
        else {
            tbContent.setUpdated(new Date());
            //新增内容
            if(tbContent.getId() == null) {
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }

            //编辑内容
            else {
                update(tbContent);
            }

            return BaseResult.success("保存内容信息成功");
        }


    }

}
