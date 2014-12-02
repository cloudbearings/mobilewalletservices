/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.util;

/**
 *
 * @author gopi
 */
public class MobileWalletID {

    private static final MobileWalletDE mobileWalletDE = new MobileWalletDE("MoBiLeWaLlET*uSErEmAil&pWd");

    public static String getEncryptedUserId(String id) {
        return mobileWalletDE.encryptURLSafe(id);
    }

    public static String getDecryptedUserId(String id) {
        return mobileWalletDE.decryptURLSafe(id);
    }
}
