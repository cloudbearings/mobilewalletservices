package com.mobilewallet.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author gopi
 */
public class MobileWalletDE {

    Cipher ecipher;
    Cipher dcipher;
    // 8-byte Salt
    byte[] salt = {
        (byte) 0xBB, (byte) 0x5F, (byte) 0xE7, (byte) 0x24,
        (byte) 0x56, (byte) 0xDE, (byte) 0xC2, (byte) 0xCB
    };
    // Iteration count
    int iterationCount = 9;

    public MobileWalletDE(String passPhrase) {
        try {
            // Create the key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            // Create the ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    public String encryptURLSafe(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return Base64.encodeBase64URLSafeString(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

    public String decryptURLSafe(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = Base64.decodeBase64(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        MobileWalletDE f = new MobileWalletDE("App already intaled in this device.");
        System.out.println("DID : " + f.decryptURLSafe("R93Tfl9A+u7SJ7WWh1xu41RvIBjiHNivwDO7YV5afarB0AEr6mW3ctMtjm6W20WfQgffsm+INpq5NDtfNf8iCY/urXBtepGf18XLe6x45eSlltQF7Gae0XfVgQ/0Wt1F9wBmxWi9aCfw1hJIsKGQxEet/zoSgp8a6bndaR+xxPcqb07zC6O72w=="));
    }

}
