package com.work.vigilantes.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Parcelable{
    private String id;
    private String tipPessoa;
    private Date registro;
    private Logradouro logradouro;
    private String email;
    private String senha;
    private boolean conectado;

    public Usuario(String id, String tipPessoa, Date registro, Logradouro logradouro, String email, String senha, boolean conectado){
        this.id = id;
        this.tipPessoa = tipPessoa;
        this.registro = registro;
        this.logradouro = logradouro;
        this.email = email;
        this.senha = senha;
        this.conectado = conectado;
    }// End Usuario()

    protected Usuario(Parcel in){
        id = in.readString();
        tipPessoa = in.readString();
        email = in.readString();
        senha = in.readString();
        conectado = in.readByte() != 0;
    }// End usuario()

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>(){
        @Override
        public Usuario createFromParcel(Parcel in){
            return new Usuario(in);
        }// End createFromParcel()

        @Override
        public Usuario[] newArray(int size){
            return new Usuario[size];
        }// End newArray()
    };

    public String getId(){
        return id;
    }// End getId()

    public void setId(String id){
        this.id = id;
    }// End setId()

    public String getTipPessoa(){
        return tipPessoa;
    }// End getTipPessoa()

    public void setTipPessoa(String tipPessoa){
        this.tipPessoa = tipPessoa;
    }// End setTipPessoa()

    public Date getRegistro(){
        return registro;
    }// End getIdRegistro()

    public void setRegistro(Date registro){
        this.registro = registro;
    }// End setIdRegistro()

    public Logradouro getLogradouro(){
        return logradouro;
    }// End getLogradouro()

    public void setLogradouro(Logradouro logradouro){
        this.logradouro = logradouro;
    }// End setLogradouro()

    public String getEmail(){
        return email;
    }// End getEmail()

    public void setEmail(String email){
        this.email = email;
    }// End setEmail()

    public String getSenha(){
        return senha;
    }// End getSenha()

    public void setSenha(String senha){
        this.senha = senha;
    }// End setSenha()

    public boolean isConectado(){
        return conectado;
    }// End isConectado()

    public void setConectado(boolean conectado){
        this.conectado = conectado;
    }// End setConectado()

    @Override
    public int describeContents(){
        return 0;
    }// End describeContens()

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(id);
        dest.writeString(tipPessoa);
        dest.writeString(email);
        dest.writeString(senha);
        dest.writeByte((byte) (conectado ? 1 : 0));
    }// End writeToParcel()
}// End Usuario
