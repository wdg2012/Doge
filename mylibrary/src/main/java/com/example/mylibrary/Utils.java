package com.example.mylibrary;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class Utils {
    private Utils() {
    }
    public static  <T> T checkNoNull(T obj, String msg){
        if (obj==null){
            throw new IllegalArgumentException(msg);
        }
        
        return  obj;
    }
    public static <T> Class<T> isInterface(Class<T> tClass){
        if (!tClass.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        // Prevent API interfaces from extending other interfaces. This not only avoids a bug in
        // Android (http://b.android.com/58753) but it forces composition of API declarations which is
        // the recommended pattern.
        if (tClass.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
        return tClass;
    }
}
