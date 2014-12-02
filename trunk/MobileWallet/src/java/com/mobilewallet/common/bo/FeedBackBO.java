/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.bo;

import com.mobilewallet.common.dao.FeedBackDAO;

/**
 *
 * @author Gopi
 */
public class FeedBackBO {

    private static final FeedBackDAO FEED_BACK_DAO = new FeedBackDAO();

    public static void feedBack(long userId, String feedType, String feedText, String ip, String email) {
        FEED_BACK_DAO.feedBack(userId, feedType, feedText, ip, email);
    }
}
