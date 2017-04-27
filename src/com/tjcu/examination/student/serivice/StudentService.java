/**
 * 
 */
package com.tjcu.examination.student.serivice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tjcu.examination.core.service.BaseService;
import com.tjcu.examination.student.entity.ExaminationPaper;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.teacher.entity.Question;

/**
 * @author Administrator
 *
 */

public interface StudentService extends BaseService<Student> {
	
	/**
	 * 
	 * @Author:hw[hw_jueying@163.com]
	 * @Date:2017年4月12日 下午5:22:42
	 * @Description:根据账号查询学生
	 * @Param:
	 * @param:account
	 * @return
	 */
	public Student findStudentByAccount(String account);
	/**
	 * 
	 * @Author:hw[hw_jueying@163.com]
	 * @Date:2017年4月13日 上午11:30:57
	 * @Description:创建考试试卷    ：这里返回的不是一个ExaminationPaper对象，只有在试卷提交的时候，才会创建这个对象
	 * @Param:
	 * @return
	 */
	public List<Question> createExaminationPaper();
	/**
	 * 
	 * @Author:hw[hw_jueying@163.com]
	 * @Date:2017年4月13日 下午6:32:25
	 * @Description:保存试卷
	 * @Param:
	 * @param examinationPaper
	 */
	public void saveExamintionPaper(ExaminationPaper examinationPaper);
	/**
	 * 
	 * @Author:hw[hw_jueying@163.com]
	 * @Date:2017年4月14日 下午9:07:13
	 * @Description:根据试卷id查询试卷
	 * @Param:
	 * @param id
	 * @return
	 */
	public ExaminationPaper findExaminationById(String id);
	/**
	 * 
	 * @Author:hw[hw_jueying@163.com]
	 * @Date:2017年4月14日 下午9:26:01
	 * @Description:根据question主键查询
	 * @Param:
	 * @return
	 */
	public Question findQuestionByID(String id);
	/**
	 * 
	 * @Author:hw[hw_jueying@163.com]
	 * @Date:2017年4月16日 下午10:24:10
	 * @Description:修改学生密码
	 * @Param:
	 */
	public void updateStudentPasswrod(Student student);
}
