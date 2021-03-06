package com.example.ash.smartamravati.activity.user.dashboard.sidemenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.admin.dashboard.Notification.AdminPreviousNotification;
import com.example.ash.smartamravati.activity.admin.dashboard.Notification.Notification;
import com.example.ash.smartamravati.activity.admin.dashboard.Notification.NotificationRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    private RecyclerView notification_list_view;
    private List<Notification> notification_list;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private NotificationRecyclerAdapter notificationRecyclerAdapter;

    private DocumentSnapshot lastVisible;

    private View v;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_notification, container, false);

        notification_list = new ArrayList<>();
        notification_list_view = (RecyclerView) v.findViewById(R.id.notification_list_view);

        firebaseAuth = FirebaseAuth.getInstance();

        notificationRecyclerAdapter = new NotificationRecyclerAdapter(notification_list);
        notification_list_view.setLayoutManager(new LinearLayoutManager(container.getContext()));
        notification_list_view.setAdapter(notificationRecyclerAdapter);

        if (firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            notification_list_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if (reachedBottom) {

                        String desc = lastVisible.getString("desc");
                        Toast.makeText(container.getContext(), "Reached : " + desc, Toast.LENGTH_SHORT).show();


                    }

                }
            });


            Query firstQuery = firebaseFirestore.collection("Posts").orderBy("timestamp", Query.Direction.DESCENDING);
            firstQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);

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
        return v;
    }

}
