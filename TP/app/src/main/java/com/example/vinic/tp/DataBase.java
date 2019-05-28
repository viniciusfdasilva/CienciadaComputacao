package com.example.vinic.tp;
import android.content.Context;
import android.database.sqlite.*;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper{
    public static final String DB_NAME = "ufs.db";
    public static final String TABLE_NAME = "UFS";
    private static final int VERSION = 1;
    private static final String ID = "_id";
    public static final String ARVORE = "arvore";
    private static final String DATABASE_CREATE = " CREATE TABLE " + TABLE_NAME + "( " + ID + " integer primary key autoincrement, " + ARVORE + " text not null)";

    public DataBase(Context context){
        super(context, DB_NAME, null,VERSION);
    }// End DataBase()

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
    }// End onCreate()

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(DataBase.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }// End onUpgrade()
}// End class DataBase





