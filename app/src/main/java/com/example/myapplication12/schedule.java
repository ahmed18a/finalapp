package com.example.myapplication12;

import java.util.Arrays;

public class schedule {
public lesson schedul[];
public String day;

    public schedule(lesson[] schedul, String day) {
        this.schedul = schedul;
        this.day = day;
    }


    public lesson[] getSchedul() {
        return schedul;
    }

    public void setSchedul(lesson[] schedul) {
        this.schedul = schedul;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "schedule{" +
                "schedul=" + Arrays.toString(schedul) +
                ", day='" + day + '\'' +
                '}';
    }
}
