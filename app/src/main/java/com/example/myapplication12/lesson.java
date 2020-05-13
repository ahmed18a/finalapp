package com.example.myapplication12;

public class lesson {
    public String day;
    public String clas;
    public int number;
    public String subject;
    public String learned;
    public String homework;



    public lesson(String clas, String day, int number, String subject,String learned,String homework) {
        this.day = day;
        this.clas = clas;
        this.number = number;
        this.subject = subject;
        this.learned=learned;
        this.homework=homework;

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "lesson{" +
                "day=" + day +
                ", clas='" + clas + '\'' +
                ", number=" + number +
                ", subject='" + subject + '\'' +
                '}';
    }
}
