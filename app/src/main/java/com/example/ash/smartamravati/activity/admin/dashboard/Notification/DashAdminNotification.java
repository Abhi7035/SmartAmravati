package com.example.ash.smartamravati.activity.admin.dashboard.Notification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.dashboard.menu.MyComplaints;
import com.example.ash.smartamravati.activity.user.dashboard.menu.NewComplaint;

public class DashAdminNotification extends AppCompatActivity {

    public ImageView imageView1;
    public TextView text1;
    public ImageView imageView2;
    public TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_admin_notification);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myIntent = new Intent(DashAdminNotification.this, AdminNewNotification.class);
                startActivity(myIntent);

            }
        });

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myIntent = new Intent(DashAdminNotification.this, AdminNewNotification.class);
                startActivity(myIntent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent myIntent = new Intent(DashAdminNotification.this, AdminPreviousNotifications.class);
                startActivity(myIntent);

            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(DashAdminNotification.this, AdminPreviousNotifications.class);
                startActivity(myIntent);


            }
        });
    }


}

