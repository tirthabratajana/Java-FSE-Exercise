// src/com.greetings/com/example/greetings/MainApp.java
package com.example.greetings;

import com.example.utils.StringUtil; // Import class from the required module

public class MainApp {
    public static void main(String[] args) {
        String original = "Hello Java Modules";
        System.out.println("Original: " + original);

        // Use the utility from com.utils module
        String reversed = StringUtil.reverse(original);
        System.out.println("Reversed: " + reversed);

        String upperCased = StringUtil.toUpperCase(original);
        System.out.println("Uppercase: " + upperCased);
    }
}