package com.guc.bachelor.facultyscheduler;

/**
 * Created by FRRR on 04/05/2016.
 */
public class Schedule {
    private int doctor_ID;
    private String SaturdayfirstSlot;
    private String Saturdaygap1;
    private String SaturdaysecondSlot;
    private String Saturdaygap2;
    private String SaturdaythirdSlot;
    private String Saturdaygap3;
    private String SaturdayfourthSlot;
    private String Saturdaygap4;
    private String SaturdayfifthSlot;
    private String Saturdaygap5;

    public Schedule(int doctor_ID, String SaturdayfirstSlot, String Saturdaygap1, String SaturdaysecondSlot, String Saturdaygap2, String SaturdaythirdSlot, String Saturdaygap3, String SaturdayfourthSlot, String Saturdaygap4, String SaturdayfifthSlot, String Saturdaygap5) {
        this.setDoctor_ID(doctor_ID);
        this.setSaturdayfirstSlot(SaturdayfirstSlot);
        this.setSaturdaygap1(Saturdaygap1);
        this.setSaturdaysecondSlot(SaturdaysecondSlot);
        this.setSaturdaygap2(Saturdaygap2);
        this.setSaturdaythirdSlot(SaturdaythirdSlot);
        this.setSaturdaygap3(Saturdaygap3);
        this.setSaturdayfourthSlot(SaturdayfourthSlot);
        this.setSaturdaygap4(Saturdaygap4);
        this.setSaturdayfifthSlot(SaturdayfifthSlot);
        this.setSaturdaygap5(Saturdaygap5);
    }

    public int getDoctor_ID() {
        return doctor_ID;
    }

    public void setDoctor_ID(int doctor_ID) {
        this.doctor_ID = doctor_ID;
    }

    public String getSaturdayfirstSlot() {
        return SaturdayfirstSlot;
    }

    public void setSaturdayfirstSlot(String saturdayfirstSlot) {
        SaturdayfirstSlot = saturdayfirstSlot;
    }

    public String getSaturdaygap1() {
        return Saturdaygap1;
    }

    public void setSaturdaygap1(String saturdaygap1) {
        Saturdaygap1 = saturdaygap1;
    }

    public String getSaturdaysecondSlot() {
        return SaturdaysecondSlot;
    }

    public void setSaturdaysecondSlot(String saturdaysecondSlot) {
        SaturdaysecondSlot = saturdaysecondSlot;
    }

    public String getSaturdaythirdSlot() {
        return SaturdaythirdSlot;
    }

    public void setSaturdaythirdSlot(String saturdaythirdSlot) {
        SaturdaythirdSlot = saturdaythirdSlot;
    }

    public String getSaturdaygap2() {
        return Saturdaygap2;
    }

    public void setSaturdaygap2(String saturdaygap2) {
        Saturdaygap2 = saturdaygap2;
    }

    public String getSaturdaygap3() {
        return Saturdaygap3;
    }

    public void setSaturdaygap3(String saturdaygap3) {
        Saturdaygap3 = saturdaygap3;
    }

    public String getSaturdaygap4() {
        return Saturdaygap4;
    }

    public void setSaturdaygap4(String saturdaygap4) {
        Saturdaygap4 = saturdaygap4;
    }

    public String getSaturdayfourthSlot() {
        return SaturdayfourthSlot;
    }

    public void setSaturdayfourthSlot(String saturdayfourthSlot) {
        SaturdayfourthSlot = saturdayfourthSlot;
    }

    public String getSaturdayfifthSlot() {
        return SaturdayfifthSlot;
    }

    public void setSaturdayfifthSlot(String saturdayfifthSlot) {
        SaturdayfifthSlot = saturdayfifthSlot;
    }

    public String getSaturdaygap5() {
        return Saturdaygap5;
    }

    public void setSaturdaygap5(String saturdaygap5) {
        Saturdaygap5 = saturdaygap5;
    }
}
