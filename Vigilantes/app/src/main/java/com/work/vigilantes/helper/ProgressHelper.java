package com.work.vigilantes.helper;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressHelper{
    private ProgressDialog progressDialog;
    private int type;
    private boolean cancelable;
    private String title;
    private String message;

    public ProgressHelper(Context context, int type, boolean cancelable, String title,String message){
        this.progressDialog = new ProgressDialog(context);
        this.type = type;
        this.cancelable = cancelable;
        this.title = title;
        this.message = message;
    }// End Progresso()

    public void showProgressDialog(){
        progressDialog.setProgressStyle(this.type);
        progressDialog.setCancelable(this.cancelable);
        progressDialog.setTitle(this.title);
        progressDialog.setMessage(this.message);
        progressDialog.show();
    }// End showProgressDialog()

    public void closeProgressDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }// End if
    }// End disableProgressDialog()
}// End ProgressHelper
