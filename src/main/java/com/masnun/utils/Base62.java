package com.masnun.utils;


// Copied from: https://gist.github.com/jdcrensh/4670128
// Modified to use Long

public class Base62 {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static final int BASE = ALPHABET.length();

    private Base62() {
    }

    public static String fromBase10(Long i) {


        StringBuilder sb = new StringBuilder("");
        if (i == 0) {
            return "a";
        }
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }

    private static Long fromBase10(Long i, final StringBuilder sb) {
        Long rem = i % BASE;
        sb.append(ALPHABET.charAt(rem.intValue()));
        return i / BASE;
    }

    public static Long toBase10(String str) {
        return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
    }

    private static Long toBase10(char[] chars) {
        Long n = Long.valueOf(0);
        for (int i = chars.length - 1; i >= 0; i--) {
            n += toBase10(ALPHABET.indexOf(chars[i]), i);
        }
        return n;
    }

    private static int toBase10(int n, int pow) {
        return n * (int) Math.pow(BASE, pow);
    }
}