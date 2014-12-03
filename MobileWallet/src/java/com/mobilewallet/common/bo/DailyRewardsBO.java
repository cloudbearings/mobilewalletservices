/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.bo;

import com.mobilewallet.common.dao.DailyRewardsDAO;

/**
 *
 * @author Gopi
 */
public class DailyRewardsBO {

    private static final DailyRewardsDAO DAILY_REWARDS_DAO = new DailyRewardsDAO();

    public static int addDailyRewards(long userId, String ip, String imei) {
        return DAILY_REWARDS_DAO.addDailyRewards(userId, ip, imei);
    }
}
