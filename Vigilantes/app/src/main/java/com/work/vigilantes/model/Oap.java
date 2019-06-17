package com.work.vigilantes.model;

import com.google.firebase.database.DatabaseReference;
import com.work.vigilantes.helper.FirebaseHelper;

import java.util.HashMap;
import java.util.Map;

public class Oap extends Usuario{
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String subordinacao;
    private String sede;

    public Oap(Usuario usuario,String razaoSocial, String nomeFantasia, String cnpj, String subordinacao, String sede){
        super(usuario.getId(),usuario.getTipPessoa(),usuario.getRegistro(),
              usuario.getLogradouro(),usuario.getEmail(),usuario.getSenha(),
              usuario.isConectado());

        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.subordinacao = subordinacao;
        this.sede = sede;
    }// End Oap

    public void salvar(){
        DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference();
        databaseReference.child("usuarios").child("oap").child(getId()).setValue(this);
    }// End salvar()

    public String getRazaoSocial(){
        return razaoSocial;
    }// End getRazaoSocial()

    public void setRazaoSocial(String razaoSocial){
        this.razaoSocial = razaoSocial;
    }// End setRazaoSocial()

    public String getNomeFantasia(){
        return nomeFantasia;
    }// End getNomeFantasia()

    public void setNomeFantasia(String nomeFantasia){
        this.nomeFantasia = nomeFantasia;
    }// End setNomeFantasia()

    public String getCnpj(){
        return cnpj;
    }// End getCnpj()

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }// End setCnpj()

    public String getSubordinacao(){
        return subordinacao;
    }// End getSubordinacao()

    public void setSubordinacao(String subordinacao){
        this.subordinacao = subordinacao;
    }// End setSubordinacao()

    public String getSede(){
        return sede;
    }// End getSede()

    public void setSede(String sede){
        this.sede = sede;
    }// End setSede()

    public Map<String,Object> getMap(){
        Map<String,Object> map = new HashMap<>(0);
        map.put("id",getId());
        map.put("tipPessoa",getTipPessoa());
        map.put("rua",getLogradouro().getRua() == null ? "Itangua" : getLogradouro().getRua());
        map.put("numero",getLogradouro().getNumero() == null ? "333" : getLogradouro().getNumero());
        map.put("complemento",getLogradouro().getComplemento() == null ? "" : getLogradouro().getComplemento());
        map.put("bairro",getLogradouro().getBairro() == null ? "Dom Bosco" : getLogradouro().getBairro());
        map.put("cidade",getLogradouro().getCidade() == null ? "Belo Horizonte" : getLogradouro().getCidade());
        map.put("estado",getLogradouro().getEstado() == null ? "Minas Gerais" : getLogradouro().getEstado());
        map.put("dia",getRegistro().getDay());
        map.put("mes",getRegistro().getMonth());
        map.put("ano",getRegistro().getYear());
        map.put("email",getEmail());
        map.put("senha",getSenha());
        map.put("conectado",isConectado());
        map.put("razaoSocial",getRazaoSocial());
        map.put("nomeFantasia",getNomeFantasia());
        map.put("cnpj",getCnpj());
        map.put("subordinacao",getSubordinacao());
        map.put("sede",getSede());
        return map;
    }// End Map()
}// End class Oap
