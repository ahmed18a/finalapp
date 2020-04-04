package com.example.myapplication12;

public class schedule {
    public String [][] day ;

    public schedule(String[][] day) {
        this.day = day;
    }

    public String[] getdaysschedule(int i){
        String re[]= new String[8];
        for (int d=0;d<day[i][0].length();d++)
            re[d]=day[i][d];
        return re;
    }
}
