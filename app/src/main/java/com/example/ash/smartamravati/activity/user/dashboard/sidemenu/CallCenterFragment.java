package com.example.ash.smartamravati.activity.user.dashboard.sidemenu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ash.smartamravati.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallCenterFragment extends Fragment {


    public CallCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_center, container, false);
    }

}