package com.sqi.my.shop.web.api.service.impl;

import com.sqi.my.shop.domain.TbContent;
import com.sqi.my.shop.domain.TbContentCategory;
import com.sqi.my.shop.web.api.dao.TbContentDao;
import com.sqi.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-29 21:47
 */
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCatagoryId(Long categoryId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);

        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentDao.selectByCatagoryId(tbContent);
    }

}
