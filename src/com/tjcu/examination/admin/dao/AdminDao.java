package com.tjcu.examination.admin.dao;

import com.tjcu.examination.admin.entity.Admin;
import com.tjcu.examination.core.dao.BaseDao;
import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.teacher.entity.Teacher;

import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/30 18:31
 */
public interface AdminDao extends BaseDao<Admin> {
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/1/17 21:29
    * @Description:
    * @Param: * @param account
     * @Des:根据账号查询管理员
    */
    public Admin findAdminByAccount(String account);

    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/1/17 21:31
    * @Description:
    * @Param: * @param null
     * 查询所有老师
    */
    public List<Teacher> findTeachers();
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/1/17 23:05
    * @Description:添加老师
    * @Param: * @param null
     *
    */
    public void addTeacher(Teacher teacher);
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/1/19 20:15
    * @Description:根据姓名模糊查询老师
    * @Param: * @param null
     *
    */
    public List<Teacher> findTeacherByName(String username);

  /**
  * @Author: hw【hw_jueying@163.com】
  * @Date: 2017/1/19 21:08
  * @Description:根据id查询对应的老师
  * @Param: * @param null
  */
    public Teacher findTeacherById(String id);
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/1/19 21:17
    * @Description:更新teacher信息
    * @Param: * @param null
    */
    public void updateTeacher(Teacher teacher);
    /**
     * 
     * @Author:hw[hw_jueying@163.com]
     * @Date:2017年4月12日 下午4:13:25
     * @Description:查询当前管理员相关联的菜单
     * @Param:
     * @return
     */
    public List<Menu> findMenuList();

}
