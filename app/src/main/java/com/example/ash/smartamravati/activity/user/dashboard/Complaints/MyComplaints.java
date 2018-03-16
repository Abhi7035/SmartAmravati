package com.example.ash.smartamravati.activity.user.dashboard.Complaints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.admin.dashboard.Notification.AdminPreviousNotification;
import com.example.ash.smartamravati.activity.admin.dashboard.Notification.Notification;
import com.example.ash.smartamravati.activity.admin.dashboard.Notification.NotificationRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyComplaints extends AppCompatActivity {

    private RecyclerView complaint_list_view;
    private List<Complaint> complaint_list;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private ComplaintRecyclerAdapter complaintRecyclerAdapter;

    private DocumentSnapshot lastVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaints);

        complaint_list = new ArrayList<>();
        complaint_list_view = (RecyclerView)findViewById(R.id.complaint_list_view);

        firebaseAuth = FirebaseAuth.getInstance();

        complaintRecyclerAdapter = new ComplaintRecyclerAdapter(complaint_list);
        complaint_list_view.setLayoutManager(new LinearLayoutManager(this));
        complaint_list_view.setAdapter(complaintRecyclerAdapter);



        if(firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            complaint_list_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if(reachedBottom){


                        Toast.makeText(MyComplaints.this, "Bottom Reached : " , Toast.LENGTH_SHORT).show();


                    }

                }
            });


            Query firstQuery = firebaseFirestore.collection("Complaints")
                    .orderBy("timestamp", Query.Direction.DESCENDING);
            firstQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() -1);

                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            Complaint blogPost = doc.getDocument().toObject(Complaint.class);
                            complaint_list.add(blogPost);

                            complaintRecyclerAdapter.notifyDataSetChanged();

                        }
                    }

                }
            });

        }
    }



}

