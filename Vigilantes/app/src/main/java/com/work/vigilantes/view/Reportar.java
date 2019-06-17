package com.work.vigilantes.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Repo;
import com.work.vigilantes.R;
import com.work.vigilantes.adapter.GridAdapter;
import com.work.vigilantes.controller.FirebaseAuthControllerAcess;
import com.work.vigilantes.helper.AlertDialogHelper;
import com.work.vigilantes.helper.Base64Helper;
import com.work.vigilantes.helper.FirebaseHelper;
import com.work.vigilantes.helper.SharedPreferencesHelper;
import com.work.vigilantes.model.Incidente;
import com.work.vigilantes.model.Logradouro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Reportar extends AppCompatActivity implements View.OnClickListener{
    public static int ID_DATEPICKER = 0x0000;
    private EditText titulo,descricao;
    private Button novo,enviar;
    private TextView viewCalendar;
    private ImageView logradouro,calendario;
    private int dia,mes,ano = -1;
    private List<String> listlogradouro;
    private List<Uri> uriList;
    private GridView gridView;
    private Random random;
    private int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);

        uriList = new ArrayList<>(0);
        random = new Random();
        codigo = random.nextInt(9999) + 1;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        this.titulo = (EditText)findViewById(R.id.titulo);
        this.descricao = (EditText)findViewById(R.id.descricao);
        this.novo = (Button)findViewById(R.id.novo);
        this.enviar = (Button)findViewById(R.id.enviar);
        this.viewCalendar = (TextView)findViewById(R.id.viewCalendar);
        this.calendario = (ImageView)findViewById(R.id.registro1);
        this.logradouro = (ImageView)findViewById(R.id.logradouro1);
        this.gridView = (GridView)findViewById(R.id.gridview);
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
            case android.R.id.home :
                startActivity(new Intent(Reportar.this,TelaPrincipal.class));
                return true;
            case R.id.out :
                FirebaseAuthControllerAcess firebaseAuthControllerAcess = new FirebaseAuthControllerAcess(Reportar.this);
                firebaseAuthControllerAcess.signOut();
                return true;
            default: return super.onOptionsItemSelected(item);
        }// End item.getItemId()
    }// End onOptionsItemSelected()

    @Override
    protected void onResume(){
        super.onResume();
        novo.setOnClickListener(this);
        enviar.setOnClickListener(this);
        calendario.setOnClickListener(this);
        logradouro.setOnClickListener(this);
    }// End onResume()

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.novo :
                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 123);
                break;
            case R.id.registro1 :
                showDialog(ID_DATEPICKER);
                break;
            case R.id.logradouro1 :
                LayoutInflater inflater = this.getLayoutInflater();
                AlertDialogHelper alertDialogHelper = new AlertDialogHelper(Reportar.this,"OK",
                        "SAIR",true,
                        "Logradouro","Digite o logradouro",
                        inflater);
                listlogradouro = alertDialogHelper.showAlertDialogText();
                break;

            case R.id.enviar :
                if(listlogradouro == null || listlogradouro.isEmpty()) Toast.makeText(this,"Digite o logradouro",Toast.LENGTH_SHORT).show();
                Logradouro logradouro = new Logradouro(listlogradouro.get(0),listlogradouro.get(1),listlogradouro.get(2),listlogradouro.get(3),listlogradouro.get(4),listlogradouro.get(5));

                String titulo_ = titulo.getText().toString().trim();
                String descricao_ = descricao.getText().toString().trim();

                if(!titulo_.isEmpty() && !descricao_.isEmpty() && dia != -1 && mes != -1 && ano != -1 && !logradouro.toString().isEmpty() && !uriList.isEmpty()){
                    FirebaseAuth firebaseAuth = FirebaseHelper.getAuthReference();
                    Incidente incidente = new Incidente(
                            "INC "+String.valueOf(codigo)
                            ,Base64Helper.code(firebaseAuth.getCurrentUser().getEmail())
                            ,uriList
                            ,titulo_
                            ,descricao_
                            ,logradouro
                            ,new Date(ano,mes,dia)
                            ,false
                            ,false);
                    incidente.salvar();

                    startActivity(new Intent(Reportar.this,Concluido.class).putExtra("codigo","INC "+String.valueOf(codigo)));
                    finish();
                }else Toast.makeText(this,getString(R.string.vazio),Toast.LENGTH_SHORT).show();
                break;
        }// End switch
    }// End onClick()

    @Override
    protected Dialog onCreateDialog(int id){
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,onDateSetListener,year,month,day);
        datePickerDialog.setCancelable(false);
        return datePickerDialog;
    }// End onCreateDialog()

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
            Calendar calendar = Calendar.getInstance();

            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);

            if(new Date(currentYear,currentMonth,currentDay).compareTo(new Date(year,month,dayOfMonth)) >= 0){
                String date = dayOfMonth + "/" + String.valueOf(month+1) + "/" + year;

                dia = dayOfMonth;
                mes = (month+1);
                ano = year;

                viewCalendar.setText(date);
            }else Toast.makeText(getApplicationContext(),getString(R.string.datavalida),Toast.LENGTH_SHORT).show();
        }};

        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            if(resultCode == Activity.RESULT_OK){
                if(requestCode == 123){
                    Uri imagemSelecionada = data.getData();
                    uriList.add(imagemSelecionada);
                }// End if
                GridAdapter gridAdapter = new GridAdapter(Reportar.this,uriList);
                gridView.setAdapter(gridAdapter);
            }// End if
        }// End onActivityResult()
}// End Reportar
