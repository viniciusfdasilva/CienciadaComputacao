package com.work.vigilantes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.work.vigilantes.R;
import com.work.vigilantes.helper.Base64Helper;
import com.work.vigilantes.helper.FirebaseHelper;
import com.work.vigilantes.helper.K;
import com.work.vigilantes.helper.SharedPreferencesHelper;

public class Configuracao extends AppCompatActivity{
    private Switch aSwitch;
    private TextView nome,email;
    private Button salvar;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        sharedPreferencesHelper = new SharedPreferencesHelper(Configuracao.this);

        this.nome = (TextView)findViewById(R.id.nome);
        this.email = (TextView)findViewById(R.id.email);
        this.salvar = (Button)findViewById(R.id.confirmar);
        this.aSwitch = (Switch)findViewById(R.id.conectado);

        FirebaseAuth firebaseAuth = FirebaseHelper.getAuthReference();

        id = Base64Helper.code(firebaseAuth.getCurrentUser().getEmail());
        String nome_ = sharedPreferencesHelper.getString(id + "" + K.preferences.PREF_NOME);
        String email_ = sharedPreferencesHelper.getString(id + "" + K.preferences.PREF_EMAIL);
        boolean conectado = sharedPreferencesHelper.getBoolean(id + "" + K.preferences.PREF_BOOLEAN);

        nome.setText(nome_);
        email.setText(email_);
        aSwitch.setChecked(conectado);
    }// End onCreate()

    @Override
    protected void onResume(){
        super.onResume();

        salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sharedPreferencesHelper.putBoolean(id + "" + K.preferences.PREF_BOOLEAN,aSwitch.isChecked());
                sharedPreferencesHelper.commit();
                Toast.makeText(Configuracao.this,"Dados atualizados com sucesso",Toast.LENGTH_SHORT).show();
            }});
    }// End onResume()

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home :
                startActivity(new Intent(this,TelaPrincipal.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }// End item.getItemId()
    }// End onOptionsItemSelected()
}// End Configuracao
