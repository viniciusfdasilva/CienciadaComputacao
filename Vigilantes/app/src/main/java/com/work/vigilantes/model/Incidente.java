package com.work.vigilantes.model;

import android.media.Image;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.work.vigilantes.helper.FirebaseHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Incidente implements Parcelable {
    private List<Uri> imagens;
    private String titulo;
    private String descricao;
    private Logradouro local;
    private Date data;
    private boolean bonificacao;
    private boolean verificado;
    private String userid;
    private String codigo;

    public Incidente(String codigo,String userid,List<Uri> imagens, String titulo, String descricao, Logradouro local, Date data,boolean bonificacao,boolean verificado){
        this.imagens = imagens;
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.bonificacao = bonificacao;
        this.verificado = verificado;
        this.userid = userid;
        this.codigo = codigo;
    }// End Incidente()

    protected Incidente(Parcel in){
        imagens = in.createTypedArrayList(Uri.CREATOR);
        titulo = in.readString();
        descricao = in.readString();
        bonificacao = in.readByte() != 0;
        verificado = in.readByte() != 0;
        userid = in.readString();
        codigo = in.readString();
    }// End Incidente

    public static final Creator<Incidente> CREATOR = new Creator<Incidente>(){
        @Override
        public Incidente createFromParcel(Parcel in){
            return new Incidente(in);
        }// End createFromParcel()

        @Override
        public Incidente[] newArray(int size){
            return new Incidente[size];
        }// End newArray()
    };

    public void salvar(){
        DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference();
        databaseReference.child("incidente").child(getCodigo()).updateChildren(getMap());
    }// End salvar()

    public List<Uri> getImagens(){
        return imagens;
    }// End getImagens()

    public void setImagens(List<Uri> imagens){
        this.imagens = imagens;
    }// End setImagens()

    public String getTitulo(){
        return titulo;
    }// End getTitulo()

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }// End setTitulo()

    public String getDescricao(){
        return descricao;
    }// End getDescricao()

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }// End setDescricao()

    public Logradouro getLocal(){
        return local;
    }// End getLocal()

    public void setLocal(Logradouro local){
        this.local = local;
    }// End setLocal()

    public Date getData(){
        return data;
    }// End getData()

    public void setData(Date data){
        this.data = data;
    }// End setData()

    public boolean isBonificacao(){
        return bonificacao;
    }// End isBonificacao()

    public void setBonificacao(boolean bonificacao){
        this.bonificacao = bonificacao;
    }// End isBonificacao()

    public boolean isVerificado(){
        return verificado;
    }// End isVerificado()

    public void setVerificado(boolean verificado){
        this.verificado = verificado;
    }// End setVerificado()

    public String getUserid(){
        return userid;
    }// End getUserid()

    public void setUserid(String userid){
        this.userid = userid;
    }// End setUserid()

    public String getCodigo(){
        return codigo;
    }// End getCodigo()

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }// End setCodigo()

    public Map<String,Object> getMap(){
        Map<String,Object> map = new HashMap<>(0);
        for(int i = 0; i < getImagens().size(); i++) map.put("uri "+i,getImagens().get(i).toString());
        map.put("titulo",getTitulo());
        map.put("descricao",getDescricao());
        map.put("rua",getLocal().getRua());
        map.put("numero",getLocal().getNumero());
        map.put("complemento",getLocal().getComplemento() == null ? " " : getLocal().getComplemento());
        map.put("bairro",getLocal().getBairro());
        map.put("cidade",getLocal().getCidade());
        map.put("estado",getLocal().getCidade());
        map.put("dia",getData().getDay());
        map.put("mes",getData().getMonth());
        map.put("ano",getData().getYear());
        map.put("bonificacao",isBonificacao());
        map.put("verificado",isVerificado());
        map.put("userid",getUserid());
        map.put("codigo",getCodigo());
        return map;
    }// End getMap()

    @Override
    public int describeContents(){
        return 0;
    }// End describeContents()

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeTypedList(imagens);
        dest.writeString(titulo);
        dest.writeString(descricao);
        dest.writeByte((byte) (bonificacao ? 1 : 0));
        dest.writeByte((byte) (verificado ? 1 : 0));
        dest.writeString(userid);
        dest.writeString(codigo);
    }// End writeToParcel()
}// End Incidente
