/**
 * 
 */
package com.tjcu.examination.student.dao;

import java.util.List;

import com.tjcu.examination.core.dao.BaseDao;
import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.student.entity.ExaminationPaper;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.teacher.entity.Question;

/**
 * @author Administrator
 *
 */
public interface StudentDao extends BaseDao<Student>{
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
	 * @Date:2017年4月12日 下午5:27:41
	 * @Description:查询与学生相对应得菜单
	 * @Param:
	 * @return
	 */
	public List<Menu> findMenuList();

	/**
	 * 
	 * @Author:hw[hw_jueying@163.com]
	 * @Date:2017年4月13日 上午11:14:56
	 * @Description:从Question表中获取当前最大的rownum
	 * @Param:
	 * @return
	 */
	public List<Question> findAllQuestions();
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
