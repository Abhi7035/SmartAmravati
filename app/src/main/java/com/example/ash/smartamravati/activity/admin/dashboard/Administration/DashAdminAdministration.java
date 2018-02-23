package com.example.ash.smartamravati.activity.admin.dashboard.Administration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.admin.dashboard.ElectedOfficials.DeputyMayorProfile;
import com.example.ash.smartamravati.activity.admin.dashboard.ElectedOfficials.MayorProfile;
import com.example.ash.smartamravati.activity.admin.dashboard.ElectedOfficials.StandingChairmanProfile;

public class DashAdminAdministration extends AppCompatActivity {

    TextView text1, text2, text3, text4, text5, text6;
    ImageView image1, image2, image3, image4, image5, image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_admin_administration);

        text1 = (TextView)findViewById(R.id.text1);

        text2 = (TextView)findViewById(R.id.text2);

        text3 = (TextView)findViewById(R.id.text3);

        text4 = (TextView)findViewById(R.id.text4);

        text5 = (TextView)findViewById(R.id.text5);

        text6 = (TextView)findViewById(R.id.text6);



        image1 = (ImageView)findViewById(R.id.image1);

        image2 = (ImageView)findViewById(R.id.image2);

        image3 = (ImageView)findViewById(R.id.image3);

        image4 = (ImageView)findViewById(R.id.image4);

        image5 = (ImageView)findViewById(R.id.image5);

        image6 = (ImageView)findViewById(R.id.image6);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commi();
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commi();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcommi();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcommi();
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dycommi();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dycommi();
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depart();
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depart();
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoneoff();
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoneoff();
            }
        });

        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offemp();
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offemp();
            }
        });
    }

    private void commi() {
        Intent myIntent = new Intent(this, CommissionerProfile.class);
        startActivity(myIntent);
    }

    private void addcommi() {
        Intent myIntent = new Intent(this, AdditionalCommissionerProfile.class);
        startActivity(myIntent);
    }

    private void dycommi() {
        Intent myIntent = new Intent(this, DeputyCommissionerProfile.class);
        startActivity(myIntent);
    }

    private void depart() {
        Intent myIntent = new Intent(this, DepartPdf.class);
        startActivity(myIntent);
    }

    private void zoneoff() {
        Intent myIntent = new Intent(this, ZonalOffices.class);
        startActivity(myIntent);
    }

    private void offemp() {
        Intent myIntent = new Intent(this, OfficersAndEmployees.class);
        startActivity(myIntent);
    }

}
