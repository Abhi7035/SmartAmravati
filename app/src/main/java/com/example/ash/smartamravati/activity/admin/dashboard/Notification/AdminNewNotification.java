package com.example.ash.smartamravati.activity.admin.dashboard.Notification;

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
import com.example.ash.smartamravati.activity.admin.dashboard.AdminDashboard;
import com.example.ash.smartamravati.activity.admin.profile.AdminProfile;
import com.example.ash.smartamravati.activity.user.dashboard.NavigationDrawer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

public class AdminNewNotification extends AppCompatActivity {
    private ImageView newPostImage;
    private EditText newPostTittle, newPostDesc;
    private Button newPostBtn;


    DatabaseReference UserDatabase;
    StorageReference StorageRef;

    Uri imageHoldUri = null;
    private ProgressBar newPostProgress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_notification);

        UserDatabase = FirebaseDatabase.getInstance().getReference();
        StorageRef = FirebaseStorage.getInstance().getReference();


        newPostImage = findViewById(R.id.new_post_image);
        newPostDesc = findViewById(R.id.new_post_desc);
        newPostTittle = findViewById(R.id.new_post_tittle);
        newPostBtn = findViewById(R.id.post_btn);
        newPostProgress = findViewById(R.id.new_post_progress);


        newPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(AdminNewNotification.this);

            }
        });


        newPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String tittle = newPostTittle.getText().toString();

                final String desc = newPostDesc.getText().toString();

                if(!TextUtils.isEmpty(tittle) && !TextUtils.isEmpty(desc) && imageHoldUri != null){

                    newPostProgress.setVisibility(View.VISIBLE);

                    StorageReference mChildStorage = StorageRef.child("Notification").child(imageHoldUri.getLastPathSegment());


                    mChildStorage.putFile(imageHoldUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            final Uri imageUrl = taskSnapshot.getDownloadUrl();

                            UserDatabase.child("Notification").child(tittle).child("Tittle").setValue(tittle);
                            UserDatabase.child("Notification").child(tittle).child("Desc").setValue(desc);
                            UserDatabase.child("Notification").child(tittle).child("Notification pic url").setValue(imageUrl.toString());

                            newPostProgress.setVisibility(View.INVISIBLE);


                            Toast.makeText(AdminNewNotification.this, "Notification Posted Sucessfully", Toast.LENGTH_LONG).show();
                            finish();
                            Intent myIntent = new Intent(AdminNewNotification.this, AdminDashboard.class);
                            startActivity(myIntent);
                        }

                    });
                }
                else {
                    Toast.makeText(AdminNewNotification.this, "Please select All Fields", Toast.LENGTH_LONG).show();
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

                imageHoldUri = result.getUri();
                newPostImage.setImageURI(imageHoldUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }


}
