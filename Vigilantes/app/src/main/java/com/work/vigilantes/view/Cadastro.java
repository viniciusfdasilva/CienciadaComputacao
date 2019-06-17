package com.work.vigilantes.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.work.vigilantes.R;
import com.work.vigilantes.helper.AlertDialogHelper;
import com.work.vigilantes.helper.Base64Helper;
import com.work.vigilantes.model.Logradouro;
import com.work.vigilantes.model.Usuario;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Cadastro extends AppCompatActivity implements View.OnClickListener{
    public static int ID_DATEPICKER = 0x0000;

    private EditText email,senha;
    private Spinner tipPessoa;
    private ImageView registro,logradouro;
    private TextView viewCalendar;
    private Switch conectado;
    private Button seguir;

    private int dia,mes,ano = -1;

    private EditText rua;

    private List<String> listlogradouro;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);
        tipPessoa = (Spinner)findViewById(R.id.tipPessoa);
        registro = (ImageView)findViewById(R.id.registro);
        logradouro = (ImageView)findViewById(R.id.logradouro);
        conectado = (Switch)findViewById(R.id.conectado);
        seguir = (Button)findViewById(R.id.seguir);
        viewCalendar = (TextView)findViewById(R.id.viewCalendar);
    }// End onCreate()

    @Override
    protected void onResume(){
        super.onResume();
        registro.setOnClickListener(this);
        logradouro.setOnClickListener(this);
        seguir.setOnClickListener(this);
    }// End onResume()

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.registro :
                showDialog(ID_DATEPICKER);
                break;
            case R.id.logradouro :
                LayoutInflater inflater = this.getLayoutInflater();
                AlertDialogHelper alertDialogHelper = new AlertDialogHelper(Cadastro.this,"OK",
                                                                            "SAIR",true,
                                                                                "Logradouro","Digite o logradouro",
                                                                                 inflater);
                listlogradouro = alertDialogHelper.showAlertDialogText();
                break;

            case R.id.seguir :
                if(listlogradouro == null || listlogradouro.isEmpty()) Toast.makeText(this,"Digite o logradouro",Toast.LENGTH_SHORT).show();
                Logradouro logradouro = new Logradouro(listlogradouro.get(0),listlogradouro.get(1),listlogradouro.get(2),
                                                       listlogradouro.get(3),listlogradouro.get(4),listlogradouro.get(5));

                String email_ = email.getText().toString();
                String senha_ = senha.getText().toString();
                int position = tipPessoa.getSelectedItemPosition();
                String tipPessoa_ = tipPessoa.getItemAtPosition(position).toString();
                boolean conectado_ = conectado.isChecked();

                if(!email_.isEmpty() && !senha_.isEmpty() && !tipPessoa_.isEmpty() && dia != -1 && mes != -1 && ano != -1 && !logradouro.toString().isEmpty()){
                    Usuario usuario = null;
                    if(position == 0){
                        usuario = new Usuario(Base64Helper.code(email_),tipPessoa_,new Date(ano,mes,dia),logradouro,email_,senha_,conectado_);
                        startActivity(new Intent(this,FisicoCadastro.class).putExtra("usuario",usuario)
                                .putExtra("local",logradouro)
                                .putExtra("dia",dia)
                                .putExtra("mes",mes)
                                .putExtra("ano",ano));
                    }else{
                        usuario = new Usuario(Base64Helper.code(email_),tipPessoa_,new Date(ano,mes,dia),logradouro,email_,senha_,conectado_);
                        startActivity(new Intent(this,JuridicoCadastro.class).putExtra("usuario",usuario)
                                .putExtra("local",logradouro)
                                .putExtra("dia",dia)
                                .putExtra("mes",mes)
                                .putExtra("ano",ano));
                    }// End else
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home :
                startActivity(new Intent(this,Login.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }// End item.getItemId()
    }// End onOptionsItemSelected()
}// End class Cadastro
