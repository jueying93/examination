package com.tjcu.examination.teacher.dao.impl;

import com.tjcu.examination.core.dao.impl.BaseDaoImpl;
import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.teacher.dao.TeacherDao;
import com.tjcu.examination.teacher.entity.Question;
import com.tjcu.examination.teacher.entity.Teacher;

import org.hibernate.Query;
import org.springframework.dao.support.DaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2017/1/22 22:38
 */
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao{

    @Override
    public Teacher findteacherByAccount(String account) {
        String hql = "from Teacher where account = ?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,account);
        List<Teacher> teacherList = query.list();
        /*
        * 避免出现这样的错误：
        * java.lang.IndexOutOfBoundsException: Index: 0, Size: 0*/
        if(teacherList.size() == 0){
            return null;
        }else{
        	Teacher teacher = teacherList.get(0);
        	teacher.setMenuList(this.findMenuList());
            return teacher;
        }

    }

    @Override
    public List<Question> findAllQuestions() {
        String hql = "from Question";
        Query query = getSession().createQuery(hql);
        List<Question> listQuestion = query.list();
        return listQuestion;
    }

    @Override
    public void saveQuestion(Question question) {
        getHibernateTemplate().save(question);
    }

    @Override
    public Question findQuestionByID(String id) {
        return getHibernateTemplate().get(Question.class,id);
    }

    @Override
    public void updateQuestionInfo(Question question) {
        getHibernateTemplate().update(question);
    }

    @Override
    public void deleteQuestion(String id) {
        getHibernateTemplate().delete(findQuestionByID(id));

    }

   

    @Override
    public List<Question> likeQueryByStemAndUsername(String stem,String username) {
        List<Question> questionList = new ArrayList<Question>();
        if(stem == null && "".equals(stem) && username == null && "".equals(username)){
            String hql = "from Question ";
            questionList = getHibernateTemplate().find(hql);
        }else if (stem != null && username == null && "".equals(username)){
            String hql = "from Question where stem like '%'||?||'%'";
            questionList = getHibernateTemplate().find(hql,stem);
        }else if(stem == null && "".equals(stem) && username != null ){
            String hql = "from Question q where q.teacher.username like '%'||?||'%'";
            questionList = getHibernateTemplate().find(hql,username);
        }else{
            String hql = "from Question q where q.stem like '%'||?||'%' and q.teacher.username like '%'||?||'%'";
            questionList = getHibernateTemplate().find(hql,stem,username);
        }

        return questionList;
    }

    @Override
    public List<Student> findAllStudent() {
        String hql = "from Student";
        List<Student> studnetList = getHibernateTemplate().find(hql);
        return studnetList;
    }

    @Override
    public void saveStudent(Student student) {
        getHibernateTemplate().save(student);
    }

	@Override
	public List<Student> likeQueryByUsernameAndTeachername(String studentName,
			String teacherName) {
		List<Student> studentList = new ArrayList<Student>();
        if(studentName == null && "".equals(studentName) && teacherName == null && "".equals(teacherName)){
            String hql = "from Student ";
            studentList = getHibernateTemplate().find(hql);
        }else if (studentName != null && teacherName == null && "".equals(teacherName)){
            String hql = "from Student where username like '%'||?||'%'";
            studentList = getHibernateTemplate().find(hql,studentName);
        }else if(studentName == null && "".equals(studentName) && teacherName != null ){
            String hql = "from Student s where s.teacher.username like '%'||?||'%'";
            studentList = getHibernateTemplate().find(hql,teacherName);
        }else{
            String hql = "from Student s where s.username like '%'||?||'%' and s.teacher.username like '%'||?||'%'";
            studentList = getHibernateTemplate().find(hql,studentName,teacherName);
        }

        return studentList;
	}
	
	
	public List<Student> likeQueryByUsernameAndTeacherID(String studentName,
			String id) {
		List<Student> studentList = new ArrayList<Student>();
        if(studentName == null && "".equals(studentName) ){
            String hql = "from Student s where s.teacher.id = ? ";
            studentList = getHibernateTemplate().find(hql,id);
        }else if (studentName != null ){
            String hql = "from Student where username like '%'||?||'%' and teacher.id = ?";
            studentList = getHibernateTemplate().find(hql,studentName,id);
        }

        return studentList;
	}


	@Override
	  public List<Menu> findMenuList(){
    	String hql = "from Menu m where m.state = 2";
    	Query query = getSession().createQuery(hql);
    	List<Menu> menuList = query.list();
		return menuList;
    	
    }

	@Override
	public List<Student> findStudentByTeacherID(String id) {
		String hql = "from Student s where s.teacher.id = ? ";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		return query.list();
		
	}

	@Override
	public void setExaminationDate(Teacher teacher) {
		String sql = "update t_teacher set startDate = ? , endDate = ? where id = ?";
		Query query = getSession().createSQLQuery(sql);
		query.setDate(0, teacher.getStartDate());
		query.setDate(1, teacher.getEndDate());
		query.setString(2, teacher.getId());
		query.executeUpdate();
		
	}

	@Override
	public void deleteStudent(String studentID) {
		
		getHibernateTemplate().delete(getHibernateTemplate().get(Student.class, studentID));
		
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		String sql = "update t_teacher set account = ?,password = ?,username = ? where = ?";
		Query query = getSession().createSQLQuery(sql);
		query.setString(0, teacher.getAccount());
		query.setString(1, teacher.getPassword());
		query.setString(2, teacher.getUsername());
		query.setString(3, teacher.getId());
		query.executeUpdate();
		
	}

	@Override
	public Student findStudentByID(String id) {
	
		return 	getHibernateTemplate().get(Student.class, id);
	}

	@Override
	public void updateStudentPassword(Student student) {
		String sql = "update t_student set password = ? where id = ?";
		Query query = getSession().createSQLQuery(sql);
		query.setString(0, student.getPassword());
		query.setString(1, student.getId());
		query.executeUpdate();
		
	}
	
	
	

	
    

}
