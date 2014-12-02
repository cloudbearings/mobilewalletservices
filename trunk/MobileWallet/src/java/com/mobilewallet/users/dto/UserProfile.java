/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.dto;

/**
 *
 * @author Gopi
 */
public class UserProfile extends User {

    private String dob;
    private String mcode;
    private String mnumber;
    private String occupation;
    private String income;
    private String gender;

    public UserProfile() {
    }

    public UserProfile(String dob, String mcode, String mnumber, String occupation, String income, String gender) {
        this.dob = dob;
        this.mcode = mcode;
        this.mnumber = mnumber;
        this.occupation = occupation;
        this.income = income;
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getMnumber() {
        return mnumber;
    }

    public void setMnumber(String mnumber) {
        this.mnumber = mnumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
