package com.tjcu.examination.admin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tjcu.examination.admin.entity.Admin;
import com.tjcu.examination.admin.serivice.AdminService;
import com.tjcu.examination.core.constant.Constant;
import com.tjcu.examination.teacher.entity.Teacher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/30 17:43
 */
public class AdminAction extends ActionSupport {

    @Resource
    private AdminService adminService;

    //获取登录页面的请求信息
    private Admin admin;
    //获取添加老师的信息
    private Teacher teacher;
    //获取页面根据用户名模糊查询的关键字
    private String username;
    //获取页面修改teacher的id
    private String id;


    //验证登录
    public String adminHome(){
        //获取request域
        Map<String,Object> request = (Map<String, Object>) ActionContext.getContext().getContextMap().get("request");
        //非空判断
        if(admin != null) {
            Admin admin1  = adminService.findAdminByAccount(admin.getAccount());
            if(admin1 != null) {
                if(admin.getPassword().equals(admin1.getPassword())){
                    //密码相同，则跳转到adminUI页面，而且还要讲admin1放入到session中。
                    Map<String ,Object> session = ActionContext.getContext().getSession();
                    session.put(Constant.USER,admin1);
                    return "home";
                }else{
                    //密码错误
                    request.put("error","密码错误");
                    return "index";
                }
            }
             else{
                    //如果从数据库中查出为空，说明用户名错误
                request.put("error","账号错误");
                return "index";
                }


        }
        //用户名和密码不能为null
        request.put("error","用户名和密码不能为空");
        return "index";

    }

    //查看当前admin的所有信息
    public String adminInfoUI(){

        return "adminInfoUI";
    }
    //跳转到修改管理员界面
    public String updateAdminUI(){
        Map<String ,Object> session = ActionContext.getContext().getSession();
        admin = (Admin)session.get(Constant.USER);
        //System.out.println(admin.toString());
        return "updateAdminUI";
    }
    //修改管理员信息
    public String updateAdmin(){
        //获取request域
        Map<String,Object> request = (Map<String, Object>) ActionContext.getContext().getContextMap().get("request");
        if(admin.getAccount() != null && admin.getUsername() != null && admin.getPassword() != null){
            //System.out.println(admin.toString());
            adminService.update(admin);
            request.put("success","更新成功");
            Map<String ,Object> session = ActionContext.getContext().getSession();
            //更新成功后，也要随之更新session中保存的admin
            session.put(Constant.USER,admin);
            return "success";
        }else{


            //提示用户不能有非空输入
            request.put("error","账号、密码或姓名不能为空");
            return "updateAdminUI";
        }

    }

    //查看所有老师的信息
    public String teacherListUI(){
        //获取request域
        Map<String,Object> request = (Map<String, Object>) ActionContext.getContext().getContextMap().get("request");
        //查询所有老师
      List<Teacher> teacherList =  adminService.findTeachers();
  /*      List<Integer> questionSizeList = new ArrayList<Integer>();
        List<Integer> studentSizeList = new ArrayList<Integer>();
        //将每个老师的学生数目和存储到题库的数目放入到request中
        for(Teacher teacher:teacherList){
            int questionSize = teacher.getQuestions().size();
            int studentSize = teacher.getStudents().size();
            questionSizeList.add(questionSize);
            studentSizeList.add(studentSize);
        }
        request.put("questionSizeList",questionSizeList);
        request.put("studentSizeList",studentSizeList);*/
        //将每个老师的questionSize和studentSize赋值
    
        request.put("teacherList",teacherList);
        return "teacherListUI";
    }
    //跳转到新增老师页面
    public String addTeacherUI(){
        return "addTeacherUI";
    }
    //新增老师
    public String addTeacher(){
        adminService.addTeacher(teacher);
        teacherListUI();
        return "teacherListUI";
    }
    //根据老师姓名模糊查询
    public String findTeacherByName(){
        List<Teacher> teacherList = adminService.findTeacherByName(username);
        //获取request域
        Map<String,Object> request = (Map<String, Object>) ActionContext.getContext().getContextMap().get("request");
        request.put("teacherList",teacherList);
        return "teacherListUI";
    }
    //跳转到修改teacher页面
    public String updateTeacherUI(){
         teacher = adminService.findTeacherById(id);
        return "updateTeacherUI";
    }
    //修改teacher信息
    public String updateTeacher(){
        adminService.updateTeacher(teacher);
        teacherListUI();
        return "teacherListUI";
    }
    //退出
    public String loginOut(){
        //清除session中保存的user
        Map<String,Object> session =  ActionContext.getContext().getSession();
        session.clear();
        return "index";
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
