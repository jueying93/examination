
package com.tjcu.examination.teacher.dao;

import com.tjcu.examination.core.dao.BaseDao;
import com.tjcu.examination.core.entity.Menu;
import com.tjcu.examination.student.entity.Student;
import com.tjcu.examination.teacher.entity.Question;
import com.tjcu.examination.teacher.entity.Teacher;

import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2017/1/22 22:37
 */
public interface TeacherDao extends BaseDao<Teacher> {

    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/1/22 23:40
    * @Description:根据账户查找teacher
    * @Param: * @param null
    */
   public Teacher findteacherByAccount(String account);
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date:  22:19
    * @Description:查询所有题目
    * @Param: * @param null
    */
    /*
    org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.tjcu.examination.teacher.entity.Question.options, no session or session was closed
    */
    public List<Question> findAllQuestions();
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/1/31 22:33
    * @Description:保存question  单条
    * @Param: * @param null
    */
    public void saveQuestion(Question question);
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date:  15:35
    * @Description:通过id查询问题
    * @Param: * @param null
    */
    public Question findQuestionByID(String id);
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/2/6 15:58
    * @Description:更新问题信息
    * @Param: * @param null
    */
    public void updateQuestionInfo(Question question);
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/2/6 17:51
    * @Description:删除题目
    * @Param: * @param null
    */
    public void deleteQuestion(String id);
   /**
   * @Author: hw【hw_jueying@163.com】
   * @Date: 2017/2/7 14:32
   * @Description:根据题目信息模糊查询题目和老师信息
   * @Param: * @param null
   */
    public List<Question> likeQueryByStemAndUsername(String stem,String username);
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/2/13 19:36
    * @Description:查询所有学生
    * @Param: * @param null
    */
    public List<Student> findAllStudent();
    /**
    * @Author: hw【hw_jueying@163.com】
    * @Date: 2017/2/13 21:55
    * @Description:保存学生
    * @Param: * @param null
    */
    public void saveStudent(Student student);
    /**
     * 根据学生姓名和老师姓名模糊查询student*/
    public List<Student> likeQueryByUsernameAndTeachername(String studentName,String teacherName);
   /**
    * 
    * @Author:hw[hw_jueying@163.com]
    * @Date:2017年4月12日 下午4:22:44
    * @Description:查询与老师相关联的菜单信息
    * @Param:
    * @return
    */
    public List<Menu> findMenuList();
    /**
     * 
     * @Author:hw[hw_jueying@163.com]
     * @Date:2017年4月15日 下午9:17:40
     * @Description:查询某一位老师所带的学生
     * @Param:
     * @param id
     * @return
     */
    public List<Student> findStudentByTeacherID(String id);
    /**
     * 
     * @Author:hw[hw_jueying@163.com]
     * @Date:2017年4月17日 上午11:57:32
     * @Description:设置正式考试时间
     * @Param:
     * @param teacher
     */
    
    public void setExaminationDate(Teacher teacher);
    /**
     * 
     * @Author:hw[hw_jueying@163.com]
     * @Date:Apr 20, 2017 2:28:18 PM
     * @Description:删除学生，根据学生id
     * @Param:
     * @param studentID
     */
    public void deleteStudent(String studentID);
    /**
     * 
     * @Author:hw[hw_jueying@163.com]
     * @Date:Apr 20, 2017 10:17:06 PM
     * @Description:修改老师信息
     * @Param:
     * @param teacher
     */
    public void updateTeacher(Teacher teacher);
    /**
     * 
     * @Author:hw[hw_jueying@163.com]
     * @Date:2017年4月23日 下午9:54:11
     * @Description:根据id查询学生
     * @Param:
     * @param id
     * @return
     */
    public Student findStudentByID(String id);
    /**
     * 
     * @Author:hw[hw_jueying@163.com]
     * @Date:2017年4月23日 下午9:55:13
     * @Description:修改学生密码
     * @Param:
     * @param student
     */
    public void updateStudentPassword(Student student);
    /**
     * 
     * @Author:hw[hw_jueying@163.com]
     * @Date:2017年4月25日 下午3:08:28
     * @Description:通过学生姓名和老师id模糊查询学生
     * @Param:
     * @param studentName
     * @param id
     * @return
     */
    public List<Student> likeQueryByUsernameAndTeacherID(String studentName,
			String id) ;


}
