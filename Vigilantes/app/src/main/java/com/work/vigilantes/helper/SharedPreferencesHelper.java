package com.work.vigilantes.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String FILE_NAME = "vigilantes";
    public static final int MODE = Context.MODE_PRIVATE;

    public SharedPreferencesHelper(Context context){
        sharedPreferences = context.getSharedPreferences(FILE_NAME,MODE);
        editor = sharedPreferences.edit();
    }// End SharedPreferencesHelper()

    public void putString(String key,String object){
        editor.putString(key,object);
    }// End put()

    public void putBoolean(String key,boolean object){
        editor.putBoolean(key,object);
    }// End put()

    public void commit(){
        editor.commit();
    }// End commit()

    public String getString(String key){
        return sharedPreferences.getString(key,"");
    }// End getString()

    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }// End getBoolean()
}// End class SharedPreferencesHelper
