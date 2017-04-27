package com.tjcu.examination.teacher.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/29 13:55
 */
public class Question implements Serializable {

    private String id;
    private String stem;
    private List<String> options;
    private String answer;
    private Teacher teacher;

    public Question() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

  /*  @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", stem='" + stem + '\'' +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                ", teacher=" + teacher +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (answer != null ? !answer.equals(question.answer) : question.answer != null) return false;
        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (options != null ? !options.equals(question.options) : question.options != null) return false;
        if (stem != null ? !stem.equals(question.stem) : question.stem != null) return false;
        if (teacher != null ? !teacher.equals(question.teacher) : question.teacher != null) return false;

        return true;
    }

 /*   @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (stem != null ? stem.hashCode() : 0);
        result = 31 * result + (options != null ? options.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }*/
}