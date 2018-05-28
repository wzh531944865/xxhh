package com.gm.api.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtils {

    public static String key = "_dabanyexiangkey";
    public static String iv = "_dabanyexiangiv_";

    public AESUtils() {
    }

    public static String encrypt(String input, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec k = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(1, k, new IvParameterSpec(iv.getBytes()));
        byte[] e = cipher.doFinal(input.getBytes("utf-8"));
        return new String(Base64.encodeBase64(e));
    }

    public static String encrypt(String input) throws Exception {
        return encrypt(input, key, iv);
    }

    public static String decrypt(String base, String key, String iv) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(base);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec k = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(2, k, new IvParameterSpec(iv.getBytes()));
        byte[] e = cipher.doFinal(keyBytes);
        return new String(e, "UTF-8");
    }

    public static String decrypt(String base) throws Exception {
        return decrypt(base, key, iv);
    }

    public static void main(String[] args) {
        try {
            String aes = encrypt("123");
            System.out.println(aes);
            String origin = decrypt(aes);
            System.out.println(origin);
        } catch (Exception e) {

        }
    }
}
