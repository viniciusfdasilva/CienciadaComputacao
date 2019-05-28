package com.example.vinic.tp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FragmentMenu extends AppCompatActivity implements SensorEventListener{
    Button adicionar;
    Button voltar;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    public static float[] longvalue = new float[3];

    @Override
    protected void onCreate(Bundle savedBundleState){
        super.onCreate(savedBundleState);
        setContentView(R.layout.fragment);
        Intent intent = getIntent();
        adicionar = (Button)findViewById(R.id.adicionarCidade);
        voltar = (Button)findViewById(R.id.voltar);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }// End onCreate()

    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer,SensorManager.SENSOR_DELAY_UI);
        adicionar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ startAdicionarActivity(); }});

        voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String[] estados = getResources().getStringArray(R.array.estados);
                startAtividadeActivity(estados);
            }});

        if(!Menu.cidades.isEmpty() && (int)longvalue[0] < 0 && (int)longvalue[1] >= 5 && (int)longvalue[2] >= 0){
            Menu.cidades.remove(Menu.cidades.size()-1);
        }// End if
    }// End onResume()

    public void startAtividadeActivity(String[] estados){
        startActivity(new Intent(this,Atividade.class).putExtra("Estados",estados));
    }// End startAtividadeActivity()

    public void startAdicionarActivity(){
        startActivity(new Intent(this,Adicionar.class));
    }// End startAdicionarActivity()

    public void onSensorChanged(SensorEvent event){
        longvalue = event.values;
    }// End onSensorChanged()

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }// End onAccuracyChanged()
}// End Fragment
