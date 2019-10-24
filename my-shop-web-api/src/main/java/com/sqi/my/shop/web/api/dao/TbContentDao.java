package com.sqi.my.shop.web.api.dao;

import com.sqi.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-29 21:27
 */

@Repository
public interface TbContentDao {

    /**
     * 根据类别ID查询内容列表
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCatagoryId(TbContent tbContent);
}
