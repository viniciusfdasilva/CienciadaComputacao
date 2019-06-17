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
import com.work.vigilantes.model.Usuario;
import com.work.vigilantes.model.Vigilante;

import java.util.Date;

public class FisicoCadastro extends AppCompatActivity{
    private Intent intent;
    private EditText nome,cpf,rg;
    private Button cadastrar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisico_cadastro);

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

        nome = (EditText)findViewById(R.id.nome);
        cpf = (EditText)findViewById(R.id.cpf);
        rg = (EditText)findViewById(R.id.rg);
        cadastrar = (Button)findViewById(R.id.cadastrar);

        MaskFormatterHelper maskFormatterHelperCpf = new MaskFormatterHelper("NNN.NNN.NNN-NN",cpf);
        cpf.addTextChangedListener(maskFormatterHelperCpf.getMtw());

        MaskFormatterHelper maskFormatterHelperRg = new MaskFormatterHelper("LL-NN.NNN.NNN",rg);
        rg.addTextChangedListener(maskFormatterHelperRg.getMtw());
    }// End onCreate()

    @Override
    protected void onResume(){
        super.onResume();

        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String nome_ = nome.getText().toString().trim();
                String cpf_ = cpf.getText().toString().trim();
                String rg_ = rg.getText().toString().trim();

                if(!nome_.isEmpty() && !cpf_.isEmpty() && !rg_.isEmpty()){
                    FirebaseAuthControllerAcess<Vigilante> firebaseAuthControllerAcess = new FirebaseAuthControllerAcess<>(FisicoCadastro.this);
                    Vigilante vigilante = new Vigilante(usuario,nome_,cpf_,rg_);
                    firebaseAuthControllerAcess.createUser(vigilante);
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
}// End FisicoCadastro
