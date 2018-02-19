package com.example.ash.smartamravati.activity.user.dashboard.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ash.smartamravati.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyComplaints extends AppCompatActivity {

    FirebaseAuth mAuth;
    private ListView mUserList;
    private DatabaseReference childRef;
    private DatabaseReference childRef1;
    private DatabaseReference databaseReference;
    private ArrayList<String> mComplaints = new ArrayList<>();

    private  String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaints);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        childRef = databaseReference.child("Complaints");
        childRef1 = childRef.child(userID);

        mUserList = (ListView)findViewById(R.id.user_list);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mComplaints);

        mUserList.setAdapter(arrayAdapter);

        childRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    String name = dataSnapshot.getKey();

                    mComplaints.add(name);
                    arrayAdapter.notifyDataSetChanged();



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
