/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walletplus.util;

import java.util.Random;

/**
 *
 * @author Gopi
 */
public class Password {

    private static final String passwordString = "1Aa2Bb3CcDdEe4FfGgHhJj5KkLMmNnPp6Qq7Rv8Ss9TWXYZtwxyz";

    public static String getPassword() {
        int length = passwordString.length();
        char[] c = passwordString.toCharArray();
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(c[r.nextInt(length)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPassword());
    }
}
