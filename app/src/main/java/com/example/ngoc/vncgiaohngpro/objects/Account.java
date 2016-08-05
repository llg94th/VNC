package com.example.ngoc.vncgiaohngpro.objects;

/**
 * Created by phimau on 8/4/2016.
 */
public class Account {
    private String id;
    private String password;
    private String regKey;
    private String numPhone1;
    private String numPhone2;

    public Account(String password, String id) {
        this.password = password;
        this.id = id;
    }

    public Account(String id, String password, String numPhone1, String regKey, String numPhone2) {
        this.id = id;
        this.password = password;
        this.numPhone1 = numPhone1;
        this.regKey = regKey;
        this.numPhone2 = numPhone2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumPhone2() {
        return numPhone2;
    }

    public void setNumPhone2(String numPhone2) {
        this.numPhone2 = numPhone2;
    }

    public String getNumPhone1() {
        return numPhone1;
    }

    public void setNumPhone1(String numPhone1) {
        this.numPhone1 = numPhone1;
    }

    public String getRegKey() {
        return regKey;
    }

    public void setRegKey(String regKey) {
        this.regKey = regKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
