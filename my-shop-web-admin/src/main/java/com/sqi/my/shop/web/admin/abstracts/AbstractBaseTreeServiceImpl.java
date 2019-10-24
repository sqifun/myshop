package com.sqi.my.shop.web.admin.abstracts;

import com.sqi.my.shop.commons.persistence.BaseTreeDao;
import com.sqi.my.shop.commons.persistence.BaseTreeEntity;
import com.sqi.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-25 00:18
 */

public abstract class AbstractBaseTreeServiceImpl<T extends BaseTreeEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询全部
     * @return
     */
    public List<T> selectAll() {
        return dao.selectAll();
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 更新
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
        dao.update(entity);
    }

    /**
     * 根据父级节点ID查询所有子节点
     * @param pid
     * @return
     */
    @Override
    public List<T> selectByPid(Long pid) {
        return dao.selectByPid(pid);
    }

}
