/**
 * 
 */
package com.tjcu.examination.student.serivice.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.core.service.impl.BaseServiceImpl;
import com.tjcu.examination.student.dao.StudentDao;
import com.tjcu.examination.student.entity.ExaminationPaper;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.student.serivice.StudentService;
import com.tjcu.examination.teacher.entity.Question;

/**
 * @author Administrator
 *
 */
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

	private StudentDao studentDao;
	@Resource
	public void setStudentDao(StudentDao studentDao) {
		super.setBaseDao(studentDao);
		this.studentDao = studentDao;
	}


	@Override
	public Student findStudentByAccount(String account) {
		//判断当前系统时间是否在正式考试时间范围内
		Student student = studentDao.findStudentByAccount(account);
		//这里要对student做一个非空判断
		if(student != null){
			List<Menu> menuList = student.getMenuList();
			Date startDate = student.getTeacher().getStartDate();
			Date endDate = student.getTeacher().getEndDate();
			   
		    Iterator<Menu> menuIter = menuList.iterator();
		    //不在正考期间
		    if(!(startDate.getTime() <= new Date().getTime() && endDate.getTime() >= new Date().getTime())){
			    while (menuIter.hasNext()) {    
			        Menu menu = menuIter.next(); 
			        if(menu.getId().equals("11")){
						menuIter.remove();
						//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException    
					}
			    }    
			   //在正考期间
		    }else{
		    	while (menuIter.hasNext()) {    
			        Menu menu = menuIter.next(); 
			        if(menu.getId().equals("10")){
						menuIter.remove();
						//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException    
					}
			    }    
		    }
			student.setMenuList(menuList);
			
		}
		
		return student;
	}

	@Override
	public List<Question> createExaminationPaper() {
		//获取最大的rownum
		//int maxRownum = studentDao.findMaxRownumFromQuestion();
		//创建一个用于临时存放随机数值得数组（目的是为了不产生重复的随机数）
		int[] tempArr = new int[5];
		//根据rownum产生大于0小于rownum的整数
		int i = 0;
		List<Question> questionList1 = studentDao.findAllQuestions();
		int maxRownum = questionList1.size();
		List<Question> questionList2 = new ArrayList<Question>();
		Question question = null;
		while(i<tempArr.length){
			Random random = new Random();
			int temp = random.nextInt(maxRownum);
			//首先将产生的随机数和数组中已有的数进行比对，如果相同则不放入,否则放入
			for(int j=0;j<tempArr.length;j++){
				if(tempArr[j] == temp){
					break;
				}
				if(j == tempArr.length-1){
					//System.out.println(temp);
					tempArr[i] = temp;
					//从数据库中获取对应的question
					question = questionList1.get(temp);
					
					//question = studentDao.findQuestionByRownum(temp);
					questionList2.add(question);
					//当有数据成功存入数组，那么i就移向下一位
					i++;
				}
			}
		}
		return questionList2;
	}


	@Override
	public void saveExamintionPaper(ExaminationPaper examinationPaper) {
		studentDao.saveExamintionPaper(examinationPaper);
		
	}
	@Override
	public ExaminationPaper findExaminationById(String id) {
		
		return studentDao.findExaminationById(id);
	}

	@Override
	public Question findQuestionByID(String id) {
	
		return studentDao.findQuestionByID(id);
	}


	@Override
	public void updateStudentPasswrod(Student student) {
		studentDao.updateStudentPasswrod(student);
		
	}
	
	

}
