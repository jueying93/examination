package com.tjcu.examination.core.service.impl;

import com.tjcu.examination.core.dao.BaseDao;
import com.tjcu.examination.core.service.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/30 18:43
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    /*
    * 通过BaseServiceImpl类的实例，向本类中注入BaseDao*/
    private BaseDao<T> baseDao;
    public void setBaseDao(BaseDao<T> baseDao){
        this.baseDao = baseDao;
    }


    @Override
    public List<T> findObjects() {
        return baseDao.findObjects();
    }

    @Override
    public T findObjectById(Serializable id) {
        return baseDao.findObjectById(id);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }

    @Override
    public void svae(T entity) {
        baseDao.svae(entity);
    }
}
