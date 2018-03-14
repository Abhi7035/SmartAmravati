package com.example.ash.smartamravati.activity.admin.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.admin.dashboard.Administration.DashAdminAdministration;
import com.example.ash.smartamravati.activity.admin.dashboard.ElectedOfficials.DashAdminElectedOfficials;
import com.example.ash.smartamravati.activity.admin.dashboard.Notification.DashAdminNotification;
import com.example.ash.smartamravati.activity.admin.login.AdminLogin;
import com.example.ash.smartamravati.activity.other.CircleTransform;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AdminDashboard extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    RelativeLayout relativeLayout;

    ImageButton imageButton;

    TextView textView8, textView9, textView10, textView11, t1;
    ImageView imageView1;

    TextView text1, text2, text3, text4, text5, text6;
    ImageView image1, image2, image3, image4, image5, image6;

    StorageReference mStorageRef;

    private  String name;
    private  String email;
    private  String department;
    private  String userID;
    private  String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        imageButton = (ImageButton)findViewById(R.id.image);

        relativeLayout = (RelativeLayout)findViewById(R.id.relativelay);

        relativeLayout.setVisibility(View.VISIBLE);

        t1 = (TextView)findViewById(R.id.t1);
        textView8 = (TextView)findViewById(R.id.textView8);
        textView9 = (TextView)findViewById(R.id.textView9);
        textView10 = (TextView)findViewById(R.id.textView10);
        textView11 = (TextView)findViewById(R.id.textView11);

        imageView1 = (ImageView)findViewById(R.id.imageView1);

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
                adminis();
            }
        });
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminis();
            }
        });


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elected();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               elected();
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification();
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification();
            }
        });


        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout.setVisibility(View.VISIBLE);
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setVisibility(View.GONE);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                signOut();

            }
        });

        DatabaseReference mRootRefer = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mChildrefer = mRootRefer.child("Admin").child(userID).child("user Name");
        DatabaseReference mChildrefer1 = mRootRefer.child("Admin").child(userID).child("user Email");
        DatabaseReference mChildrefer2 = mRootRefer.child("Admin").child(userID).child("Department");
        DatabaseReference mChildrefer3 = mRootRefer.child("Admin").child(userID).child("Profile pic url");


        mChildrefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                name = dataSnapshot.getValue().toString();
                textView10.setText(name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email = dataSnapshot.getValue().toString();
                textView11.setText(email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                department = dataSnapshot.getValue().toString();
                textView9.setText(department);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imageURL= dataSnapshot.getValue().toString();
                Glide.with(getApplicationContext()).load(imageURL).bitmapTransform(new CircleTransform(getApplicationContext())).into(imageView1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void notification() {

        Intent myIntent = new Intent(this, DashAdminNotification.class);
        startActivity(myIntent);
    }

    private void adminis() {
        Intent myIntent = new Intent(this, DashAdminAdministration.class);
        startActivity(myIntent);


    }

    private void elected() {
        Intent myIntent = new Intent(this, DashAdminElectedOfficials.class);
        startActivity(myIntent);

    }


    private void signOut() {
        Intent myIntent = new Intent(this, AdminLogin.class);
        startActivity(myIntent);
    }

}
