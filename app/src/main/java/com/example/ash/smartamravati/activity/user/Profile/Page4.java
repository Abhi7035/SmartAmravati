package com.example.ash.smartamravati.activity.user.Profile;

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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.text.TextUtils;
import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.verification.VerificationActivity;
import com.example.ash.smartamravati.activity.user.dashboard.NavigationDrawer;
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

public class Page4 extends AppCompatActivity {

    boolean checked = false;
    private static final int REQUEST_CAMERA = 3;
    private static final int GALLERY_REQUEST = 1;
    private static final int SELECT_FILE = 1;

    EditText tittle, name, email, mobileNo, address, pincode, DoBirth;
    Button btnSave;
    ImageView userImageProfileView;
    CheckBox checkBox;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    DatabaseReference mUserDatabse;
    StorageReference mStorageRef;

    Uri imageHoldUri = null;

    ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        userImageProfileView = (ImageView)findViewById(R.id.profileImage);
        btnSave = (Button) findViewById(R.id.btnSave);
        tittle = (EditText) findViewById(R.id.tittle);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        mobileNo = (EditText) findViewById(R.id.mobileNo);
        address = (EditText) findViewById(R.id.address);
        pincode = (EditText) findViewById(R.id.pincode);
        DoBirth = (EditText) findViewById(R.id.DoBirth);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checked = ((CheckBox) view).isChecked();
                if (checked == true){
                    Toast.makeText(Page4.this," Agreed...!! ", Toast.LENGTH_LONG).show();
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //LOGIC CHECK USER
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {

                    finish();
                    Intent moveToHome = new Intent(Page4.this, NavigationDrawer.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(moveToHome);

                }

            }
        };




        //PROGRESS DIALOG
        mProgress = new ProgressDialog(this);

        //FIREBASE DATABASE INSTANCE
        mUserDatabse = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        mStorageRef = FirebaseStorage.getInstance().getReference();



        //ONCLICK SAVE PROFILE BTN
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //LOGIC FOR SAVING USER PROFILE
                saveUserProfile();

            }
        });

        //USER IMAGEVIEW ONCLICK LISTENER
        userImageProfileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //LOGIC FOR PROFILE PICTURE
                profilePicSelection();

            }
        });

    }

    private void saveUserProfile() {

        final String usertittle, username, useremail,usermobileno, useraddress, userpincode, userdobirth;

        usertittle = tittle.getText().toString().trim();
        username = name.getText().toString().trim();
        useremail = email.getText().toString().trim();
        usermobileno = mobileNo.getText().toString();
        useraddress = address.getText().toString();
        userpincode = pincode.getText().toString();
        userdobirth = DoBirth.getText().toString();





        if( !TextUtils.isEmpty(usertittle) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(useremail) && !TextUtils.isEmpty(usermobileno) && !TextUtils.isEmpty(useraddress) && !TextUtils.isEmpty(userpincode) && !TextUtils.isEmpty(userdobirth))
        {

            if( imageHoldUri != null )
            {

                mProgress.setTitle("Saving Profile");
                mProgress.setMessage("Please wait....");
                mProgress.show();

                StorageReference mChildStorage = mStorageRef.child("User_Profile").child(imageHoldUri.getLastPathSegment());


                mChildStorage.putFile(imageHoldUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        final Uri imageUrl = taskSnapshot.getDownloadUrl();
                        mUserDatabse.child("user Tittle").setValue(usertittle);
                        mUserDatabse.child("user Name").setValue(username);
                        mUserDatabse.child("user Address").setValue(useraddress);
                        mUserDatabse.child("user Email").setValue(useremail);
                        mUserDatabse.child("user Mobile No").setValue(usermobileno);
                        mUserDatabse.child("user Pincode").setValue(userpincode);
                        mUserDatabse.child("user Date of Birth").setValue(userdobirth);
                        mUserDatabse.child("Profile pic url").setValue(imageUrl.toString());
                        mUserDatabse.child("user ID").setValue(mAuth.getCurrentUser().getUid());

                        mProgress.dismiss();
                        if (checked == true) {

                            Toast.makeText(Page4.this, "profile created sucessfully..", Toast.LENGTH_LONG).show();
                            Toast.makeText(Page4.this, "Verify Email..", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), VerificationActivity.class));
                        }else {

                            Toast.makeText(Page4.this, " Click on the CheckBox ", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }else
            {

                Toast.makeText(Page4.this, "Please select the profile pic", Toast.LENGTH_LONG).show();

            }
        }else
        {

            Toast.makeText(Page4.this, "Please fill all the fields", Toast.LENGTH_LONG).show();

        }

    }


    private void profilePicSelection() {


        //DISPLAY DIALOG TO CHOOSE CAMERA OR GALLERY

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Page4.this);
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

        //CHOOSE CAMERA

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
        intent.setAction(Intent.ACTION_GET_CONTENT);
    }

    private void galleryIntent() {

        //CHOOSE IMAGE FROM GALLERY

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_FILE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //SAVE URI FROM GALLERY
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null)
        {
           // imageHoldUri = data.getData();
         //   userImageProfileView.setImageURI(imageHoldUri);
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)

                    .setBorderLineColor(Color.RED)
                    .start(this);

        }else if ( requestCode == REQUEST_CAMERA && resultCode == RESULT_OK ){
            //SAVE URI FROM CAMERA

            Uri imageUri = data.getData();


            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(4,3)
                    .setBorderLineColor(Color.RED)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageHoldUri = result.getUri();

                userImageProfileView.setImageURI(imageHoldUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

        }


    }


