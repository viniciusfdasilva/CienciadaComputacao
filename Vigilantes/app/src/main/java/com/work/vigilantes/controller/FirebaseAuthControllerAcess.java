package com.work.vigilantes.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.work.vigilantes.R;
import com.work.vigilantes.helper.FirebaseHelper;
import com.work.vigilantes.helper.K;
import com.work.vigilantes.helper.ProgressHelper;
import com.work.vigilantes.helper.SharedPreferencesHelper;
import com.work.vigilantes.model.Oap;
import com.work.vigilantes.model.Usuario;
import com.work.vigilantes.model.Vigilante;
import com.work.vigilantes.view.Login;
import com.work.vigilantes.view.TelaPrincipal;

public class FirebaseAuthControllerAcess<T extends Usuario>{
    private FirebaseAuth firebaseAuth;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Context context;

    public FirebaseAuthControllerAcess(Context context){
        firebaseAuth = FirebaseHelper.getAuthReference();
        this.context = context;
        sharedPreferencesHelper = new SharedPreferencesHelper(this.context);
    }// End FirebaseAuthControllerAcess()

    public void signOut(){
        firebaseAuth.signOut();
        context.startActivity(new Intent(context,Login.class));
    }// End signOut()

    public void createUser(final T value){
        final ProgressHelper progresso = new ProgressHelper(
                context
                , ProgressDialog.STYLE_SPINNER
                ,false
                ,context.getString(R.string.inicio)
                ,context.getString(R.string.iniciosistema));
        progresso.showProgressDialog();

        firebaseAuth.createUserWithEmailAndPassword(value.getEmail(),value.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                String erro = "";

                if(task.isSuccessful() && value.getClass().equals(Vigilante.class)){
                    Vigilante vigilante = (Vigilante)value;
                    vigilante.salvar();
                    sharedPreferencesHelper.putString(vigilante.getId() + "" + K.preferences.PREF_EMAIL,vigilante.getEmail());
                    sharedPreferencesHelper.putString(vigilante.getId() + "" + K.preferences.PREF_NOME,vigilante.getNome());
                    sharedPreferencesHelper.putBoolean(vigilante.getId() + "" + K.preferences.PREF_BOOLEAN,vigilante.isConectado());
                    sharedPreferencesHelper.commit();
                    Toast.makeText(context,"SUCESSO!",Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, TelaPrincipal.class));
                }else if(task.isSuccessful() && value.getClass().equals(Oap.class)){
                    Oap oap = (Oap)value;
                    oap.salvar();
                    sharedPreferencesHelper.putString(oap.getId() + "" + K.preferences.PREF_EMAIL,oap.getEmail());
                    sharedPreferencesHelper.putString(oap.getId() + "" + K.preferences.PREF_NOME,oap.getRazaoSocial());
                    sharedPreferencesHelper.putBoolean(oap.getId() + "" + K.preferences.PREF_BOOLEAN,oap.isConectado());
                    sharedPreferencesHelper.commit();
                    Toast.makeText(context,"SUCESSO!",Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, TelaPrincipal.class));
                }else{
                    String[] exceptions = context.getResources().getStringArray(R.array.createUserExceptions);
                    try{
                        throw task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){
                        erro = exceptions[0];
                        Toast.makeText(context,erro,Toast.LENGTH_SHORT).show();
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        erro = exceptions[1];
                        Toast.makeText(context,erro,Toast.LENGTH_SHORT).show();
                    }catch(FirebaseAuthUserCollisionException e){
                        erro = exceptions[2];
                        Toast.makeText(context,erro,Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        erro = exceptions[3];
                        Toast.makeText(context,erro,Toast.LENGTH_SHORT).show();
                    }// End try
                    progresso.closeProgressDialog();
                }// End else
            }});
    }// End createUser()

    public void signIn(String email,String senha){
        final ProgressHelper progresso = new ProgressHelper(
                context
                , ProgressDialog.STYLE_SPINNER
                ,false
                ,context.getString(R.string.inicio)
                ,context.getString(R.string.iniciosistema));
        progresso.showProgressDialog();

        firebaseAuth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                String erro = "";
                if(task.isSuccessful()){
                    context.startActivity(new Intent(context, TelaPrincipal.class));
                }else{
                    String[] exceptions = context.getResources().getStringArray(R.array.signInExceptions);
                   try{
                       throw task.getException();
                   }catch(FirebaseAuthInvalidUserException e){
                       erro = exceptions[0];
                       Toast.makeText(context,erro,Toast.LENGTH_SHORT).show();
                   }catch(FirebaseAuthInvalidCredentialsException e){
                       erro = exceptions[1];
                       Toast.makeText(context,erro,Toast.LENGTH_SHORT).show();
                   }catch(Exception e){
                       erro = exceptions[2];
                       Toast.makeText(context,erro,Toast.LENGTH_SHORT).show();
                   }// End catch
                    progresso.closeProgressDialog();
                }// End else
            }});
    }// End signIn()
}// End class FirebaseAuthControllerAcess
