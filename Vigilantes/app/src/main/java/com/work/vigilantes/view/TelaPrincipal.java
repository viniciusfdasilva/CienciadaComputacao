package com.work.vigilantes.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.work.vigilantes.R;
import com.work.vigilantes.adapter.RecyclerViewAdapter;
import com.work.vigilantes.controller.DataBaseReferenceController;
import com.work.vigilantes.controller.FirebaseAuthControllerAcess;
import com.work.vigilantes.helper.Base64Helper;
import com.work.vigilantes.helper.FirebaseHelper;
import com.work.vigilantes.model.Incidente;

import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends AppCompatActivity{
    private List<Incidente> incidenteList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FirebaseAuth firebaseAuth = FirebaseHelper.getAuthReference();
        DataBaseReferenceController dataBaseReferenceController = new DataBaseReferenceController();
        incidenteList = new ArrayList<>(0);
        ArrayList<String> text = new ArrayList<>(0);

        mAdapter = new RecyclerViewAdapter(this,text,incidenteList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        dataBaseReferenceController.getIncidente(Base64Helper.code(firebaseAuth.getCurrentUser().getEmail()), (RecyclerViewAdapter)mAdapter);
      //  Toast.makeText(TelaPrincipal.this,incidenteList.isEmpty() ? 0 : incidenteList.size(),Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);

        if(firebaseAuth.getCurrentUser().getEmail().equals("oap@gmail.com")){
            fab.setVisibility(View.INVISIBLE);
        }// End if

        fab.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            startActivity(new Intent(TelaPrincipal.this,Reportar.class));
        }});
    }// End onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.telaprincipal, menu);
        return super.onCreateOptionsMenu(menu);
    }// End onCreateOptionsMenu()

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.out :
                FirebaseAuthControllerAcess firebaseAuthControllerAcess = new FirebaseAuthControllerAcess(TelaPrincipal.this);
                firebaseAuthControllerAcess.signOut();
                return true;

            case R.id.config :
                startActivity(new Intent(TelaPrincipal.this,Configuracao.class));
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }// End item.getItemId()
    }// End onOptionsItemSelected()
}// End TelaPrincipal
