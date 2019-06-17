package com.work.vigilantes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.work.vigilantes.R;
import com.work.vigilantes.controller.FirebaseAuthControllerAcess;
import com.work.vigilantes.helper.MaskFormatterHelper;
import com.work.vigilantes.model.Logradouro;
import com.work.vigilantes.model.Oap;
import com.work.vigilantes.model.Usuario;

import java.util.Date;

public class JuridicoCadastro extends AppCompatActivity{
    private EditText razaoSocial,nomeFantasia,cnpj,subordinacao,sede;
    private Button cadastrar;
    private Intent intent;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        intent = getIntent();
        usuario = (Usuario)intent.getParcelableExtra("usuario");
        Logradouro logradouro = (Logradouro)intent.getParcelableExtra("local");
        int dia = intent.getIntExtra("dia",2);
        int mes = intent.getIntExtra("mes",2);
        int ano = intent.getIntExtra("ano",2000);
        usuario.setLogradouro(logradouro);
        usuario.setRegistro(new Date(ano,mes,dia));

        razaoSocial = (EditText)findViewById(R.id.razaoSocial);
        nomeFantasia = (EditText)findViewById(R.id.nomeFantasia);
        cnpj = (EditText)findViewById(R.id.cnpj);
        subordinacao = (EditText)findViewById(R.id.subordinacao);
        sede = (EditText)findViewById(R.id.sede);
        cadastrar = (Button)findViewById(R.id.cadastrar);

        MaskFormatterHelper maskFormatterHelperCnpj = new MaskFormatterHelper("NN.NNN.NNN/NNNN-NN",cnpj);
        cnpj.addTextChangedListener(maskFormatterHelperCnpj.getMtw());
    }// End onCreate()

    @Override
    protected void onResume(){
        super.onResume();

        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String razaoSocial_ = razaoSocial.getText().toString().trim();
                String nomeFantasia_ = nomeFantasia.getText().toString().trim();
                String cnpj_ = cnpj.getText().toString().trim();
                String subordinacao_ = subordinacao.getText().toString().trim();
                String sede_ = sede.getText().toString().trim();

                if(!razaoSocial_.isEmpty() && !nomeFantasia_.isEmpty() && !cnpj_.isEmpty() && !subordinacao_.isEmpty() && !sede_.isEmpty()){
                    FirebaseAuthControllerAcess<Oap> firebaseAuthControllerAcess = new FirebaseAuthControllerAcess<>(JuridicoCadastro.this);
                    Oap oap = new Oap(usuario,razaoSocial_,nomeFantasia_,cnpj_,subordinacao_,sede_);
                    firebaseAuthControllerAcess.createUser(oap);
                }else Toast.makeText(getApplicationContext(),getString(R.string.vazio),Toast.LENGTH_SHORT).show();
            }});
    }// End onResume()

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home :
                startActivity(new Intent(this,Cadastro.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }// End item.getItemId()
    }// End onOptionsItemSelected()
}// End JuridicoCadastro
