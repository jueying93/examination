package com.tjcu.examination.admin.serivice.impl;

import com.tjcu.examination.admin.dao.AdminDao;
import com.tjcu.examination.admin.entity.Admin;
import com.tjcu.examination.admin.serivice.AdminService;
import com.tjcu.examination.core.service.impl.BaseServiceImpl;
import com.tjcu.examination.teacher.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/30 18:58
 */
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    //从ioc容器中取出adminDao bean属性
    private AdminDao adminDao;
    @Resource
    public void setAdminDao(AdminDao adminDao){
        super.setBaseDao(adminDao);
        this.adminDao = adminDao;
    }

    @Override
    public Admin findAdminByAccount(String account) {
        return adminDao.findAdminByAccount(account);
    }

    @Override
    public List<Teacher> findTeachers() {
        return adminDao.findTeachers();
    }

    @Override
    public void addTeacher(Teacher teacher) {
        adminDao.addTeacher(teacher);
    }

    @Override
    public List<Teacher> findTeacherByName(String username) {
        return adminDao.findTeacherByName(username);
    }

    @Override
    public Teacher findTeacherById(String id) {
        return adminDao.findTeacherById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        adminDao.updateTeacher(teacher);
    }
}
