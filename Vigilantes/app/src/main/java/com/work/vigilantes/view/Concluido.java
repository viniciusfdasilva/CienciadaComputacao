package com.work.vigilantes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.work.vigilantes.R;

public class Concluido extends AppCompatActivity{
    private TextView codigo;
    private Button confirmar;
    private String cod;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concluido);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        cod = intent.getStringExtra("codigo");

        this.codigo = (TextView)findViewById(R.id.codigo);
        this.confirmar = (Button)findViewById(R.id.confirmar);
    }// End onCreate()

    @Override
    protected void onResume(){
        super.onResume();

        codigo.setText(cod);

        confirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Concluido.this,TelaPrincipal.class));
            }});
    }// End onResume()
}// End Concluido
