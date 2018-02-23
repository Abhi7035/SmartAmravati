package com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ash.smartamravati.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AllMembers extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

    PDFView pdfView;

    Integer pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_members);


        pdfView = (PDFView)findViewById(R.id.pdfView);

        new RetrievePDFStream().execute("https://firebasestorage.googleapis.com/v0/b/smartamc-14986.appspot.com/o/Elected_Officials_Profile%2FAllWardMembers%2F3.%20All_Members_List_2017.pdf?alt=media&token=da929b01-4f91-49e5-ac31-d5e7a825fe72");
    }


    @Override
    public void onPageChanged(int page, int pageCount) {

        pageNumber = page;
        setTitle(String.format("%s %s / %s","All_Members_List_2017.pdf", page + 1, pageCount));
    }

    class RetrievePDFStream extends AsyncTask<String,Void,InputStream> implements OnPageChangeListener, OnLoadCompleteListener {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode() == 200)
                {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e)
            {
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream)
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .onPageChange(this)
                    .enableAnnotationRendering(true)
                    .onLoad(this)
                    .load();
        }

        @Override
        public void onPageChanged(int page, int pageCount) {

            pageNumber = page;
            setTitle(String.format("%s %s / %s","All_Committee_List_2017.pdf", page + 1, pageCount));

        }

        @Override
        public void loadComplete(int nbPages) {
            PdfDocument.Meta meta = pdfView.getDocumentMeta();
            printBookmarksTree(pdfView.getTableOfContents(), "-");

        }

        private void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {


            for (PdfDocument.Bookmark b : tree) {

                if (b.hasChildren()) {
                    printBookmarksTree(b.getChildren(), sep + "-");
                }
            }

        }

    }


    @Override
    public void loadComplete(int nbPages) {


        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    private void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {


        for (PdfDocument.Bookmark b : tree) {

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }

    }

}
