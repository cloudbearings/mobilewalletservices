/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.bo;

import com.mobilewallet.common.dao.ReferralIncentiveDAO;

/**
 *
 * @author Gopi
 */
public class ReferralIncentiveBO {

    private static final ReferralIncentiveDAO REFERRAL_INCENTIVE_DAO = new ReferralIncentiveDAO();

    public static Object[] addReferralIncetive(long userId, String refCode, String imei, String ip) {
        return REFERRAL_INCENTIVE_DAO.addReferralIncetive(userId, refCode, imei, ip);
    }

    public static String showInvitationStrig(long userId) {
        return REFERRAL_INCENTIVE_DAO.showInvitationStrig(userId);
    }

    public static String[] getRAmount() {
        return REFERRAL_INCENTIVE_DAO.getRAmount();
    }
}
