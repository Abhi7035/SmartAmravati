package com.example.ash.smartamravati.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ash.smartamravati.R;

public class BirthCertificate extends AppCompatActivity implements View.OnClickListener{
public Button button9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_certificate);
        button9=(Button)findViewById(R.id.button9);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==button9)
        {
            Intent tp9= new Intent(BirthCertificate.this,BirthRegistration.class);
            startActivity(tp9);
        }
    }
}
