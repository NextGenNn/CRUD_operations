package ru.sfedu.log4jproject.utils;

public class StringUtil {
    public static boolean checkStringEmpty(String toCheck){
        if(toCheck!= null) return toCheck.isBlank();
        return true;
    }
}
