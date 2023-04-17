package com.xingchi.shortlink.common.utils;

public class Base62Utils {
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final int BASE = 62;

    public static String encode(long number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int remainder = (int) (number % BASE);
            sb.append(BASE62[remainder]);
            number /= BASE;
        }
        return sb.reverse().toString();
    }

    public static long decode(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int digit = (c >= '0' && c <= '9') ? c - '0' :
                        (c >= 'A' && c <= 'Z') ? c - 'A' + 10 :
                        (c >= 'a' && c <= 'z') ? c - 'a' + 36 : -1;
            result = result * BASE + digit;
        }
        return result;
    }
}
