package com.tjcu.examination.admin.dao.impl;

import com.tjcu.examination.admin.dao.AdminDao;
import com.tjcu.examination.admin.entity.Admin;
import com.tjcu.examination.core.dao.impl.BaseDaoImpl;
import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.teacher.entity.Teacher;

import org.hibernate.Query;

import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/30 18:32
 */
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{
    @Override
    public Admin findAdminByAccount(String account) {
        //管理员用户是我数据库总直接插入的，由于插入后，没有做事物提交的操作，后面导致一直报错
        String hql = "from Admin a where a.account = ?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,account);
        List<Admin> list = query.list();
        //List<Admin> list = getSession().createSQLQuery("select * from T_ADMIN where account = 'admin'").addEntity(Admin.class).list();
       if(list.size() == 0){
           return null;
        }else{
        	Admin admin = list.get(0);
        	admin.setMenuList(findMenuList());
            return admin;
       }


    }
    public List<Menu> findMenuList(){
    	String hql = "from Menu m where m.state = 1";
    	Query query = getSession().createQuery(hql);
    	List<Menu> menuList = query.list();
		return menuList;
    	
    }

    @Override
    public List<Teacher> findTeachers() {
        String hql = "from Teacher";
        List<Teacher> teacherList = getHibernateTemplate().find(hql);
        return teacherList;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        getHibernateTemplate().save(teacher);
    }

    @Override
    public List<Teacher> findTeacherByName(String username) {
        String hql = "from Teacher where username like '%'||?||'%'";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,username);
        List<Teacher> teacherList = query.list();
        return teacherList;
    }

    @Override
    public Teacher findTeacherById(String id) {
        Teacher teacher = getHibernateTemplate().get(Teacher.class,id);
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        getHibernateTemplate().update(teacher);
    }

}
