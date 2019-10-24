package com.sqi.my.shop.web.api.service;

import com.sqi.my.shop.domain.TbContent;

import java.util.List;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-29 21:45
 */

public interface TbContentService {

    /**
     * 根据类别ID查询内容列表
     * @param categoryId
     * @return
     */
    List<TbContent> selectByCatagoryId(Long categoryId);
}
