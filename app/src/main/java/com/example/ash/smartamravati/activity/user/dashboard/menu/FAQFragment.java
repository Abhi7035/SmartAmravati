package com.example.ash.smartamravati.activity.user.dashboard.menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials.Mayor;
import com.example.ash.smartamravati.activity.user.dashboard.FAQ.Fire_Faq;
import com.example.ash.smartamravati.activity.user.dashboard.FAQ.Garden_Faq;
import com.example.ash.smartamravati.activity.user.dashboard.FAQ.Health_FAQ;
import com.example.ash.smartamravati.activity.user.dashboard.FAQ.Marriage_Faq;
import com.example.ash.smartamravati.activity.user.dashboard.FAQ.Town_Faq;
import com.example.ash.smartamravati.activity.user.dashboard.FAQ.Water_Faq;

/**
 * A simple {@link Fragment} subclass.
 */
public class FAQFragment extends Fragment implements View.OnClickListener {

    private View v;
    public CardView card1;
    public CardView card2;
    public CardView card5;
    public CardView card6;
    public CardView card3;
    public CardView card4;

    public FAQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_faq,container, false);
        configureImageButton0();
        return v;
    }

    private void configureImageButton0() {


        card1=(CardView)v.findViewById(R.id.card1);
        card1.setOnClickListener(this);
        card5=(CardView)v.findViewById(R.id.card5);
        card5.setOnClickListener(this);
        card2=(CardView)v.findViewById(R.id.card2);
        card2.setOnClickListener(this);
        card6=(CardView)v.findViewById(R.id.card6);
        card6.setOnClickListener(this);
        card3=(CardView)v.findViewById(R.id.card3);
        card3.setOnClickListener(this);
        card4=(CardView)v.findViewById(R.id.card4);
        card4.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view==card1)
        {

            Intent tp1= new Intent(getActivity(),Health_FAQ.class);
            startActivity(tp1);
        }
        if(view==card3)
        {
            Intent tp1= new Intent(getActivity(),Water_Faq.class);
            startActivity(tp1);
        }
        if(view==card5)
        {
            Intent tp2= new Intent(getActivity(),Town_Faq.class);
            startActivity(tp2);
        }
        if(view==card2)
        {
            Intent tp3= new Intent(getActivity(),Fire_Faq.class);
            startActivity(tp3);
        }
        if(view==card6)
        {
            Intent tp4= new Intent(getActivity(),Garden_Faq.class);
            startActivity(tp4);
        }
        if(view==card4)
        {
            Intent tp4= new Intent(getActivity(),Marriage_Faq.class);
            startActivity(tp4);
        }

    }
}
