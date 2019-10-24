package com.sqi.my.shop.web.admin.abstracts;

import com.sqi.my.shop.commons.dto.PageInfo;
import com.sqi.my.shop.commons.persistence.BaseDao;
import com.sqi.my.shop.commons.persistence.BaseEntity;
import com.sqi.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sqi
 * @description: TODO
 * @date 2019-08-25 10:17
 */

public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 更新信息
     * @param tbUser
     */
    @Override
    public void update(T tbUser) {
        dao.update(tbUser);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteMulti(String[] ids) {
        dao.deleteMulti(ids);
    }

    /**
     * 查询总条数
     * @param tbUser
     * @return
     */
    @Override
    public int count(T tbUser) {
        return dao.count(tbUser);
    }

    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count(entity);

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("pageParams", entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));

        return pageInfo;
    }

}
