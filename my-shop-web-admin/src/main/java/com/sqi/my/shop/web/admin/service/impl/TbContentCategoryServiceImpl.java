package com.sqi.my.shop.web.admin.service.impl;

import com.sqi.my.shop.commons.dto.BaseResult;
import com.sqi.my.shop.commons.validator.BeanValidator;
import com.sqi.my.shop.domain.TbContentCategory;
import com.sqi.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.sqi.my.shop.web.admin.dao.TbContentCategoryDao;
import com.sqi.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-20 21:42
 */
@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null) {
            return BaseResult.fail(validator);
        }

        else {
            TbContentCategory parent = entity.getParent();
            //如果没有选择父级节点，则默认设置为根目录
            if(parent == null || parent.getId() == null) {
                //0代表根目录
                parent.setId(0L);
            }

            entity.setUpdated(new Date());

            //新增
            if (entity.getId() == null) {
                entity.setCreated(new Date());
                entity.setIsParent(false);

                //判断当前新增的节点有没有父级节点
                if (parent.getId() != 0) {
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if(currentCategoryParent != null && !currentCategoryParent.getIsParent()) {
                        //为父级节点设置isParent为true
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }

                //父级节点为0，表示为根目录
                else {
                    //根目录一定是父级目录
                    entity.setIsParent(true);
                }

                dao.insert(entity);

            }

            //修改
            else {
                update(entity);
            }
            return BaseResult.success("保存分类信息成功");
        }
    }


}
