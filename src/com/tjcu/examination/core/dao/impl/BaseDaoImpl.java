package com.tjcu.examination.core.dao.impl;

import com.tjcu.examination.core.dao.BaseDao;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/29 11:15
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

    private Class<T> clazz;
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2016/12/29 11:58
    * @Description:在构造方法里使用反射得到T的真实类型
    * @Param: * @param null
    */
    public BaseDaoImpl(){
        //使用反射得到T的真实类型
        //获取当前new的对象的泛型的父类类型。
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        //获取第一个类型参数的真实类型。
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }
    @Override
    public void svae(Object entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(Serializable id) {
        getHibernateTemplate().delete(findObjectById(id),null);
    }

    @Override
    public void update(Object entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public T findObjectById(Serializable id) {
        return getHibernateTemplate().get(clazz,id);
    }

    @Override
    public List<T> findObjects() {
        Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
        return query.list();
    }


}
