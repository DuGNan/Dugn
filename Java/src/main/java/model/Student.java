package model;

import java.io.Serializable;

public class Student extends Animal implements Person,Cloneable, Serializable {
    private String name;
    private Integer age;
    // 年级
    private String grade;

    public String sex;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String hello() {
        return "你好,我是" + this.name ;
    }

}
