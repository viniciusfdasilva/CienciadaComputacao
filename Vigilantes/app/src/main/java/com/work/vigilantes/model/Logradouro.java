package com.work.vigilantes.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Logradouro implements Parcelable {
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public Logradouro(String rua, String numero, String complemento, String bairro, String cidade, String estado){
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }// End Logradouro

    protected Logradouro(Parcel in){
        rua = in.readString();
        numero = in.readString();
        complemento = in.readString();
        bairro = in.readString();
        cidade = in.readString();
        estado = in.readString();
    }// End Logradouro()

    public static final Creator<Logradouro> CREATOR = new Creator<Logradouro>(){
        @Override
        public Logradouro createFromParcel(Parcel in){
            return new Logradouro(in);
        }// End createFromParcel()

        @Override
        public Logradouro[] newArray(int size){
            return new Logradouro[size];
        }// End newArray()
    };

    public String getRua(){
        return rua;
    }// End getRua()

    public void setRua(String rua){
        this.rua = rua;
    }// End setRua()

    public String getNumero(){
        return numero;
    }// End getNumero()

    public void setNumero(String numero){
        this.numero = numero;
    }// End setNumero()

    public String getComplemento(){
        return complemento;
    }// End getComplemento()

    public void setComplemento(String complemento){
        this.complemento = complemento;
    }// End setComplemento()

    public String getBairro(){
        return bairro;
    }// End getBairro()

    public void setBairro(String bairro){
        this.bairro = bairro;
    }// End setBairro()

    public String getCidade(){
        return cidade;
    }// End getCidade()

    public void setCidade(String cidade){
        this.cidade = cidade;
    }// End setCidade()

    public String getEstado(){
        return estado;
    }// End getEstado()

    public void setEstado(String estado){
        this.estado = estado;
    }// End setEstado()

    @Override
    public String toString(){
        return " " + rua + "" + numero + "" + complemento + "" + bairro + "" + cidade + "" + estado;
    }// End toString()

    @Override
    public int describeContents(){
        return 0;
    }// End describeContents()

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(rua);
        dest.writeString(numero);
        dest.writeString(complemento);
        dest.writeString(bairro);
        dest.writeString(cidade);
        dest.writeString(estado);
    }// End writeToParcel()
}// End class Logradouro
