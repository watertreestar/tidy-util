package com.github.watertreestar;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Provides AES, encryption
 */
public class CryptoUtil {
    public static String decryptAES(String encryptedSeq, byte[] secret) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(secret, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted = Base64.getDecoder().decode(encryptedSeq);
        byte[] original = cipher.doFinal(encrypted);
        String originalString = new String(original, "utf-8");
        return originalString;
    }

    public static String encryptAES(String toEncrypt, byte[] secret) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(secret, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(toEncrypt.getBytes("utf-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
