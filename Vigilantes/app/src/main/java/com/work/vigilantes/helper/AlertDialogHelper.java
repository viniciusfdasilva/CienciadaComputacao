package com.work.vigilantes.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.work.vigilantes.R;
import com.work.vigilantes.model.Incidente;
import com.work.vigilantes.model.Logradouro;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLDisplay;

public class AlertDialogHelper{
    private AlertDialog.Builder builder;
    private String title;
    private String positive;
    private String negative;
    private String message;
    private Context context;
    private boolean canceable;
    private View view;

    public AlertDialogHelper(Context context,String title,String positive,String negative,String message,boolean canceable){
        this.context = context;
        builder = new AlertDialog.Builder(context);
        this.title = title;
        this.positive = positive;
        this.negative = negative;
        this.message = message;
        this.canceable = canceable;
    }// End AlertDialogHelper

    public AlertDialogHelper(Context context, String positive, String negative, boolean canceable, String title, String message, LayoutInflater inflater){
        builder = new AlertDialog.Builder(context);
        this.context = context;
        this.positive = positive;
        this.negative = negative;
        this.canceable = canceable;
        this.title = title;
        this.message = message;
        this.view = inflater.inflate(R.layout.dialog_cadastro, null);
        builder.setView(view);
    }// End AlertDialogHelper()

    public void showAlertDialog(){
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(canceable);

        builder.setPositiveButton(positive, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

            }});

        builder.setNegativeButton(negative, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){


            }});

        builder.show();
    }// End showAlertDialog()

    public List<String> showAlertDialogText(){
        final List<String> stringList = new ArrayList<>(0);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(canceable);

        final EditText rua = (EditText)view.findViewById(R.id.rua);
        final EditText numero = (EditText)view.findViewById(R.id.numero);
        final EditText complemento = (EditText)view.findViewById(R.id.complemento);
        final EditText bairro = (EditText)view.findViewById(R.id.bairro);
        final EditText cidade = (EditText)view.findViewById(R.id.cidade);
        final EditText estado = (EditText)view.findViewById(R.id.estado);

        builder.setPositiveButton(positive, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                stringList.add(rua.getText().toString().trim());
                stringList.add(numero.getText().toString().trim());
                stringList.add(complemento.getText().toString().trim());
                stringList.add(bairro.getText().toString().trim());
                stringList.add(cidade.getText().toString().trim());
                stringList.add(estado.getText().toString().trim());
            }});

        builder.setNegativeButton(negative, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){ }});

        builder.show();

        return stringList;
    }// End showAlertDialogText()

    public void showAlertDialogText(Logradouro logradouro,LayoutInflater inf){
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(canceable);

        View v = inf.inflate(R.layout.dialog_view,null);
        builder.setView(v);

        final TextView rua = (TextView)v.findViewById(R.id.ruaview);
        final TextView numero = (TextView)v.findViewById(R.id.numeroview);
        final TextView complemento = (TextView)v.findViewById(R.id.complementoview);
        final TextView bairro = (TextView)v.findViewById(R.id.bairroview);
        final TextView cidade = (TextView)v.findViewById(R.id.cidadeview);
        final TextView estado = (TextView)v.findViewById(R.id.estadoview);

        rua.setText("Rua: " + logradouro.getRua());
        numero.setText("Número: " + logradouro.getNumero());
        complemento.setText("Complemento: " + logradouro.getComplemento());
        bairro.setText("Bairro: " + logradouro.getBairro());
        cidade.setText("Cidade: " + logradouro.getCidade());
        estado.setText("Estado: " + logradouro.getEstado());

        builder.setPositiveButton(positive, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){ }});
        builder.show();
    }// End showAlertDialogText()
}// End AlertDialogHelper
