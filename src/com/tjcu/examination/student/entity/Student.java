package com.tjcu.examination.student.entity;

import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.teacher.entity.Teacher;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/29 13:43
 */
public class Student implements Serializable {

    private String id;
    private String account;
    private String username;
    private String password;
    //学生登录电脑时的ip地址
    private String ip;
    //专业
    private String major;
    //班级
    private String grade;
    //试卷
    private Set<ExaminationPaper> examinationPapers;
    //老师
    private Teacher teacher;
    //与学生相对应的菜单
    private List<Menu> menuList;

    public Student(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Set<ExaminationPaper> getExaminationPapers() {
        return examinationPapers;
    }

    public void setExaminationPapers(Set<ExaminationPaper> examinationPapers) {
        this.examinationPapers = examinationPapers;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    
    public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	/*@Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ip='" + ip + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", examinationPapers=" + examinationPapers +
                ", teacher=" + teacher +
                '}';
    }*/

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (account != null ? !account.equals(student.account) : student.account != null) return false;
        if (examinationPapers != null ? !examinationPapers.equals(student.examinationPapers) : student.examinationPapers != null)
            return false;
        if (grade != null ? !grade.equals(student.grade) : student.grade != null) return false;
        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (ip != null ? !ip.equals(student.ip) : student.ip != null) return false;
        if (major != null ? !major.equals(student.major) : student.major != null) return false;
        if (password != null ? !password.equals(student.password) : student.password != null) return false;
        if (teacher != null ? !teacher.equals(student.teacher) : student.teacher != null) return false;
        if (username != null ? !username.equals(student.username) : student.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (examinationPapers != null ? examinationPapers.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }
    */
}