package com.guc.bachelor.facultyscheduler;

/**
 * Created by FRRR on 03/27/2016.
 */
public class Doctors {
    private String doctor_ID;
    private String doctor_name;
    private String doctor_email;
    private String doctor_password;
    private String doctor_office;
    private String doctor_department;
    private String doctor_telephone;

    public Doctors(String doctor_ID, String doctor_name, String doctor_email, String doctor_office, String doctor_department, String doctor_telephone) {
      this.setDoctor_ID(doctor_ID);
        this.setDoctor_name(doctor_name);
        this.setDoctor_email(doctor_email);
        this.setDoctor_office(doctor_office);
        this.setDoctor_department(doctor_department);
        this.setDoctor_telephone(doctor_telephone);
    }

    public Doctors(String doctor_name) {
        this.setDoctor_name(doctor_name);
    }

    public String getDoctor_ID() {
        return doctor_ID;
    }

    public void setDoctor_ID(String doctor_ID) {
        this.doctor_ID = doctor_ID;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_password() {
        return doctor_password;
    }

    public void setDoctor_password(String doctor_password) {
        this.doctor_password = doctor_password;
    }

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public String getDoctor_office() {
        return doctor_office;
    }

    public void setDoctor_office(String doctor_office) {
        this.doctor_office = doctor_office;
    }

    public String getDoctor_department() {
        return doctor_department;
    }

    public void setDoctor_department(String doctor_department) {
        this.doctor_department = doctor_department;
    }

    public String getDoctor_telephone() {
        return doctor_telephone;
    }

    public void setDoctor_telephone(String doctor_telephone) {
        this.doctor_telephone = doctor_telephone;
    }
}
