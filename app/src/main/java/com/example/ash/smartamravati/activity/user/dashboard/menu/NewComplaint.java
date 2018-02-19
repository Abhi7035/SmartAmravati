package com.example.ash.smartamravati.activity.user.dashboard.menu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.Profile.Page4;
import com.example.ash.smartamravati.activity.user.dashboard.NavigationDrawer;
import com.example.ash.smartamravati.activity.user.verification.VerificationActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class NewComplaint extends AppCompatActivity {



    ProgressDialog mProgress;

    private static final int REQUEST_CAMERA = 3;
    private static final int GALLERY_REQUEST = 1;
    private static final int SELECT_FILE = 1;

    EditText textView3,textView4,textView5,textView6;
    Button btnSave,btnCancel;
    ImageView imageView;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    DatabaseReference mUserDatabase;
    StorageReference mStorageRef;

    Uri imageHoldUri = null;

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


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //LOGIC CHECK USER
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {

                    finish();
                    Intent moveToHome = new Intent(NewComplaint.this, NavigationDrawer.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(moveToHome);

                }

            }
        };

        //PROGRESS DIALOG
        mProgress = new ProgressDialog(this);


        //ONCLICK SAVE Complaint
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveComplaint();
            }
        });

        //ONCLICK CANCEL Complaint
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));
                Toast.makeText(NewComplaint.this,"Complaint Cancel !!",Toast.LENGTH_SHORT).show();

            }
        });
        //USER IMAGEVIEW ONCLICK LISTENER
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //LOGIC FOR PROFILE PICTURE
                complaintPicSelection();

            }
        });
    }

    private void complaintPicSelection() {

        //DISPLAY DIALOG TO CHOOSE CAMERA OR GALLERY

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(NewComplaint.this);
        builder.setTitle("Add Photo!");

        //SET ITEMS AND THERE LISTENERS
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                } else  if (items[item].equals("Choose from Library")) {
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
        intent.setAction(Intent.ACTION_GET_CONTENT);
    }


    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_FILE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
    }

    private void saveComplaint() {

        final String subject, userName, userMobileNo, complaint;

        subject = textView3.getText().toString().trim();
        userName = textView4.getText().toString().trim();
        userMobileNo = textView5.getText().toString().trim();
        complaint = textView6.getText().toString();

        if( !TextUtils.isEmpty(subject) && !TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userMobileNo) && !TextUtils.isEmpty(complaint))
        {

            if( imageHoldUri != null )
            {

                mProgress.setTitle("Saving Complaint");
                mProgress.setMessage("Please wait....");
                mProgress.show();
                //FIREBASE DATABASE INSTANCE
                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Complaints").child(mAuth.getCurrentUser().getUid()).child(subject);
                mStorageRef = FirebaseStorage.getInstance().getReference();

                StorageReference mChildStorage = mStorageRef.child("Complaints").child(imageHoldUri.getLastPathSegment());

                mChildStorage.putFile(imageHoldUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        final Uri imageUrl = taskSnapshot.getDownloadUrl();

                        mUserDatabase.child("user ID").setValue(mAuth.getCurrentUser().getUid());
                        mUserDatabase.child("Subject").setValue(subject);
                        mUserDatabase.child("user Name").setValue(userName);
                        mUserDatabase.child("user Mobile No").setValue(userMobileNo);
                        mUserDatabase.child("Complaint").setValue(complaint);
                        mUserDatabase.child("Complaint pic url").setValue(imageUrl.toString());

                        mProgress.dismiss();


                        Toast.makeText(NewComplaint.this,"Complaint saved sucessfully..",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));
                    }
                });
            }else
            {
                Toast.makeText(NewComplaint.this,"Please select the Complaint pic..!!",Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(NewComplaint.this,"Please fill all the fields..!!",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //SAVE URI FROM GALLERY
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null)
        {

            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setBorderLineColor(Color.GREEN)
                    .start(this);

        }else if ( requestCode == REQUEST_CAMERA && resultCode == RESULT_OK ){
            //SAVE URI FROM CAMERA

            Uri imageUri = data.getData();


            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setBorderLineColor(Color.GREEN)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageHoldUri = result.getUri();

                imageView.setImageURI(imageHoldUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

}
