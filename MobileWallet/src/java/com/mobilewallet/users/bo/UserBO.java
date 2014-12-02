/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.bo;

import com.mobilewallet.users.dao.UserDAO;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.users.dto.UserProfile;

/**
 *
 * @author Gopi
 */
public class UserBO {

    private static final UserDAO USER_DAO = new UserDAO();

    public static User userInfo(long userId) {
        return USER_DAO.userInfo(userId);
    }
    
    public static UserProfile userProfile(long userId) {
        return USER_DAO.userProfile(userId);
    }
    
    public static int updateProfile(long userId, String mCode, String mobileNumber, String dob, String gender, String occupation, String income) {
        return USER_DAO.updateProfile(userId, mCode, mobileNumber, dob, gender, occupation, income);
    }
    
    public static String getGCMID(long userId) {
        return USER_DAO.getGCMID(userId);
    }
}
