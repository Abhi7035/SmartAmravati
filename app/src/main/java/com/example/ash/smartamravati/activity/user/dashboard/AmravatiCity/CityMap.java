package com.example.ash.smartamravati.activity.user.dashboard.AmravatiCity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ash.smartamravati.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class CityMap extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_map);


        pdfView = (PDFView) findViewById(R.id.pdfView);

        pdfView.fromAsset("amc_election_maps_2017_small-size.pdf")
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();


    }
}
