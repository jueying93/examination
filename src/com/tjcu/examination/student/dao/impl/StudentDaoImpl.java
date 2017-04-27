/**
 * 
 */
package com.tjcu.examination.student.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.tjcu.examination.core.dao.impl.BaseDaoImpl;
import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.student.dao.StudentDao;
import com.tjcu.examination.student.entity.ExaminationPaper;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.teacher.entity.Question;
import com.tjcu.examination.teacher.entity.Teacher;

/**
 * @author Administrator
 *
 */
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	
	
	
	
	
	@Override
	public Student findStudentByAccount(String account) {
		String hql = "from Student where account = ?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,account);
        List<Student> studentList = query.list();
        /*
        * 避免出现这样的错误：
        * java.lang.IndexOutOfBoundsException: Index: 0, Size: 0*/
        if(studentList.size() == 0){
            return null;
        }else{
        	Student student = studentList.get(0);
        	student.setMenuList(this.findMenuList());
            return student;
        }
		
	}

	
	@Override
	public List<Menu> findMenuList() {
		String hql = "from Menu m where m.state = 3";
    	Query query = getSession().createQuery(hql);
    	List<Menu> menuList = query.list();
		return menuList;
	
	}
	
	@Override
	public List<Question> findAllQuestions() {
		String hql = "from Question ";
		Query query = getSession().createQuery(hql);
		List<Question> questionList = query.list();
		return questionList;
	}


	/* (non-Javadoc)
	 * @see com.tjcu.examination.core.dao.BaseDao#svae(java.lang.Object)
	 */
	@Override
	public void svae(Student entity) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.tjcu.examination.core.dao.BaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(Student entity) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.tjcu.examination.student.dao.StudentDao#saveExamintionPaper(com.tjcu.examination.student.entity.ExaminationPaper)
	 */
	@Override
	public void saveExamintionPaper(ExaminationPaper examinationPaper) {
		getHibernateTemplate().save(examinationPaper);
		
	}

	@Override
	public ExaminationPaper findExaminationById(String id) {
		
		return getHibernateTemplate().get(ExaminationPaper.class, id);
	}

	@Override
	public Question findQuestionByID(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Question.class, id);
	}


	@Override
	public void updateStudentPasswrod(Student student) {
		String sql = "update t_student set password = ? where id = ?";
		Query query = getSession().createSQLQuery(sql);
		query.setString(0, student.getPassword());
		query.setString(1, student.getId());
		query.executeUpdate();
		
	}
	

	

}
