/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gopi
 */
@XmlRootElement
public class DailyRewardsBean {

    private String loginCredited;

    public DailyRewardsBean() {
    }

    public DailyRewardsBean(String loginCredited) {
        this.loginCredited = loginCredited;
    }

    public String getLoginCredited() {
        return loginCredited;
    }

    public void setLoginCredited(String loginCredited) {
        this.loginCredited = loginCredited;
    }

}
