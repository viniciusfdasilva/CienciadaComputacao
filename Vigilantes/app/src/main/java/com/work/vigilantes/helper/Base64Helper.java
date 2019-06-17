package com.work.vigilantes.helper;

import android.util.Base64;

public class Base64Helper{
    private static final int DEFAULT = Base64.DEFAULT;

    public static String code(String idDecoded){
        return Base64.encodeToString(idDecoded.getBytes(),DEFAULT).replaceAll("(\\n|\\r)","");
    }// End code()

    public static String decode(String idEncoded){
        return new String(Base64.decode(idEncoded,DEFAULT));
    }// End decode()
}// End Base64Helper
