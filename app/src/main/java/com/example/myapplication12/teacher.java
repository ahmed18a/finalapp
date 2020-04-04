package com.example.myapplication12;

public class teacher {
    public String name;
    public String username;
    public String password;
    public float id;
    public String birthday;


    public teacher(String name, String username, String password,float id,String birthday) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.id=id;
        this.birthday=birthday;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
