package com.example.myapplication12;

public class student {
    public String name;
    public float id;
    public String clas;
    public int absence;
    public int late;

    public student(String name, float id, String clas, int absence, int late) {
        this.name = name;
        this.id = id;
        this.clas = clas;
        this.absence = absence;
        this.late = late;
    }

    public String getName() {
        return name;
    }

    public float getId() {
        return id;
    }

    public String getClas() {
        return clas;
    }

    public int getAbsence() {
        return absence;
    }

    public int getLate() {
        return late;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public void setLate(int late) {
        this.late = late;
    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", clas='" + clas + '\'' +
                ", absence=" + absence +
                ", late=" + late +
                '}';
    }
}
