package com.example.ash.smartamravati.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ash.smartamravati.R;

public class AllService extends AppCompatActivity implements View.OnClickListener{
   public ImageView image;
   public TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_service);
        image=(ImageView) findViewById(R.id.image);
        image.setOnClickListener(this);
        text=(TextView)findViewById(R.id.text);
        text.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==image)
        {
            Intent tp5=new Intent(AllService.this,Healthdept.class);
            startActivity(tp5);
        }
        if(view==text)
        {
            Intent tp6= new Intent(AllService.this,Healthdept.class);
            startActivity(tp6);
        }

    }
}
