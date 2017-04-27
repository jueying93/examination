package com.tjcu.examination.core.service;

import java.io.Serializable;
import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/30 18:40
 */
public interface BaseService<T> {

    /**
     * @Author: hw【hw_jueying@163.com】
     * @Date: 2016/12/29 11:49
     * @Description:保存实体对象
     * @Param:
     */
    public void svae(T entity);
    /**
     * @Author: hw【hw_jueying@163.com】
     * @Date: 2016/12/29 11:50
     * @Description:通过id删除实体对象
     * @Param: Serializable id
     */
    public void delete(Serializable id);
    /**
     * @Author: hw【hw_jueying@163.com】
     * @Date: 2016/12/29 11:52
     * @Description:更新实体对象
     * @Param:
     */
    public void update(T entity);
    /**
     * @Author: hw【hw_jueying@163.com】
     * @Date: 2016/12/29 11:53
     * @Description:通过id查找实体对象
     * @Param: * @param null
     */
    public T findObjectById(Serializable id);
    /**
     * @Author: hw【hw_jueying@163.com】
     * @Date: 2016/12/29 11:55
     * @Description:查询所有对象
     * @Param: * @param null
     */
    public List<T> findObjects();
}
