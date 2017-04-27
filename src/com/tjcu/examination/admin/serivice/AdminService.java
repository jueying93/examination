package com.tjcu.examination.admin.serivice;

import com.tjcu.examination.admin.entity.Admin;
import com.tjcu.examination.core.service.BaseService;
import com.tjcu.examination.teacher.entity.Teacher;

import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/30 18:54
 */
public interface AdminService extends BaseService<Admin> {

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
     * @Description:
     * @Param: * @param null
     * 添加老师
     */
    public void addTeacher(Teacher teacher);
    /**
     * @Author: hw【hw_jueying@163.com】
     * @Date: 2017/1/19 20:15
     * @Description:
     * @Param: * @param null
     * 根据姓名模糊查询老师
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
}
