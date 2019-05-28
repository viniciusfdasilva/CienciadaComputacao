package com.example.vinic.tp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }// End onCreate()

    protected void onResume(){
        super.onResume();
        String[] estados = getResources().getStringArray(R.array.estados);
        String[] cidades = getResources().getStringArray(R.array.cidades);

        if(estados.length == 27 && cidades.length == 27) {
            startAtividade(estados,cidades);
        }else Toast.makeText(getApplicationContext(), "ERRO: Não foram cadastradas todos os estados ou cidades", Toast.LENGTH_SHORT).show();;
    }// End onResume()

    private void startAtividade(String[] uf,String[] cid){
        startActivity(new Intent(this,Atividade.class).putExtra("Cidade",cid).putExtra("Estados",uf));
        finish();
    }// End startAtividade()
}//End MainActivity
