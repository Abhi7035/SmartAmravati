package com.example.ash.smartamravati.activity.user.dashboard.Complaints;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.dashboard.NavigationDrawer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import id.zelory.compressor.Compressor;

public class NewComplaint extends AppCompatActivity {


    EditText textView3, textView4, textView5, textView6;
    Button btnSave, btnCancel;
    ImageView imageView;

    FirebaseAuth mAuth;
    private Uri postImageUri = null;

    private ProgressBar newProgress;

    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    private String current_user_id;

    private Bitmap compressedImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint);


        textView3 = (EditText) findViewById(R.id.textView3);
        textView4 = (EditText) findViewById(R.id.textView4);
        textView5 = (EditText) findViewById(R.id.textView5);
        textView6 = (EditText) findViewById(R.id.textView6);

        imageView = (ImageView) findViewById(R.id.imageView);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);


        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        newProgress = findViewById(R.id.new_post_progress);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(NewComplaint.this);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));
                Toast.makeText(NewComplaint.this,"Complaint Cancel !!",Toast.LENGTH_SHORT).show();

            }
        });

       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               final String sub = textView3.getText().toString();
               final String name = textView4.getText().toString();
               final String Mobile = textView5.getText().toString();
               final String comDet = textView6.getText().toString();

               if (!TextUtils.isEmpty(sub) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(Mobile) && !TextUtils.isEmpty(comDet) && postImageUri != null)
               {
                   newProgress.setVisibility(View.VISIBLE);

                   final String randomName = UUID.randomUUID().toString();

                   StorageReference filePath = storageReference.child("Complaint_images").child(randomName + ".jpg");
                   filePath.putFile(postImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                           final String downloadUri = task.getResult().getDownloadUrl().toString();

                           if (task.isSuccessful()){
                               File newImageFile = new File(postImageUri.getPath());
                               try {

                                   compressedImageFile = new Compressor(NewComplaint.this)
                                           .setMaxHeight(100)
                                           .setMaxWidth(100)
                                           .setQuality(2)
                                           .compressToBitmap(newImageFile);

                               } catch (IOException e) {
                                   e.printStackTrace();
                               }

                               ByteArrayOutputStream baos = new ByteArrayOutputStream();
                               compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                               byte[] thumbData = baos.toByteArray();

                               UploadTask uploadTask = storageReference.child("Complaint_images/thumbs")
                                       .child(randomName + ".jpg").putBytes(thumbData);

                               uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                   @Override
                                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                       String downloadthumbUri = taskSnapshot.getDownloadUrl().toString();

                                       Map<String, Object> postMap = new HashMap<>();
                                       postMap.put("image_url", downloadUri);
                                       postMap.put("image_thumb", downloadthumbUri);
                                       postMap.put("subject", sub);
                                       postMap.put("name", name);
                                       postMap.put("mobile", Mobile);
                                       postMap.put("complaint_Details", comDet);
                                       postMap.put("timestamp", FieldValue.serverTimestamp());

                                       firebaseFirestore.collection("Complaints").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DocumentReference> task) {

                                               if (task.isSuccessful()) {

                                                   Toast.makeText(NewComplaint.this, "Complaint was added", Toast.LENGTH_LONG).show();
                                                   Intent mainIntent = new Intent(NewComplaint.this, NavigationDrawer.class);
                                                   startActivity(mainIntent);
                                                   finish();

                                               } else {


                                               }

                                               newProgress.setVisibility(View.INVISIBLE);

                                           }
                                       });

                                   }
                               }).addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {

                                       //Error handling

                                   }
                               });



                           }

                       }
                   });

               }else {

                   Toast.makeText(NewComplaint.this, "Select all Fields", Toast.LENGTH_LONG).show();

                   newProgress.setVisibility(View.INVISIBLE);
               }
           }
       });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                imageView.setImageURI(postImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }
}
