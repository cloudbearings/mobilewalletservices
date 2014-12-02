/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.bo;

import com.mobilewallet.users.dao.BalanceDAO;
import com.mobilewallet.users.dto.Balance;

/**
 *
 * @author Gopi
 */
public class BalanceBO {

    private static final BalanceDAO BALANCE_DAO = new BalanceDAO();

    public static Balance balanceInfo(long userId) {
        return BALANCE_DAO.balanceInfo(userId);
    }
}
