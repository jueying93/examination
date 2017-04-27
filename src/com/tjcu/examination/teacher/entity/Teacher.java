package com.tjcu.examination.teacher.entity;

import com.tjcu.examination.admin.entity.Admin;
import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.student.entity.Student;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/29 13:27
 */
public class Teacher implements Serializable{

    private String id;
    private String account;
    private String username;
    private String password;
    private Set<Student> students;
    private Set<Question> questions;
    private List<Menu> menuList;
    //设置考试时间
    private Date startDate;
    private Date endDate;

    public Teacher(){
    	
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (account != null ? !account.equals(teacher.account) : teacher.account != null) return false;
        if (id != null ? !id.equals(teacher.id) : teacher.id != null) return false;
        if (password != null ? !password.equals(teacher.password) : teacher.password != null) return false;
        if (questions != null ? !questions.equals(teacher.questions) : teacher.questions != null) return false;
        if (students != null ? !students.equals(teacher.students) : teacher.students != null) return false;
        if (username != null ? !username.equals(teacher.username) : teacher.username != null) return false;

        return true;
    }

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

/*    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        return result;
    }*/

    /*@Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", students=" + students +
                ", questions=" + questions +
                '}';
    }*/

}
