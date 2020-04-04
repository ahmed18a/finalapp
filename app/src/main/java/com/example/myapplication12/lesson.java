package com.example.myapplication12;

public class lesson {
    public int day;
    public String clas;
    public int number;
    public String subject;
    public String learned;
    public String homework;

    public lesson(int day, String clas, int number, String subject, String learned, String homework) {
        this.day = day;
        this.clas = clas;
        this.number = number;
        this.subject = subject;
        this.learned = learned;
        this.homework = homework;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
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

    public String getLearned() {
        return learned;
    }

    public void setLearned(String learned) {
        this.learned = learned;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "lesson{" +
                "day=" + day +
                ", clas='" + clas + '\'' +
                ", number=" + number +
                ", subject='" + subject + '\'' +
                ", learned='" + learned + '\'' +
                ", homework='" + homework + '\'' +
                '}';
    }
}
