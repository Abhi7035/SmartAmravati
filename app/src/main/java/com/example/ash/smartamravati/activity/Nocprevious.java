package com.example.ash.smartamravati.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ash.smartamravati.R;

public class Nocprevious extends AppCompatActivity implements View.OnClickListener{

    public Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nocprevious);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == next) {
            Intent tp9 = new Intent(Nocprevious.this, NocNext.class);
            startActivity(tp9);

        }

    }
}
