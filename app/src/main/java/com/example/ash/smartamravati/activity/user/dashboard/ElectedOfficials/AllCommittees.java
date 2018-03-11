package com.example.ash.smartamravati.activity.user.dashboard.ElectedOfficials;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.dashboard.Administration.DepartmentInfo;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shockwave.pdfium.PdfDocument;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class AllCommittees extends AppCompatActivity {

    PDFView pdfView;

    Integer pageNumber = 0;

    FirebaseAuth firebaseAuth;
    StorageReference mStorageRef;

    private  String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_committees);

        pdfView = (PDFView) findViewById(R.id.pdfView);


        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        DatabaseReference mRootRefer = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mChildrefer = mRootRefer.child("Elected Officials").child("All Committee List").child("url");

        mChildrefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                url = dataSnapshot.getValue().toString();

                 new RetrievePDFStream().execute(url);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

      //  new RetrievePDFStream().execute(url);
    }


    class RetrievePDFStream extends AsyncTask<String,Void,InputStream> implements OnPageChangeListener, OnLoadCompleteListener {

        ProgressDialog progressDialog;
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(AllCommittees.this);
            progressDialog.setTitle("getting the content...");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

        }

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
            progressDialog.dismiss();
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
}
