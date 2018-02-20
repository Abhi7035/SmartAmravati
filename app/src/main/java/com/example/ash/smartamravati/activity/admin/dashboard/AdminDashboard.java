package com.example.ash.smartamravati.activity.admin.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ash.smartamravati.R;
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

    TextView textView9, textView10, textView11;
    ImageView imageView1;

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

        textView9 = (TextView)findViewById(R.id.textView9);
        textView10 = (TextView)findViewById(R.id.textView10);
        textView11 = (TextView)findViewById(R.id.textView11);

        imageView1 = (ImageView)findViewById(R.id.imageView1);


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
}
