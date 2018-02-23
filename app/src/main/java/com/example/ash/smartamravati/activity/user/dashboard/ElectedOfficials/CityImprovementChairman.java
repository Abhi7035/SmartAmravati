package com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class CityImprovementChairman extends AppCompatActivity {

    ImageView imageView;
    TextView name, post, mobileNo, email, address, msg;

    Spinner tittle;
    String Text;

    FirebaseAuth firebaseAuth;
    StorageReference mStorageRef;

    private  String mName;
    private  String mEmail;
    private  String mMsg;
    private  String mAddress;
    private  String mMobileNo;
    private  String mPost;
    private  String userID;
    private  String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_improvement_chairman);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        name = (TextView)findViewById(R.id.name);
        post = (TextView)findViewById(R.id.post);
        mobileNo = (TextView)findViewById(R.id.mobileNo);
        email = (TextView)findViewById(R.id.email);
        address = (TextView)findViewById(R.id.address);
        msg = (TextView)findViewById(R.id.msg);

        imageView = (ImageView)findViewById(R.id.profileImage3);

        tittle = (Spinner)findViewById(R.id.fromspin);


        DatabaseReference mRootRefer = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mChildrefer = mRootRefer.child("Elected Officials").child("City Improvement Committee Chairman").child("Name");
        DatabaseReference mChildrefer1 = mRootRefer.child("Elected Officials").child("City Improvement Committee Chairman").child("Designation");
        DatabaseReference mChildrefer2 = mRootRefer.child("Elected Officials").child("City Improvement Committee Chairman").child("Address");
        DatabaseReference mChildrefer3 = mRootRefer.child("Elected Officials").child("City Improvement Committee Chairman").child("Email");
        DatabaseReference mChildrefer4 = mRootRefer.child("Elected Officials").child("City Improvement Committee Chairman").child("Mobile No");
        DatabaseReference mChildrefer5 = mRootRefer.child("Elected Officials").child("City Improvement Committee Chairman").child("Message");
        DatabaseReference mChildrefer6 = mRootRefer.child("Elected Officials").child("City Improvement Committee Chairman").child("Profile pic url");


        mChildrefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mName = dataSnapshot.getValue().toString();
                name.setText(mName);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPost = dataSnapshot.getValue().toString();
                post.setText(mPost);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mAddress = dataSnapshot.getValue().toString();
                address.setText(mAddress);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mEmail = dataSnapshot.getValue().toString();
                email.setText(mEmail);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mMobileNo = dataSnapshot.getValue().toString();
                mobileNo.setText(mMobileNo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mMsg = dataSnapshot.getValue().toString();
                msg.setText(mMsg);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChildrefer6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imageURL= dataSnapshot.getValue().toString();
                Glide.with(getApplicationContext()).load(imageURL).bitmapTransform(new CircleTransform(getApplicationContext())).into(imageView);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
