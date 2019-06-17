package com.work.vigilantes.helper;

import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper{
    private static FirebaseAuth firebaseAuth;
    private static DatabaseReference databaseReference;

    public static FirebaseAuth getAuthReference(){
        if(firebaseAuth == null) firebaseAuth = firebaseAuth.getInstance();
        return firebaseAuth;
    }// End getAuthReference()

    public static DatabaseReference getDatabaseReference(){
        if(databaseReference == null) databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference;
    }// End getDatabaseReferece()
}// End FirebaseHelper
