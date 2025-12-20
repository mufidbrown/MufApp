package com.muf.common.helper;


import java.util.Calendar;

import com.muf.common.constant.AppConstant;
import org.jasypt.util.binary.BasicBinaryEncryptor;


public class EncryptHelper {

    public static String encrypt(String text) throws Exception {
        /* MFD-12122025 */
        return Encryption.encrypt(AppConstant.PRODUCT_KEY, text);
    }

    public static String decrypt(String text) throws Exception {
        return Encryption.decrypt(AppConstant.PRODUCT_KEY, text);
    }

    public static String encryptWithSeparator(String text) throws Exception {
        String separator = AppConstant.LOGIN_SEPARATOR_KEY;
        String second = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
        String auth = second + separator + text + separator;
        return encrypt(auth);
    }

    public static String decryptWithSeparator(String text) throws Exception {
        String auth = decrypt(text);
        String[] arrString = auth.split(AppConstant.LOGIN_SEPARATOR_KEY);
        return arrString[1];
    }

    public static byte[] binaryDecrypt(byte[] encryptedData) {
        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(AppConstant.PRODUCT_KEY);
        byte[] data = binaryEncryptor.decrypt(encryptedData);
        return data;
    }

    public static byte[] binaryEncrypt(byte[] encryptedData) {
        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(AppConstant.PRODUCT_KEY);
        byte[] data = binaryEncryptor.encrypt(encryptedData);
        return data;
    }

}
