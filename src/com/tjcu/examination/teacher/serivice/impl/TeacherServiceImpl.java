package com.tjcu.examination.teacher.serivice.impl;

import com.tjcu.examination.core.service.impl.BaseServiceImpl;
import com.tjcu.examination.student.entity.ExaminationPaper;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.teacher.dao.TeacherDao;
import com.tjcu.examination.teacher.entity.Question;
import com.tjcu.examination.teacher.entity.Teacher;
import com.tjcu.examination.teacher.serivice.TeacherService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2017/1/22 22:44
 */
@Service("teacherService")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements
		TeacherService {
	// 从ioc容器中取出teacherDao bean属性
	private TeacherDao teacherDao;

	@Resource
	public void setTeacherDao(TeacherDao teacherDao) {
		super.setBaseDao(teacherDao);
		this.teacherDao = teacherDao;
	}

	@Override
	public Teacher findteacherByAccount(String account) {
		return teacherDao.findteacherByAccount(account);
	}

	@Override
	public List<Question> findAllQuestions() {
		List<Question> questionList = teacherDao.findAllQuestions();
		return questionList;
	}

	@Override
	public void saveQuestion(Question question) {
		teacherDao.saveQuestion(question);
	}

	@Override
	public Question findQuestionByID(String id) {
		return teacherDao.findQuestionByID(id);
	}

	@Override
	public void updateQuestionInfo(Question question) {
		teacherDao.updateQuestionInfo(question);
	}

	@Override
	public void deleteQuestion(String id) {
		teacherDao.deleteQuestion(id);
	}

	@Override
	public List<Question> likeQueryByStemAndUsername(String stem,
			String username) {
		return teacherDao.likeQueryByStemAndUsername(stem, username);
	}

	@Override
	public void exportQuestionToExcel(List<Question> questionList,
			ServletOutputStream outputStream) {

		// 1.创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 1.1、创建合并单元格对象
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 6);// 起始行号；结束行号；起始列号；结束列号；

		// 1.2、头标题样式
		HSSFCellStyle style1 = createCellStyle(workbook, (short) 16);
		// 1.3、列标题样式
		HSSFCellStyle style2 = createCellStyle(workbook, (short) 13);

		// 2.创建工作表
		HSSFSheet sheet = workbook.createSheet("questionList");
		// 合并单元格对象应用于工作表，所以在工作表中加载
		sheet.addMergedRegion(cellRangeAddress);
		// 设置默认列宽
		sheet.setDefaultColumnWidth(25);
		// 3.创建行
		// 3.1、创建头标题行：并且设置头标题
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell1 = row1.createCell(0);
		// 加载头标题单元格样式
		cell1.setCellStyle(style1);
		cell1.setCellValue("Question列表");
		// 3.2创建列标题行：并且设置列标题
		HSSFRow row2 = sheet.createRow(1);
		String[] titles = { "题目", "选项一", "选项二", "选项三", "选项四", "答案", "上传老师" };
		// 通过遍历给出列标题
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell2 = row2.createCell(i);
			// 加载列标题样式
			cell2.setCellStyle(style2);
			// 给标题赋值
			cell2.setCellValue(titles[i]);
		}

		// 4.操作单元格：将用户列表写入到excel中
		// 通过遍历给出user信息

		for (int j = 0; j < questionList.size(); j++) {
			HSSFRow row3 = sheet.createRow(j + 2);
			// 题目
			HSSFCell cell3 = row3.createCell(0);
			cell3.setCellValue(questionList.get(j).getStem());
			// 选项一
			HSSFCell cell4 = row3.createCell(1);
			cell4.setCellValue(questionList.get(j).getOptions().get(0));
			// 选项二
			HSSFCell cell5 = row3.createCell(2);
			cell5.setCellValue(questionList.get(j).getOptions().get(1));
			// 选项三
			HSSFCell cell6 = row3.createCell(3);
			cell6.setCellValue(questionList.get(j).getOptions().get(2));
			// 选项四
			HSSFCell cell7 = row3.createCell(4);
			cell7.setCellValue(questionList.get(j).getOptions().get(3));
			// 答案
			HSSFCell cell8 = row3.createCell(5);
			cell8.setCellValue(questionList.get(j).getAnswer());
			// 上传老师
			HSSFCell cell9 = row3.createCell(6);
			cell9.setCellValue(questionList.get(j).getTeacher().getUsername());
		}
		// 5.输出
		try {
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 创建单元格样式
	 * 
	 * @param workbook
	 *            工作簿
	 * @param fontSize
	 *            字体大小
	 * @return 单元格样式
	 * */

	private HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 1.2.1、设置字体样式
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontSize);// 设置字体为16号
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗字体
		// 将字体加载到样式中
		style.setFont(font);
		return style;
	}

	@Override
	/**
	 * @Author: hw【hw_jueying@163.com】
	 * @Date: 2017/2/7 18:03
	 * @Description:
	 * @Param: * @param userExcel
	 * @param questionExcelName
	 */
	public void importQuestionFromExcel(File userExcel,
			String questionExcelName, Teacher teacher) {
		try {
			FileInputStream inputStream = new FileInputStream(userExcel);
			// 判断导入的文件是03还是07的
			boolean is03Excel = questionExcelName.matches("^.+//.(?i)(xls)$");
			// 1.读取工作簿
			// Workbook workbook = is03Excel ? new HSSFWorkbook(): new
			// XSSFWorkbook();
			// 无法创建XSSFWorkBook对象
			Workbook workbook = new HSSFWorkbook(inputStream);
			// 2.读取工作表
			Sheet sheet = workbook.getSheet("Sheet1");
			// 3.读取行
			// System.out.println(sheet.getPhysicalNumberOfRows());
			//行列都是从0开始计数
			if (sheet.getPhysicalNumberOfRows() > 2) {
				Question question = null;
				// for循环遍历user数据
				int j = sheet.getPhysicalNumberOfRows();
				List<String> optionList;
				loop1:for(int i = 2; i < j; i++) {
					question = new Question();
					Row row = sheet.getRow(i);
					// 题目
					
					Cell cell0 = row.getCell(0);
					// 将要导入的question的stem和数据库中的question的stem比对，如果相等则不导入
					List<Question> listQuestion = findAllQuestions();
					for (int num = 0; num < listQuestion.size(); num++) {
						if (cell0.getStringCellValue().equals(
								listQuestion.get(num).getStem())) {
							continue loop1;
						}
					}
					/*
					 * POI操作Excel时偶尔会出现Cannot get a text value from a numeric cell的异常错误。
					异常原因：Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串并写入数据库时，就会出现Cannot get a text value from a numeric cell的异常错误。
					此异常常见于类似如下代码中：row.getCell(0).getStringCellValue()；
					解决办法：先设置Cell的类型，然后就可以把纯数字作为String类型读进来了：
					if(row.getCell(0)!=null){
			        	row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			        	stuUser.setPhone(row.getCell(0).getStringCellValue());
     				}*/
					question.setStem(cell0.getStringCellValue());
					// 选项一
					optionList = new ArrayList<String>();
					Cell cell1 = row.getCell(1);
					cell1.setCellType(Cell.CELL_TYPE_STRING);
					optionList.add(cell1.getStringCellValue());
					// 选项二
					Cell cell2 = row.getCell(2);
					cell2.setCellType(Cell.CELL_TYPE_STRING);
					optionList.add(cell2.getStringCellValue());
					// 选项三
					Cell cell3 = row.getCell(3);
					cell3.setCellType(Cell.CELL_TYPE_STRING);
					optionList.add(cell3.getStringCellValue());
					// 选项四
					Cell cell4 = row.getCell(4);
					cell4.setCellType(Cell.CELL_TYPE_STRING);
					optionList.add(cell4.getStringCellValue());
					// 答案
					Cell cell5 = row.getCell(5);
					cell5.setCellType(Cell.CELL_TYPE_STRING);
					question.setAnswer(cell5.getStringCellValue());
					// 添加老师
					question.setTeacher(teacher);
					// 将选项保存
					question.setOptions(optionList);

					// 5.保存question
					saveQuestion(question);

				}

			}
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> findAllStudent() {
		return teacherDao.findAllStudent();
	}

	@Override
	public void saveStudent(Student student) {
		teacherDao.saveStudent(student);
	}

	@Override
	public void exportStudentToExcel(List<Student> studentList,
			ServletOutputStream outpputStream) {
		// 1.创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 1.1、创建合并单元格对象
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 3);// 起始行号；结束行号；起始列号；结束列号；

		// 1.2、头标题样式
		HSSFCellStyle style1 = createCellStyle(workbook, (short) 16);
		// 1.3、列标题样式
		HSSFCellStyle style2 = createCellStyle(workbook, (short) 13);

		// 2.创建工作表
		HSSFSheet sheet = workbook.createSheet("studentList");
		// 合并单元格对象应用于工作表，所以在工作表中加载
		sheet.addMergedRegion(cellRangeAddress);
		// 设置默认列宽
		sheet.setDefaultColumnWidth(25);
		// 3.创建行
		// 3.1、创建头标题行：并且设置头标题
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell1 = row1.createCell(0);
		// 加载头标题单元格样式
		cell1.setCellStyle(style1);
		cell1.setCellValue("student列表");
		// 3.2创建列标题行：并且设置列标题
		HSSFRow row2 = sheet.createRow(1);
		String[] titles = { "账号", "用户名", "专业", "班级","成绩","任课老师" };
		// 通过遍历给出列标题
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell2 = row2.createCell(i);
			// 加载列标题样式
			cell2.setCellStyle(style2);
			// 给标题赋值
			cell2.setCellValue(titles[i]);
		}

		// 4.操作单元格：将student列表写入到excel中
		// 通过遍历给出student信息
		for (int j = 0; j < studentList.size(); j++) {
			HSSFRow row3 = sheet.createRow(j + 2);
			//账号
			HSSFCell cell3 = row3.createCell(0);
			cell3.setCellValue(studentList.get(j).getAccount());
			// 用户名
			HSSFCell cell4 = row3.createCell(1);
			cell4.setCellValue(studentList.get(j).getUsername());
			// 专业
			HSSFCell cell5 = row3.createCell(2);
			cell5.setCellValue(studentList.get(j).getMajor());
			// 班级
			HSSFCell cell6 = row3.createCell(3);
			cell6.setCellValue(studentList.get(j).getGrade());
			//成绩
			HSSFCell cell7 = row3.createCell(4);
			cell7.setCellValue("未参加考试");
			for(ExaminationPaper examinationPaper:studentList.get(j).getExaminationPapers()){
				if(examinationPaper.getState() == 2){
					cell7.setCellValue(examinationPaper.getScore());
					break;
				}
				cell7.setCellValue("未参加考试");
			}
			// 任课老师
			HSSFCell cell9 = row3.createCell(5);
			cell9.setCellValue(studentList.get(j).getTeacher().getUsername());
		}
		// 5.输出
		try {
			workbook.write(outpputStream);
			workbook.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void importStudentFromExcel(File studentExcel,
			String studentExcelName, Teacher teacher) {
		try {
			FileInputStream inputStream = new FileInputStream(studentExcel);
			// 判断导入的文件是03还是07的
			boolean is03Excel = studentExcelName.matches("^.+//.(?i)(xls)$");
			// 1.读取工作簿
			// Workbook workbook = is03Excel ? new HSSFWorkbook(): new
			// XSSFWorkbook();
			// 无法创建XSSFWorkBook对象
			Workbook workbook = new HSSFWorkbook(inputStream);
			// 2.读取工作表
			Sheet sheet = workbook.getSheet("sheet1");
			// 3.读取行
			// System.out.println(sheet.getPhysicalNumberOfRows());
			if (sheet.getPhysicalNumberOfRows() > 2) {
				Student student = null;
				// for循环遍历user数据
				int j = sheet.getPhysicalNumberOfRows();
				loop:for (int i = 2; i < j; i++) {
					student = new Student();
					Row row = sheet.getRow(i);
					// 账号
					Cell cell0 = row.getCell(0);
					/*
					 * POI操作Excel时偶尔会出现Cannot get a text value from a numeric cell的异常错误。
					异常原因：Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串并写入数据库时，就会出现Cannot get a text value from a numeric cell的异常错误。
					此异常常见于类似如下代码中：row.getCell(0).getStringCellValue()；
					解决办法：先设置Cell的类型，然后就可以把纯数字作为String类型读进来了：
					if(row.getCell(0)!=null){
			        	row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			        	stuUser.setPhone(row.getCell(0).getStringCellValue());
     				}*/
					cell0.setCellType(Cell.CELL_TYPE_STRING);

					// 将要导入的学生的account和数据库中的学生的account比对，如果相等则不导入
					List<Student> listStudent = findAllStudent();
					for (int num = 0; num < listStudent.size(); num++) {
						if (cell0.getStringCellValue().equals(
								listStudent.get(num).getAccount())) {
							continue loop;
						}
					}
					student.setAccount(cell0.getStringCellValue());
					// 姓名
					Cell cell1 = row.getCell(1);
					cell1.setCellType(Cell.CELL_TYPE_STRING);
					student.setUsername(cell1.getStringCellValue());
					// 专业
					Cell cell2 = row.getCell(2);
					student.setMajor(cell2.getStringCellValue());
					// 班级
					Cell cell3 = row.getCell(3);
					cell3.setCellType(Cell.CELL_TYPE_STRING);
					student.setGrade(cell3.getStringCellValue());
					// 设置初始密码（统一为123456）
					student.setPassword("123456");
					// 添加老师
					student.setTeacher(teacher);
					// 5.保存question
					saveStudent(student);
				}
			}
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Student> likeQueryByUsernameAndTeachername(String studentName,String teacherName) {
		
		return teacherDao.likeQueryByUsernameAndTeachername(studentName, teacherName);
	}

	@Override
	public List<Student> findStudentByTeacherID(String id) {
		
		return teacherDao.findStudentByTeacherID(id);
	}

	@Override
	public void setExaminationDate(Teacher teacher) {
		teacherDao.setExaminationDate(teacher);
		
	}

	@Override
	public void deleteStudent(String studentID) {
		teacherDao.deleteStudent(studentID);
		
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		teacherDao.updateTeacher(teacher);
		
	}

	@Override
	public Student findStudentByID(String id) {
		return teacherDao.findStudentByID(id);
	}

	@Override
	public void updateStudentPassword(Student student) {
		teacherDao.updateStudentPassword(student);
	}
	@Override
	public List<Map> getStatisticDataByTeacherID(String id) {
		List<Map>  resList = new ArrayList<Map>();
		//1.获取当前老师所属的学生
		List<Student> studentList = teacherDao.findStudentByTeacherID(id);
		//2.查出该学生的正式考试成绩
		int[] count = new int[11];
		
		for(Student student:studentList){
			int score = -1;
			Set<ExaminationPaper> examinationPaperList = student.getExaminationPapers();
			for(ExaminationPaper examinationPaper:examinationPaperList){
				if(examinationPaper.getState() == 2){
					score = examinationPaper.getScore();
					break;
				}
			}
			if(score == -1){
				count[0]++;
			}else{
				switch (score / 10) {
				//0~9
				case 0:
					count[1]++;
					break;
				//10~19
				case 1:
					count[2]++;
					break;
				//20~29
				case 2:
					count[3]++;
					break;
				//30~39
				case 3:
					count[4]++;
					break;
				//40~49
				case 4:
					count[5]++;
					break;
				//50~59
				case 5:
					count[6]++;
					break;
				//60~69
				case 6:
					count[7]++;
					break;
				//70~79
				case 7:
					count[8]++;
					break;
				//80~89
				case 8:
					count[9]++;
					break;
				//90~99
				case 9:
					count[10]++;
					break;
				//100
				case 10:
					count[10]++;
					break;
				default:
					
					break;
				}
		
			}
			}
		
			Map<String,Object> map ;
			for(int i = 0;i<count.length;i++){
				map = new HashMap<String,Object>();
				int a;
				int b;
				if(i == 0){
					map.put("label","缺考");
				}else if (i == 10) { //这个10，包含的范围是90-100，共11个字段
					 a = (i-1)*10;
					 b = i*10;
					map.put("label",a+ "~"+b);
				}else{
					 a = (i-1)*10;
					 b = (i-1)*10+9;
					map.put("label",a+ "~"+b);
				}
				map.put("value",count[i]);
				resList.add(map);
			}
			
		return resList;
		
	}

	@Override
	public List<Student> likeQueryByUsernameAndTeacherID(String studentName,
			String id) {
		
		return teacherDao.likeQueryByUsernameAndTeacherID(studentName, id);
	}

	

	
	

}
