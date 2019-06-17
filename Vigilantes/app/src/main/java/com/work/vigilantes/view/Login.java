package com.work.vigilantes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.work.vigilantes.R;
import com.work.vigilantes.controller.FirebaseAuthControllerAcess;
import com.work.vigilantes.helper.FirebaseHelper;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private EditText email,senha;
    private TextView cadastrar;
    private Button logar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        firebaseAuth = FirebaseHelper.getAuthReference();
        if(firebaseAuth.getCurrentUser() != null) startActivity(new Intent(this,TelaPrincipal.class));

        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);
        cadastrar = (TextView)findViewById(R.id.cadastro);
        logar = (Button)findViewById(R.id.logar);
    }// End onCreate()

    @Override
    protected void onResume(){
        super.onResume();

        cadastrar.setOnClickListener(this);
        logar.setOnClickListener(this);
    }// End onResume()

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.cadastro :
                startActivity(new Intent(this,Cadastro.class));
                break;
            case R.id.logar :
                String email_ = email.getText().toString().trim();
                String senha_ = senha.getText().toString().trim();
                if(!email_.isEmpty() && !senha_.isEmpty()){
                    FirebaseAuthControllerAcess firebaseAuthControllerAcess = new FirebaseAuthControllerAcess(Login.this);
                    firebaseAuthControllerAcess.signIn(email_, senha_);
                }else Toast.makeText(this,getString(R.string.vazio),Toast.LENGTH_SHORT).show();
                break;
        }// End switch
    }// End onClick()
}// End class Login
