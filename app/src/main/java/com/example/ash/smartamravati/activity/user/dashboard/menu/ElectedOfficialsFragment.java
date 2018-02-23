package com.example.ash.smartamravati.activity.user.dashboard.menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.AllCommittees;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.AllMembers;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.CityImprovementChairman;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.DeputyMayor;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.LawChairman;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.Mayor;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.StandingChairman;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.WomanAndChildChairman;


public class ElectedOfficialsFragment extends Fragment {

    TextView text1, text2, text3, text4, text5, text6, text7, text8;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8;
    private View v;


    public ElectedOfficialsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_elected_officials,container, false);
        configureImageButton0();
        return v;
    }

    private void configureImageButton0() {

        text1 = (TextView)v.findViewById(R.id.text1);

        text2 = (TextView)v.findViewById(R.id.text2);

        text3 = (TextView)v.findViewById(R.id.text3);

        text4 = (TextView)v.findViewById(R.id.text4);

        text5 = (TextView)v.findViewById(R.id.text5);

        text6 = (TextView)v.findViewById(R.id.text6);

        text7 = (TextView)v.findViewById(R.id.text7);

        text8 = (TextView)v.findViewById(R.id.text8);


        image1 = (ImageView)v.findViewById(R.id.image1);

        image2 = (ImageView)v.findViewById(R.id.image2);

        image3 = (ImageView)v.findViewById(R.id.image3);

        image4 = (ImageView)v.findViewById(R.id.image4);

        image5 = (ImageView)v.findViewById(R.id.image5);

        image6 = (ImageView)v.findViewById(R.id.image6);

        image7 = (ImageView)v.findViewById(R.id.image7);

        image8 = (ImageView)v.findViewById(R.id.image8);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Mayor.class);
                startActivity(intent);
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DeputyMayor.class);
                startActivity(intent);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), StandingChairman.class);
                startActivity(intent);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CityImprovementChairman.class);
                startActivity(intent);
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), LawChairman.class);
                startActivity(intent);
            }
        });

        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), WomanAndChildChairman.class);
                startActivity(intent);
            }
        });

        text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AllCommittees.class);
                startActivity(intent);
            }
        });

        text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AllMembers.class);
                startActivity(intent);
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Mayor.class);
                startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DeputyMayor.class);
                startActivity(intent);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), StandingChairman.class);
                startActivity(intent);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CityImprovementChairman.class);
                startActivity(intent);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), LawChairman.class);
                startActivity(intent);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), WomanAndChildChairman.class);
                startActivity(intent);
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AllCommittees.class);
                startActivity(intent);
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AllMembers.class);
                startActivity(intent);
            }
        });
    }

}
