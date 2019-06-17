package com.work.vigilantes.model;

import com.google.firebase.database.DatabaseReference;
import com.work.vigilantes.helper.FirebaseHelper;

import java.util.HashMap;
import java.util.Map;

public class Vigilante extends Usuario{
    private String nome;
    private String cpf;
    private String rg;

    public Vigilante(Usuario usuario,String nome, String cpf,String rg){
        super(usuario.getId(),usuario.getTipPessoa(),usuario.getRegistro(),
              usuario.getLogradouro(),usuario.getEmail(),usuario.getSenha(),
              usuario.isConectado());
        this.rg = rg;
        this.nome = nome;
        this.cpf = cpf;
    }// End Vigilante()

    public void salvar(){
        DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference();
        databaseReference.child("usuarios").child("vigilante").child(getId()).setValue(this);
    }// End salvar()

    public String getRg(){
        return rg;
    }// End getRg()

    public void setRg(String rg){
        this.rg = rg;
    }// End setRg()

    public String getNome(){
        return nome;
    }// End getNome()

    public void setNome(String nome){
        this.nome = nome;
    }// End setNome()

    public String getCpf(){
        return cpf;
    }// End getCpf()

    public void setCpf(String cpf){
        this.cpf = cpf;
    }// End setCpf()

    public Map<String,Object> getMap() {
        Map<String, Object> map = new HashMap<>(0);
        map.put("id", getId());
        map.put("tipPessoa", getTipPessoa());
        map.put("rua",getLogradouro().getRua() == null ? "Itangua" : getLogradouro().getRua());
        map.put("numero",getLogradouro().getNumero() == null ? "333" : getLogradouro().getNumero());
        map.put("complemento",getLogradouro().getComplemento() == null ? "" : getLogradouro().getComplemento());
        map.put("bairro",getLogradouro().getBairro() == null ? "Dom Bosco" : getLogradouro().getBairro());
        map.put("cidade",getLogradouro().getCidade() == null ? "Belo Horizonte" : getLogradouro().getCidade());
        map.put("estado",getLogradouro().getEstado() == null ? "Minas Gerais" : getLogradouro().getEstado());
        map.put("dia", getRegistro().getDay());
        map.put("mes", getRegistro().getMonth());
        map.put("ano", getRegistro().getYear());
        map.put("email", getEmail());
        map.put("senha", getSenha());
        map.put("conectado", isConectado());
        map.put("nome", getNome());
        map.put("cpf", getCpf());
        map.put("rg", getRg());
        return map;
    }// End getMap()
}// End Vigilante
