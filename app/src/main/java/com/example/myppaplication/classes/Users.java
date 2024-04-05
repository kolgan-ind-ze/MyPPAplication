package com.example.myppaplication.classes;

import java.io.Serializable;

public class Users implements Serializable {
    private int id;
    private String login;
    private String password;
    private String companyName;
    private String companyInn;
    private String companyOgrn;
    private int role;

    public Users(String login, String password, String companyName, String companyInn, String companyOgrn, int role) {
        this.login = login;
        this.password = password;
        this.companyName = companyName;
        this.companyInn = companyInn;
        this.companyOgrn = companyOgrn;
        this.role = role;
    }

    public Users(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyInn() {
        return companyInn;
    }

    public void setCompanyInn(String companyInn) {
        this.companyInn = companyInn;
    }

    public String getCompanyOgrn() {
        return companyOgrn;
    }

    public void setCompanyOgrn(String companyOgrn) {
        this.companyOgrn = companyOgrn;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
