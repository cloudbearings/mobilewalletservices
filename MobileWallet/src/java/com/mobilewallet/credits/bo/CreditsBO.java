/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.credits.bo;

import com.mobilewallet.credits.dao.CreditsDAO;
import com.mobilewallet.credits.dto.RechargeHistoryDTO;
import com.mobilewallet.credits.dto.WalletHistoryDTO;
import java.util.List;

/**
 *
 * @author Gopi
 */
public class CreditsBO {

    private static final CreditsDAO CREDITS_DAO = new CreditsDAO();

    public static int updateCredits(long userId, String isCorrect, int position) {
        return CREDITS_DAO.updateCredits(userId, isCorrect, position);
    }

    public static int creditCount(long userId) {
        return CREDITS_DAO.creditCount(userId);
    }

    public static List<WalletHistoryDTO> creditHistory(long userId, int begin, int end) {
        return CREDITS_DAO.creditHistory(userId, begin, end);
    }

    public static int debitCount(long userId) {
        return CREDITS_DAO.debitCount(userId);
    }

    public static List<RechargeHistoryDTO> debitHistory(long userId, int begin, int end) {
        return CREDITS_DAO.debitHistory(userId, begin, end);
    }
}
