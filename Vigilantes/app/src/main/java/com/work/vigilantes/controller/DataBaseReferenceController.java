package com.work.vigilantes.controller;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.work.vigilantes.adapter.RecyclerViewAdapter;
import com.work.vigilantes.helper.Base64Helper;
import com.work.vigilantes.helper.FirebaseHelper;
import com.work.vigilantes.model.Incidente;
import com.work.vigilantes.model.Logradouro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseReferenceController{
    private DatabaseReference databaseReference;

    public DataBaseReferenceController(){
        databaseReference = FirebaseHelper.getDatabaseReference();
    }// End DatabaseReferenceController()

    public List<Incidente> getIncidente(final String userIdent, final RecyclerViewAdapter adapter){
        final List<Incidente> incidenteList = new ArrayList<>(0);
        databaseReference = databaseReference.child("incidente");
        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                incidenteList.clear();
                if(dataSnapshot != null) {
                    for (DataSnapshot dados : dataSnapshot.getChildren()) {
                        String titulo = (String) dados.child("titulo").getValue();
                        String descricao = (String) dados.child("descricao").getValue();
                        String rua = (String) dados.child("rua").getValue();
                        String numero = (String) dados.child("numero").getValue();
                        String complemento = (String) dados.child("complemento").getValue();
                        String bairro = (String) dados.child("bairro").getValue();
                        String cidade = (String) dados.child("cidade").getValue();
                        String estado = (String) dados.child("estado").getValue();
                        long dia = (Long) dados.child("dia").getValue();
                        long mes = (Long)dados.child("mes").getValue();
                        long ano = (Long) dados.child("ano").getValue();
                        boolean bonificacao = (Boolean) dados.child("bonificacao").getValue();
                        boolean verificado = (Boolean) dados.child("verificado").getValue();
                        String userId = (String) dados.child("userid").getValue();
                        String codigo = (String) dados.child("codigo").getValue();
                        Logradouro logradouro = new Logradouro(rua, numero, complemento, bairro, cidade, estado);
                        Date date = new Date((int)ano,(int)mes,(int)dia);
                        int i = 0;
                        List<Uri> uri = new ArrayList<>(0);
                        while(dados.child("uri "+i).getValue() != null) {
                            Log.v("uri "+i,(String)dados.child("uri "+i).getValue());
                            uri.add(Uri.parse((String)dados.child("uri "+i).getValue()));
                            i++;
                        }// End while()

                        Incidente incidente = new Incidente(codigo, userId, uri, titulo, descricao,
                                                            logradouro, date,bonificacao,verificado);

                        if (userIdent.equals(Base64Helper.code("oap@gmail.com"))) {
                            incidenteList.add(incidente);
                            adapter.insertItem(incidente.getTitulo(),incidente);
                        }else if (userIdent.equals(incidente.getUserid())){
                            Log.i("teste","Entrou");
                            incidenteList.add(incidente);
                            adapter.insertItem(incidente.getTitulo(),incidente);
                            Log.i("teste", String.valueOf(incidenteList.size()));
                        }// End else if
                    }// End for
                    adapter.notifyDataSetChanged();
                }// End if
            }// End onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }});
        Log.i("teste", String.valueOf(incidenteList.size()));
        return incidenteList;
    }// End getIncidente()
}// End DataBaseReferenceController
