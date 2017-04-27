/**
 * 
 */
package com.tjcu.examination.student.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tjcu.examination.core.constant.Constant;
import com.tjcu.examination.student.entity.ExaminationPaper;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.student.serivice.StudentService;
import com.tjcu.examination.teacher.entity.Question;
import com.tjcu.examination.teacher.entity.Teacher;

/**
 * @author Administrator
 * 
 */
public class StudentAction extends ActionSupport {
	// 从IOC容器中获取studentService对象
	@Resource
	private StudentService studentService;

	// 收集sudent登录时的请求信息
	private Student student;
	//向页面展示考试题目
	private List<Question> questionList;
	//答题结束，将题目的id和选项保存在ExaminationPaper中
	private ExaminationPaper examinationPaper;


	// 处理请求登录
	public String studentHome() {
		// 获取request域
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().getContextMap().get("request");
		// 非空判断
		if (student != null) {

			Student student1 = studentService.findStudentByAccount(student.getAccount());
			if (student1 != null) {
				if (student.getPassword().equals(student1.getPassword())) {
					// 密码相同，则跳转到studentUI页面，而且还要讲student1放入到session中。
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					session.put(Constant.USER, student1);
					return "home";
				} else {
					// 密码错误
					request.put("error", "密码错误");
					return "index";
				}
			} else {
				// 如果从数据库中查出为空，说明用户名错误
				request.put("error", "账号错误");
				return "index";
			}

		}
		// 用户名和密码不能为null
		request.put("error", "用户名和密码不能为空");
		return "index";

	}
	//考试页面提交后的跳转
	public String Home(){
		return "home";
	}
	//查看个人信息
	public String studentInfoUI(){
		return "studentInfoUI";
	}
	//跳转到修改修改个人密码页面
	public String updateStudentPasswordUI(){
		return "updateStudentPasswordUI";
	}
	//修改密码
	public String updatePassword(){
		
		studentService.updateStudentPasswrod(student);
		return "success";//在一个页面上显示修改密码成功
	}

	//练习考试页面
	public String exerciseExaminationUI(){
		questionList = studentService.createExaminationPaper();
		//将试卷暂时保存在session中
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("questionList", questionList);
		return "exerciseExaminationUI";
	}
	
	//提交试卷，计算分数，并且将试卷保存数据库中
	public String submitPaper(){
		//System.out.println(examinationPaper.getQuestionOption().toString());
		//从session中取出questionList,并且销户它
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<Question> questionList = (List<Question>) session.get("questionList");
		session.remove("questionList");
		//计算分数
		int score = 0;
		//questionID ->optionID
		Map<String, String> questionOption = examinationPaper.getQuestionOption();
		for (Question question:questionList) {
			
			int yes = question.getOptions().indexOf(question.getAnswer());
			
			int optionID = Integer.parseInt(questionOption.get(question.getId()));
			if(yes == optionID){
				score+=5;
			}
		}
		examinationPaper.setScore(score);
		Student student = (Student) session.get(Constant.USER);
		examinationPaper.setStudent(student);
		examinationPaper.setPaperDate(new Date());
		System.out.println(score);
		//保存试卷到数据库
		studentService.saveExamintionPaper(examinationPaper);
		return "submitPaper";
	}
	//跳转到学生考卷历史信息界面
	public String examinationHistoryInfoUI(){
		Map<String, Object> session = ActionContext.getContext()
				.getSession();
		student = (Student) session.get(Constant.USER);
		//这里必须重新从数据库中查询，不能从session中获取 ，因为examinationPaperList必须是最新的。
		student = studentService.findObjectById(student.getId());
		return "examinationHistoryInfoUI";
	}
	//查询某一份试卷
	public String examinationHistoryPaper(){
		examinationPaper = studentService.findExaminationById(examinationPaper.getId());
		Map<String,String> map = examinationPaper.getQuestionOption();
		Set<String> setKey = map.keySet();
		questionList = new ArrayList<Question>();
		for(String questionID:setKey){
			questionList.add(studentService.findQuestionByID(questionID));
		}
		
		
		return "examinationHistoryPaper";
	}
	//正式考试页面
	public String normalExaminationUI(){
		//查询该学生是否已经参加过正式考试了
		Map<String, Object> session = ActionContext.getContext()
				.getSession();
		student = (Student) session.get(Constant.USER);
		//这里必须重新从数据库中查询，不能从session中获取 ，因为examinationPaperList必须是最新的。
		student = studentService.findObjectById(student.getId());
		for(ExaminationPaper examinationPaper1:student.getExaminationPapers()){
			int state = examinationPaper1.getState();
			if(state == 2){
				return "normalExminationError";
			}
		}
		
		
		questionList = studentService.createExaminationPaper();
		//将试卷暂时保存在session中
		session.put("questionList", questionList);
		
		return "normalExaminationUI";
	}
	
	
	
	

	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public ExaminationPaper getExaminationPaper() {
		return examinationPaper;
	}

	public void setExaminationPaper(ExaminationPaper examinationPaper) {
		this.examinationPaper = examinationPaper;
	}



}
