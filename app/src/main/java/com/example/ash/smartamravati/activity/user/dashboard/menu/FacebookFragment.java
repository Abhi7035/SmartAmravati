package com.example.ash.smartamravati.activity.user.dashboard.menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.ash.smartamravati.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookFragment extends Fragment {

    private WebView mWebView = null;
    public View v;


    public FacebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_facebook, container, false);
        mWebView = (WebView) v.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://mobile.facebook.com/AmravatiMunicipalCorporation/");

        return v;

}
}
