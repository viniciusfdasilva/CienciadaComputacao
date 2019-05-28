package com.example.vinic.tp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Adicionar extends AppCompatActivity{
    EditText textcidade;
    Button button, inicio;
    AlertDialog.Builder alertdialog;
    private DbController dbController;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.adicionar);
        textcidade = (EditText)findViewById(R.id.inserircidade);
        button = (Button)findViewById(R.id.inserir);
        inicio = (Button)findViewById(R.id.inicio);
        alertdialog = new AlertDialog.Builder(this);
        dbController = new DbController(getApplicationContext());
    }// End onCreate()

    public void onResume(){
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String string = textcidade.getText().toString();
                dbController.insert(string);
                Menu.cidades.add(string);
                alertdialog.setTitle("Inserção Banco de Dados");
                alertdialog.setMessage("A cidade foi inserida no banco de dados com sucesso!");
                alertdialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){ startFragmentMenu(); }});
                    alertdialog.show();
            }});

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String[] estados = getResources().getStringArray(R.array.estados);
                startAtividade(estados);
            }});
    }// End onResume()

    public void startAtividade(String[] estados){
        startActivity(new Intent(this,Atividade.class).putExtra("Estados",estados));
    }// End startFragmentMenu()

    public void startFragmentMenu(){
        startActivity(new Intent(this,FragmentMenu.class));
    }// End startFragmentMenu()
}// End Adicionar()
