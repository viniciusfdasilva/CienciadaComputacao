package com.example.vinic.tp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Vinicius Fracisco da Silva
 */
public class DbController{
    private SQLiteDatabase db;
    private DataBase data_base;

    public DbController(Context context){
        data_base = new DataBase(context);
    }// End DbController()

    public String insert(String cidade){
        ContentValues valores;
        long resultado;
        db = data_base.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DataBase.ARVORE,cidade);
        resultado = db.insert(DataBase.TABLE_NAME, null, valores);
        db.close();
        if(resultado == -1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }// End insert()
}// End DbController
