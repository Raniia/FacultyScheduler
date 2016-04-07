package com.guc.bachelor.facultyscheduler;

/**
 * Created by FRRR on 03/25/2016.
 */
public class Student {
    private int student_ID;
    private String student_name;
    private String student_email;
    private String student_password;
    private String student_faculty;
    private String student_major;
    private String student_group;

    public Student(int student_ID, String student_name, String student_email, String student_faculty, String student_major, String student_group) {
        this.setStudent_ID(student_ID);
        this.setStudent_name(student_name);
        this.setStudent_email(student_email);
        this.setStudent_faculty(student_faculty);
        this.setStudent_major(student_major);
        this.setStudent_group(student_group);
    }
    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public String getStudent_password() {
        return student_password;
    }

    public void setStudent_password(String student_password) {
        this.student_password = student_password;
    }

    public String getStudent_faculty() {
        return student_faculty;
    }

    public void setStudent_faculty(String student_faculty) {
        this.student_faculty = student_faculty;
    }

    public String getStudent_major() {
        return student_major;
    }

    public void setStudent_major(String student_major) {
        this.student_major = student_major;
    }

    public String getStudent_group() {
        return student_group;
    }

    public void setStudent_group(String student_group) {
        this.student_group = student_group;
    }
}
