package com.sqi.my.shop.commons.persistence;

import com.sqi.my.shop.commons.dto.BaseResult;

import java.util.List;

/**
 * 通用的树形结构逻辑层的基类
 * @author sqi
 * @description: TODO
 * @date 2019-08-25 00:00
 */

public interface BaseTreeService<T extends BaseEntity> {

    /**
     * 查询全部
     * @return
     */
    List<T> selectAll();

    /**
     * 保存信息
     * @param entity
     * @return
     */
    BaseResult save(T entity);

    /**
     * 删除信息
     * @param id
     */
    void delete(Long id);

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新信息
     * @param entity
     */
    void update(T entity);

    /**
     * 根据父级节点ID查询所有子节点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);

}
