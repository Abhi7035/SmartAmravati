package com.example.ash.smartamravati.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ash.smartamravati.R;

public class DeathCertificate extends AppCompatActivity implements View.OnClickListener {
public Button button12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_certificate);
        button12=(Button)findViewById(R.id.button12);
        button12.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view==button12)
        {
            Intent tp4 =new Intent(DeathCertificate.this,DeathRegistration.class);
            startActivity(tp4);
        }
    }
}
