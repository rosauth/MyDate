package com.android.mydate.model;

/**
 * Created by delaroy on 3/27/17.
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String interest;
    private String sex;
    private int age;
    private String password;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public String getInterest(){
        return interest;
    }

    public void setInterest(String interest){
        this.interest = interest;
    }

    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
       return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
