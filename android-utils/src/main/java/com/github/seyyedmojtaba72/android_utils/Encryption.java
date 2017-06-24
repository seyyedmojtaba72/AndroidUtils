package com.github.seyyedmojtaba72.android_utils;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class Encryption {
    private static final String TAG = Encryption.class.getSimpleName();

    public static String decryptStringAESFromStringToString(String text, String key) throws IOException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            int len = b.length;
            if (len > keyBytes.length) len = keyBytes.length;
            System.arraycopy(b, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(Base64.decode(text, 0));
            return new String(results, "UTF-8");
        } catch (Exception e) {
            Log.d(TAG, "[decryptStringAES] " + e.getMessage());
            return null;
        }
    }

    public static byte[] decryptStringAESFromStringToBytes(String text, String key) throws IOException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            int len = b.length;
            if (len > keyBytes.length) len = keyBytes.length;
            System.arraycopy(b, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(Base64.decode(text, 0));
            return results;
        } catch (Exception e) {
            Log.d(TAG, "[decryptStringAESToBytes] " + e.getMessage());
            return null;
        }
    }

    public static byte[] decryptStringAESFromBytesToBytes(byte[] bytes, String key) throws IOException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            int len = b.length;
            if (len > keyBytes.length) len = keyBytes.length;
            System.arraycopy(b, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(bytes);
            return results;
        } catch (Exception e) {
            Log.d(TAG, "[decryptStringAESToBytes] " + e.getMessage());
            return null;
        }
    }

    public static String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
