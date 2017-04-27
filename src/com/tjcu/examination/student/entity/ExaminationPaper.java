package com.tjcu.examination.student.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/29 14:06
 */
public class ExaminationPaper implements Serializable {
    private String id;
    private Student student;
    //map:key=问题 value=学生的选择
    private Map<String,String> questionOption;
    private Integer score;
    //每份试卷生成的时间，即考试时间
    private Date paperDate;
    //试卷是否是正式考试的试卷  1.练习  2.正式
    private Integer state;

    public ExaminationPaper(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPaperDate() {
        return paperDate;
    }

    public void setPaperDate(Date paperDate) {
        this.paperDate = paperDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Map<String, String> getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(Map<String, String> questionOption) {
        this.questionOption = questionOption;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

    

 /*   @Override
    public String toString() {
        return "ExaminationPaper{" +
                "id='" + id + '\'' +
                ", student=" + student +
                ", questionOption=" + questionOption +
                ", score=" + score +
                ", paperDate=" + paperDate +
                '}';
    }*/

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExaminationPaper that = (ExaminationPaper) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (paperDate != null ? !paperDate.equals(that.paperDate) : that.paperDate != null) return false;
        if (questionOption != null ? !questionOption.equals(that.questionOption) : that.questionOption != null)
            return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (questionOption != null ? questionOption.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (paperDate != null ? paperDate.hashCode() : 0);
        return result;
    }*/
}
