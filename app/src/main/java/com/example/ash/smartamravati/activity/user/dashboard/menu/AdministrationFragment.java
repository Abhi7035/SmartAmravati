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
import com.example.ash.smartamravati.activity.user.dashboard.Administration.AdditionalCommissionerInfo;
import com.example.ash.smartamravati.activity.user.dashboard.Administration.CommissionerInfo;
import com.example.ash.smartamravati.activity.user.dashboard.Administration.DepartmentInfo;
import com.example.ash.smartamravati.activity.user.dashboard.Administration.DeputyCommissionerInfo;
import com.example.ash.smartamravati.activity.user.dashboard.Administration.OfficersAndEmployeesInfo;
import com.example.ash.smartamravati.activity.user.dashboard.Administration.ZonalOfficesInfo;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.CityImprovementChairman;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.DeputyMayor;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.LawChairman;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.Mayor;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.StandingChairman;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.WomanAndChildChairman;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdministrationFragment extends Fragment {

    TextView text1, text2, text3, text4, text5, text6;
    ImageView image1, image2, image3, image4, image5, image6;
    private View v;

    public AdministrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_administration,container, false);
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

        image1 = (ImageView)v.findViewById(R.id.image1);

        image2 = (ImageView)v.findViewById(R.id.image2);

        image3 = (ImageView)v.findViewById(R.id.image3);

        image4 = (ImageView)v.findViewById(R.id.image4);

        image5 = (ImageView)v.findViewById(R.id.image5);

        image6 = (ImageView)v.findViewById(R.id.image6);


        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommissionerInfo.class);
                startActivity(intent);
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdditionalCommissionerInfo.class);
                startActivity(intent);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DeputyCommissionerInfo.class);
                startActivity(intent);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DepartmentInfo.class);
                startActivity(intent);
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ZonalOfficesInfo.class);
                startActivity(intent);
            }
        });

        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), OfficersAndEmployeesInfo.class);
                startActivity(intent);
            }
        });


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommissionerInfo.class);
                startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdditionalCommissionerInfo.class);
                startActivity(intent);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DeputyCommissionerInfo.class);
                startActivity(intent);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DepartmentInfo.class);
                startActivity(intent);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ZonalOfficesInfo.class);
                startActivity(intent);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), OfficersAndEmployeesInfo.class);
                startActivity(intent);
            }
        });

    }

}
