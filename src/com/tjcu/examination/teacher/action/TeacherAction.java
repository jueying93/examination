package com.tjcu.examination.teacher.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tjcu.examination.core.constant.Constant;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.teacher.entity.Options;
import com.tjcu.examination.teacher.entity.Question;
import com.tjcu.examination.teacher.entity.Teacher;
import com.tjcu.examination.teacher.serivice.TeacherService;

import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2017/1/22 22:00
 */
public class TeacherAction extends ActionSupport {
	// 从ioc容器中取出teacherService
	@Resource
	private TeacherService teacherService;

	// 自动收集关于teacher的请求参数（登录，）
	private Teacher teacher;
	/*
	 * //向questionUI传输数据过去，这个照样可以通过EL表达式取值，是在valueStack里。 List<Question>
	 * questionList;
	 */
	// 用于收集关于question的请求信息
	private Question question;
	// options用于收集question选项
	private Options options;
	// 用于收集questionUI界面模糊查询的数据(题目信息和老师名信息)
	private String questionName;
	private String teacherName;

	private String username;// 用于根据姓名模糊查询student
	// 用于接收导入的excel-question文件
	private File questionExcel;
	private String questionExcelContentType;
	private String questionExcelFileName;
	// 用于收集student信息
	private Student student;
	// 所有学生（可以将查询出来的学生放入到action中）
	private List<Student> studentList;
	// 用于接收导入的excel-student文件
	private File studentExcel;
	private String studentExcelContentType;
	private String studentExcelFileName;
	//接收要被删除的行(学生/question)的id
	private  String[] selectedRow;
	//分数统计数据
	private Map<String,Object> statisticMap; 

