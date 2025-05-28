// src/com.utils/com/example/utils/StringUtil.java
package com.example.utils;

public class StringUtil {
    public static String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    public static String toUpperCase(String text) {
        return text.toUpperCase();
    }
}