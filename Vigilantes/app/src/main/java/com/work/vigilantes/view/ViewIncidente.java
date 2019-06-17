package com.work.vigilantes.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.work.vigilantes.R;
import com.work.vigilantes.adapter.GridAdapter;
import com.work.vigilantes.controller.FirebaseAuthControllerAcess;
import com.work.vigilantes.helper.AlertDialogHelper;
import com.work.vigilantes.helper.FirebaseHelper;
import com.work.vigilantes.model.Incidente;
import com.work.vigilantes.model.Logradouro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewIncidente extends AppCompatActivity{
    private GridView grid;
    private TextView titulo,descricao,data;
    private Incidente incidente;
    private String date;
    private ImageView logradouro;
    private List<Uri> urilist;
    private Logradouro local;
    private Switch incidentes,bonificacao;
    private Button aprovar;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incidente);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        urilist = new ArrayList<>(0);
        incidente = (Incidente)getIntent().getParcelableExtra("incidente");
        local = (Logradouro)getIntent().getParcelableExtra("local");

        date = getIntent().getStringExtra("data");
        grid = (GridView)findViewById(R.id.gridview);
        titulo = (TextView)findViewById(R.id.titulo);
        descricao = (TextView)findViewById(R.id.descricao);
        data = (TextView)findViewById(R.id.viewCalendar);
        logradouro = (ImageView)findViewById(R.id.logradouro2);
        incidentes = (Switch)findViewById(R.id.incidente1);
        bonificacao = (Switch)findViewById(R.id.bonificacao);
        aprovar = (Button)findViewById(R.id.aprovar);

        FirebaseAuth firebaseAuth = FirebaseHelper.getAuthReference();
        databaseReference = FirebaseHelper.getDatabaseReference().child("incidente").child(incidente.getCodigo());

        if(!firebaseAuth.getCurrentUser().getEmail().equals("oap@gmail.com")){
            incidentes.setVisibility(View.INVISIBLE);
            bonificacao.setVisibility(View.INVISIBLE);
            aprovar.setVisibility(View.INVISIBLE);
        }// End if
    }// End onCreate()

    @Override
    protected void onResume(){
        super.onResume();

        aprovar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean bon = bonificacao.isChecked();
                boolean inc = incidentes.isChecked();
                databaseReference.child("bonificacao").setValue(bon);
                databaseReference.child("verificado").setValue(inc);
                Toast.makeText(ViewIncidente.this,"Incidente aprovado!",Toast.LENGTH_SHORT).show();
            }});

        logradouro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LayoutInflater inflater = ViewIncidente.this.getLayoutInflater();
                AlertDialogHelper alertDialogHelper = new AlertDialogHelper(ViewIncidente.this,"OK",
                        "SAIR",true,
                        "Logradouro","Digite o logradouro",
                        inflater);

                alertDialogHelper.showAlertDialogText(local,inflater);

            }});

        titulo.setText(incidente.getTitulo());
        descricao.setText(incidente.getDescricao());
        data.setText(date);

        for(Uri uri : incidente.getImagens()){
            urilist.add(uri);
        }// End for

        GridAdapter adapter = new GridAdapter(ViewIncidente.this,urilist);
        grid.setAdapter(adapter);
    }// End onResume()

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home :
                startActivity(new Intent(ViewIncidente.this,TelaPrincipal.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }// End item.getItemId()
    }// End onOptionsItemSelected()
}// End ViewIncidente
