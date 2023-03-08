package com.example.demo_mysql.pojo;



public class classinfo {


    private String username;
    private String courseName;
    private String courseDescription;
    private String teachTime;
    private Integer optionalPersonNum;
    private String classroom;

    @Override
    public String toString() {
        return "classinfo{" +
                "username='" + username + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", teachTime='" + teachTime + '\'' +
                ", optionalPersonNum=" + optionalPersonNum +
                ", classroom='" + classroom + '\'' +
                ", optionalMajor='" + optionalMajor + '\'' +
                ", teachMethod='" + teachMethod + '\'' +
                '}';
    }

    private String optionalMajor;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(String teachTime) {
        this.teachTime = teachTime;
    }

    public Integer getOptionalPersonNum() {
        return optionalPersonNum;
    }

    public void setOptionalPersonNum(Integer optionalPersonNum) {
        this.optionalPersonNum = optionalPersonNum;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getOptionalMajor() {
        return optionalMajor;
    }

    public void setOptionalMajor(String optionalMajor) {
        this.optionalMajor = optionalMajor;
    }

    public String getTeachMethod() {
        return teachMethod;
    }

    public void setTeachMethod(String teachMethod) {
        this.teachMethod = teachMethod;
    }

    private String teachMethod;

}
