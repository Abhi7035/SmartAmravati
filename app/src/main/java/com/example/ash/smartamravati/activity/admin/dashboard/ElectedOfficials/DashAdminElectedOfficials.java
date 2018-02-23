package com.example.ash.smartamravati.activity.admin.dashboard.ElectedOfficials;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ash.smartamravati.R;

public class DashAdminElectedOfficials extends AppCompatActivity {

    TextView text1, text2, text3, text4, text5, text6;
    ImageView image1, image2, image3, image4, image5, image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_admin_elected_officials);


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
                mayor();
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mayor();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deputyMayor();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deputyMayor();
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sChairman();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sChairman();
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cChairman();
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cChairman();
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lChairman();
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lChairman();
            }
        });

        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wChairman();
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wChairman();
            }
        });
    }

    private void wChairman() {
        Intent myIntent = new Intent(this, WomanAndChildChairmanProfile.class);
        startActivity(myIntent);
    }

    private void lChairman() {
        Intent myIntent = new Intent(this, LawChairmanProfile.class);
        startActivity(myIntent);
    }

    private void cChairman() {
        Intent myIntent = new Intent(this, CityImproveChairmanProfile.class);
        startActivity(myIntent);
    }

    private void sChairman() {
        Intent myIntent = new Intent(this, StandingChairmanProfile.class);
        startActivity(myIntent);
    }

    private void deputyMayor() {
        Intent myIntent = new Intent(this, DeputyMayorProfile.class);
        startActivity(myIntent);
    }

    private void mayor() {
        Intent myIntent = new Intent(this, MayorProfile.class);
        startActivity(myIntent);
    }
}
