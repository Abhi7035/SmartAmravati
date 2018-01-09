package com.example.ash.smartamravati.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ash.smartamravati.R;

public class DeathRegistration extends AppCompatActivity implements View.OnClickListener {
public Button Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_registration);
        Next=(Button)findViewById(R.id.Next);
        Next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==Next)
        {
            Intent tp1=new Intent(DeathRegistration.this,DeathRegistrationNext.class);
            startActivity(tp1);
        }

    }
}
