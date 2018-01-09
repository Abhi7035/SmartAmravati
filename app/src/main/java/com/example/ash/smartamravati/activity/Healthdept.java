package com.example.ash.smartamravati.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ash.smartamravati.R;

public class Healthdept extends AppCompatActivity implements View.OnClickListener {
public ImageView image;
public TextView text;
    public ImageView imageView;
    public TextView text12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthdept);
        image=(ImageView)findViewById(R.id.image);
        image.setOnClickListener(this);
        text=(TextView) findViewById(R.id.text);
        text.setOnClickListener(this);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        text12=(TextView) findViewById(R.id.text12);
        text12.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==image)
        {
            Intent tp6 =new Intent(Healthdept.this,DeathCertificate.class);
            startActivity(tp6);
        }
        if(view==text)
        {
            Intent tp7 =new Intent(Healthdept.this,DeathCertificate.class);
            startActivity(tp7);
        }
        if(view==imageView)
        {
            Intent tp8 =new Intent(Healthdept.this,BirthCertificate.class);
            startActivity(tp8);
        }
        if(view==text12)
        {
            Intent tp9 =new Intent(Healthdept.this,BirthCertificate.class);
            startActivity(tp9);
        }

    }
}
