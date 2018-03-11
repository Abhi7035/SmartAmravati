package com.example.ash.smartamravati.activity.user.dashboard.AmravatiCity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ash.smartamravati.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class GenralInformationAboutCity extends AppCompatActivity {


    PDFView pdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genral_information_about_city);

        pdfView = (PDFView) findViewById(R.id.pdfView);


        pdfView.fromAsset("Amravati-City-Information.pdf")
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();

    }
}