	// 验证登录
	public String teacherHome() {
		// 获取request域
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().getContextMap().get("request");
		// 非空判断
		if (teacher != null) {

			Teacher teacher1 = teacherService.findteacherByAccount(teacher
					.getAccount());
			// System.out.println("teacher1"+teacher1);
			if (teacher1 != null) {
				if (teacher.getPassword().equals(teacher1.getPassword())) {
					// 密码相同，则跳转到teacherUI页面，而且还要讲teacher1放入到session中。
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					session.put(Constant.USER, teacher1);
			
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

	// 查看老师信息
	public String teacherInfoUI() {
		return "teacherInfoUI";
	}

	// 跳转到修改老师信息页面
	public String updateTeacherUI() {
		// 获取request域
		Map<String, Object> session = (Map<String, Object>) ActionContext
				.getContext().getSession();
		teacher = (Teacher) session.get(Constant.USER);
		return "updateTeacherUI";
	}

	// 修改老师信息
	public String updateTeacher() {
		// 获取request域
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().getContextMap().get("request");
		
			teacherService.updateTeacher(teacher);
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			// 更新成功后，也要随之更新session中保存的teacher
			session.put(Constant.USER, teacher);
			return "success";
		

	}

	// 查询所有题目
	public String questionUI() {
		// 获取request域
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().getContextMap().get("request");
		List<Question> questionList = teacherService.findAllQuestions();
		request.put("questionList", questionList);
		return "questionUI";
	}

	// 查询题目详细信息
	public String questionInfoUI() {
		question = teacherService.findQuestionByID(question.getId());
		return "questionInfoUI";
	}

	// 更新题目信息(跳转到更新页面)
	public String updateQuestionInfoUI() {
		question = teacherService.findQuestionByID(question.getId());
		List<String> list = question.getOptions();
		options = new Options();
		options.setOption1(list.get(0));
		options.setOption2(list.get(1));
		options.setOption3(list.get(2));
		options.setOption4(list.get(3));
		return "updateQuestionInfoUI";
	}

	// 保存更新后的题目信息
	public String updateQuestionInfo() {
		Teacher questionTeacher = teacherService.findObjectById(question
				.getTeacher().getId());
		question.setTeacher(questionTeacher);
		List<String> list = new ArrayList<String>();
		list.add(options.getOption1());
		list.add(options.getOption2());
		list.add(options.getOption3());
		list.add(options.getOption4());
		question.setOptions(list);
		teacherService.updateQuestionInfo(question);
		// 当题目详细信息保存后，调用questionUI()方法，跳转到questionUI界面
		return questionUI();

	}

	// 删除题目
	public String deleteQuestion() {
		if(selectedRow != null){
			for(String questionID :selectedRow){
				teacherService.deleteQuestion(questionID.trim());
			}
		}
		// 当题目删除后，调用questionUI()方法，跳转到questionUI界面
		return questionUI();
	}

	// 添加question（跳转到添加question页面）
	public String addQuestionUI() {
		return "addQuestionUI";
	}

	// 添加question（将数据保存到数据库中）
	public String addQuestion() {
		// 获取request域
		Map<String, Object> session = (Map<String, Object>) ActionContext
				.getContext().getSession();
		Teacher teacher1 = (Teacher) session.get(Constant.USER);
		List<String> list = new ArrayList<String>();
		list.add(options.getOption1());
		list.add(options.getOption2());
		list.add(options.getOption3());
		list.add(options.getOption4());
		question.setTeacher(teacher1);
		question.setOptions(list);
		teacherService.saveQuestion(question);
		// 当保存题目后，调用questionUI()方法，跳转到questionUI界面
		return questionUI();
	}

	// 根据题目信息模糊查询和老师姓名模糊查询
	public String likeQueryByStemAndUsername() {
		// 获取request域
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().getContextMap().get("request");
		List<Question> questionList = teacherService
				.likeQueryByStemAndUsername(questionName, teacherName);
		request.put("questionList", questionList);
		return "questionUI";
	}

	// 导出Question到excel
	public void exportQuestionToExcel() {
		try {
			// 1.查找Question列表
			// 2.导出
			HttpServletResponse response = ServletActionContext.getResponse();
			// 设置文件向相应类型
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String("Question列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			teacherService.exportQuestionToExcel(
					teacherService.findAllQuestions(), outputStream);// 查找出了所有Question
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 通过excel导入question到数据库中
	public String importQuestionFromExcel() {
		// 判断文件是否为空
		if (questionExcel != null) {
			// 判断文件是否为excel
			if (questionExcelFileName.matches("^.+\\.(?i)(xls|xlsx)$")) {
				Map<String, Object> session = ActionContext.getContext()
						.getSession();
				teacher = (Teacher) session.get(Constant.USER);
				teacherService.importQuestionFromExcel(questionExcel,
						questionExcelFileName, teacher);
			}
		}
		return questionUI();
	}

	// 查询所有学生
	public String studentUI() {
		studentList = teacherService.findAllStudent();
		return "studentUI";
	}

	// 跳转到新增学生页面
	public String saveStudentUI() {
		return "saveStudentUI";
	}

	// 新增学生
	public String saveStudent() {
		// 一定要进行非空判断
		// 谁的学生谁添加
		student.setTeacher((Teacher) ActionContext.getContext().getSession()
				.get(Constant.USER));
		teacherService.saveStudent(student);
		return studentUI();

	}
	//跳转到修改学生密码的页面
	public String  updateStudentPasswordUI(){
		//根据学生id查询学生信息
		student = teacherService.findStudentByID(student.getId());
		return "updateStudentPasswordUI";
	}
	public String updateStudentPassword(){
		teacherService.updateStudentPassword(student);
		return "success";
	}

	// 通过excel将student导入到数据库中
	public String importStudentFromExcel() {
		// 判断文件是否为空
		if (studentExcel != null) {
			// 判断文件是否为excel
			if (studentExcelFileName.matches("^.+\\.(?i)(xls|xlsx)$")) {
				Map<String, Object> session = ActionContext.getContext()
						.getSession();
				teacher = (Teacher) session.get(Constant.USER);
				teacherService.importStudentFromExcel(studentExcel,
						studentExcelFileName, teacher);
			}
		}
		return studentUI();

	} 

	// 将数据库中的student导出到excel
	public void exportStudentToExcel() {
		try {
			// 1.查找Student列表
			// 2.导出
			HttpServletResponse response = ServletActionContext.getResponse();
			// 设置文件向相应类型
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String("Student列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			teacher = (Teacher) ActionContext.getContext().getSession().get(Constant.USER);
			studentList = teacherService.findStudentByTeacherID(teacher.getId());
			teacherService.exportStudentToExcel(
					studentList, outputStream);// 查找出了所有Question
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 根据学生姓名模糊查询和老师姓名模糊查询student
	public String likeQueryByUsernameAndTeachername() {
		studentList = teacherService.likeQueryByUsernameAndTeachername(
				username, teacherName);
		return "studentUI";
	}
	//根据学生姓名和老师姓名模糊查询学生分数
	public String likeQueryByUsernameAndTeacherIDScore(){
		teacher =  (Teacher) ActionContext.getContext().getSession().get(Constant.USER);
		studentList = teacherService.likeQueryByUsernameAndTeacherID(username, teacher.getId());
		return "studentScoreUI";
	}
	// 删除学生
	public String deleteStudent() {
		if(selectedRow != null){
			for(String studentID :selectedRow){
				teacherService.deleteStudent(studentID.trim());
			}
		}
		// 当学生删除后，调用studentUI()方法，跳转到questionUI界面
		return studentUI();
		}
	//查询学生考试成绩
	public String studentScoreUI(){
		teacher = (Teacher) ActionContext.getContext().getSession().get(Constant.USER);
		studentList = teacherService.findStudentByTeacherID(teacher.getId());
		return "studentScoreUI";
	}
	//设置考试时间 UI
	public String setExaminationDateUI(){
		return "setExaminationDateUI";
	}
	//设置考试时间
	public String setExaminationDate(){
		teacherService.setExaminationDate(teacher);
		//更新session中teacher
		teacher = teacherService.findObjectById(teacher.getId());
		ActionContext.getContext().getSession().put(Constant.USER, teacher);
		
		return "success";
	}
	//跳转到统计页面
		public String scoreStatisticChartUI(){
			return "scoreStatisticChartUI";
		}
		
	//根据老师id获取对应的学生分数统计
	public String getSocreStatisticData(){
		//1.teacherid
		Teacher teacher = (Teacher) ActionContext.getContext().getSession().get(Constant.USER);
		//2.获取统计年度的每个月的投诉数
		statisticMap = new HashMap<String,Object>();
		statisticMap.put("msg", "success");
		statisticMap.put("chartData",teacherService.getStatisticDataByTeacherID(teacher.getId()));
		
		return "scoreStatisticData";
	}
	



	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public File getQuestionExcel() {
		return questionExcel;
	}

	public void setQuestionExcel(File questionExcel) {
		this.questionExcel = questionExcel;
	}

	public String getQuestionExcelContentType() {
		return questionExcelContentType;
	}

	public void setQuestionExcelContentType(String questionExcelContentType) {
		this.questionExcelContentType = questionExcelContentType;
	}

	public String getQuestionExcelFileName() {
		return questionExcelFileName;
	}

	public void setQuestionExcelFileName(String questionExcelFileName) {
		this.questionExcelFileName = questionExcelFileName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public File getStudentExcel() {
		return studentExcel;
	}

	public void setStudentExcel(File studentExcel) {
		this.studentExcel = studentExcel;
	}

	public String getStudentExcelContentType() {
		return studentExcelContentType;
	}

	public void setStudentExcelContentType(String studentExcelContentType) {
		this.studentExcelContentType = studentExcelContentType;
	}

	public String getStudentExcelFileName() {
		return studentExcelFileName;
	}

	public void setStudentExcelFileName(String studentExcelFileName) {
		this.studentExcelFileName = studentExcelFileName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}

	public Map<String, Object> getStatisticMap() {
		return statisticMap;
	}

	public void setStatisticMap(Map<String, Object> statisticMap) {
		this.statisticMap = statisticMap;
	}
	

}
