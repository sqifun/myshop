package com.sqi.my.shop.commons.persistence;

import java.util.List;

/**
 * 通用的树形结构接口
 * @author sqi
 * @description: TODO
 * @date 2019-08-24 23:54
 */

public interface BaseTreeDao<T extends BaseEntity> {

    /**
     * 查询全部数据
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新
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
