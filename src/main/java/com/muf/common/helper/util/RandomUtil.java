package com.muf.common.helper.util;


import java.util.Random;

public class RandomUtil {
    public static String getAlphaNumericRandom(int len) {
        final char[] ch = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z' };

        char[] c = new char[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }

        return new String(c);
    }

    public static String getNumericRandom(int len) {
        final char[] ch = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

        char[] c = new char[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }

        return new String(c);
    }

    public static String getAlphaRandom(int len) {
        final char[] ch = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        char[] c = new char[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }

        return new String(c);
    }

    public static String generateUniqueAlhpaNumeric(int TOKEN_LENGTH) {
        Long longMillis = System.currentTimeMillis();
        String millis = longMillis.toString();
        int length = millis.length();
        int prefixLength = (TOKEN_LENGTH - (length * 2));
        String token = RandomUtil.getAlphaNumericRandom(prefixLength);
        String mixer = RandomUtil.getAlphaNumericRandom(length);
        for (int i = 0; i < millis.length(); i++) {
            char decode = ((char) (millis.charAt(i) + 17));
            token += mixer.charAt(i);
            token += decode;
        }
        return token;
    }

    public static String generate32UniqueAlhpaNumeric() {

        return generateUniqueAlhpaNumeric(32);
    }

}

