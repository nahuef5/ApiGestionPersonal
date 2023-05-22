package com.management.staff.global.utils;
public class Trimmer {
    public static String trimBrackets(String message){
        return message.replaceAll("[\\[\\]]", "");
    }
}