package com.example.ash.smartamravati.activity.user.dashboard.menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.GalleryFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintsFragment extends Fragment {

    public ImageView imageView1;
    public TextView text1;
    public ImageView imageView2;
    public TextView text2;
    private View v;

    public ComplaintsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_complaints,container, false);
        configureImageButton0();
        return v;

    }

    private void configureImageButton0() {

        imageView1 = (ImageView)v.findViewById(R.id.imageView1);
        imageView2 = (ImageView)v.findViewById(R.id.imageView2);
        text1 = (TextView) v.findViewById(R.id.text1);
        text2 = (TextView) v.findViewById(R.id.text2);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), NewComplaint.class);
                startActivity(intent);

            }
        });

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), NewComplaint.class);
                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getActivity(), MyComplaints.class);
                startActivity(intent);

            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MyComplaints.class);
                startActivity(intent);

            }
        });
    }



}
