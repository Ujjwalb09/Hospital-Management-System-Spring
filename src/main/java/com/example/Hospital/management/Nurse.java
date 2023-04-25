package com.example.Hospital.management;

public class Nurse {

    private int nurseId;

    private int age;

    private String name;

    private String qualification;

    public Nurse(int nurseId, int age, String name, String qualification) {
        this.nurseId = nurseId;
        this.age = age;
        this.name = name;
        this.qualification = qualification;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
