package com.example.ash.smartamravati.activity.admin.dashboard.Notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ash.smartamravati.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminPreviousNotification extends AppCompatActivity {

    private RecyclerView notification_list_view;
    private List<Notification> notification_list;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private NotificationRecyclerAdapter notificationRecyclerAdapter;

    private DocumentSnapshot lastVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_previous_notification);


        notification_list = new ArrayList<>();
        notification_list_view = (RecyclerView)findViewById(R.id.notification_list_view);

        firebaseAuth = FirebaseAuth.getInstance();

        notificationRecyclerAdapter = new NotificationRecyclerAdapter(notification_list);
        notification_list_view.setLayoutManager(new LinearLayoutManager(this));
        notification_list_view.setAdapter(notificationRecyclerAdapter);

        if(firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            notification_list_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if(reachedBottom){

                        String desc = lastVisible.getString("desc");
                        Toast.makeText(AdminPreviousNotification.this, "Reached : " + desc, Toast.LENGTH_SHORT).show();


                    }

                }
            });


            Query firstQuery = firebaseFirestore.collection("Posts")
                    .orderBy("timestamp", Query.Direction.DESCENDING);
            firstQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() -1);

                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            Notification blogPost = doc.getDocument().toObject(Notification.class);
                            notification_list.add(blogPost);

                            notificationRecyclerAdapter.notifyDataSetChanged();

                        }
                    }

                }
            });

        }
    }



}
