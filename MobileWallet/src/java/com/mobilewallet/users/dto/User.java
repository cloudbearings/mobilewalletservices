package com.mobilewallet.users.dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gopi
 */
@XmlRootElement
public class User {
    
    private long userId;
    private String eid;
    private String email;
    private String pwd;
    private String name;
    private String imei;
    private String myRefCode;
    private String friendRefCode;
    private float amount;
    private String fbId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMyRefCode() {
        return myRefCode;
    }

    public void setMyRefCode(String myRefCode) {
        this.myRefCode = myRefCode;
    }

    public String getFriendRefCode() {
        return friendRefCode;
    }

    public void setFriendRefCode(String friendRefCode) {
        this.friendRefCode = friendRefCode;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }
}
