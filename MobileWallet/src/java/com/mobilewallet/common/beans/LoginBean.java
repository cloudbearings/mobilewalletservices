package com.mobilewallet.common.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gopi
 */
@XmlRootElement
public class LoginBean {

    private String login;

    public LoginBean() {
        this.login = "login";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
