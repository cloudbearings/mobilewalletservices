/*
 * GenerateUID.java
 *
 * Created on July 22, 2009, 4:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mobilewallet.util;

import java.util.Random;

/**
 *
 * @author Gopi
 */
public class GenerateUID {
    
    private static final String src = "23456789abcdefgjkmpqrstwxyz";
    private static final String collapceSrc = "123456789";
    public static String getUID(){
        
        String uid = "";
        Random r = new Random();
        for( int i = 0; i < 6; i++ ){
            uid = uid+src.charAt(r.nextInt(src.length()));
        }
        
        return uid;
    }
    
    public static String getCollapaceKey(){
        
        String uid = "";
        Random r = new Random();
        for( int i = 0; i < 6; i++ ){
            uid = uid+collapceSrc.charAt(r.nextInt(collapceSrc.length()));
        }
        
        return uid;
    }
}
