package com.example.test;

/**
 * @Author hjc
 * @Date 2022-05-17-00-23
 **/

public class TokenHolder {
    private static ThreadLocal<String> token = new ThreadLocal<>();

    public static String getToken() {
        return token.get();
    }

    public static void setToken(String v) {
        token.set(v);
    }

    public static void clear() {
        token.remove();
    }

}
