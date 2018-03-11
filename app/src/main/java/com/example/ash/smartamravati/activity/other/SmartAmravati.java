package com.example.ash.smartamravati.activity.other;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ash on 11-03-2018.
 */

public class SmartAmravati extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }
}
