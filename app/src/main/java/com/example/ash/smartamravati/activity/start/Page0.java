package com.example.ash.smartamravati.activity.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.admin.login.AdminLogin;
import com.example.ash.smartamravati.activity.department.login.Department;
import com.example.ash.smartamravati.activity.user.login.Page3;

public class Page0 extends AppCompatActivity implements View.OnClickListener {
    public Button button2;
    public Button button3;
    public Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page0);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == button2) {

            startActivity(new Intent(getApplicationContext(), Page3.class));

        }
        if (view==button3)
        {
            Intent tp2=new Intent(Page0.this,Department.class);
                    startActivity(tp2);
        }
        if (view==button4)
        {
            Intent tp3=new Intent(Page0.this,AdminLogin.class);
            startActivity(tp3);
        }

    }
}
