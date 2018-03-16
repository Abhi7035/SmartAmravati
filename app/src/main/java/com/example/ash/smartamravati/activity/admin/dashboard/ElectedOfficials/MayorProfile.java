package com.example.ash.smartamravati.activity.admin.dashboard.ElectedOfficials;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.admin.dashboard.AdminDashboard;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class MayorProfile extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 3;
    private static final int GALLERY_REQUEST = 1;
    private static final int SELECT_FILE = 1;

    EditText name, post, mobileNo, email, address, msg;
    Button btnSave;
    ImageView ImageProfileView;
    Spinner tittle;

    String Text;

    FirebaseAuth Auth;

    DatabaseReference UserDatabase;
    StorageReference StorageRef;

    Uri imageHoldUri = null;

    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayor_profile);


        ImageProfileView = (ImageView) findViewById(R.id.profileImage3);
        btnSave = (Button) findViewById(R.id.btnSave);
        tittle = (Spinner) findViewById(R.id.fromspin);
        name = (EditText) findViewById(R.id.name);
        post = (EditText) findViewById(R.id.post);
        mobileNo = (EditText) findViewById(R.id.mobileNo);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        msg = (EditText) findViewById(R.id.msg);


        Text = String.valueOf(tittle.getSelectedItem());


        Auth = FirebaseAuth.getInstance();

        //PROGRESS DIALOG
        mProgress = new ProgressDialog(this);

        //FIREBASE DATABASE INSTANCE

        UserDatabase = FirebaseDatabase.getInstance().getReference().child("Elected Officials").child("Mayor");
        StorageRef = FirebaseStorage.getInstance().getReference();

        //ONCLICK SAVE PROFILE BTN
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //LOGIC FOR SAVING USER PROFILE
                saveProfile();

            }
        });

        //USER IMAGEVIEW ONCLICK LISTENER
        ImageProfileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //LOGIC FOR PROFILE PICTURE
                profilePicSelection();

            }
        });

    }

    private void profilePicSelection() {

        //DISPLAY DIALOG TO CHOOSE CAMERA OR GALLERY

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MayorProfile.this);
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

    private void saveProfile() {

        final String mName, mPost, mEmail, mMobileNo, mAddress, mMsg;

        mName = name.getText().toString().trim();
        mPost = post.getText().toString().trim();
        mEmail = email.getText().toString().trim();
        mMobileNo = mobileNo.getText().toString();
        mAddress = address.getText().toString();
        mMsg = msg.getText().toString();


        if (!TextUtils.isEmpty(mName) && !TextUtils.isEmpty(mPost) && !TextUtils.isEmpty(mEmail) && !TextUtils.isEmpty(mMobileNo) && !TextUtils.isEmpty(mAddress) && !TextUtils.isEmpty(mMsg)) {

            if (imageHoldUri != null) {

                mProgress.setTitle("Saving Profile");
                mProgress.setMessage("Please wait....");
                mProgress.show();

                StorageReference mChildStorage = StorageRef.child("Elected_Officials_Profile").child("Mayor_Profile").child(imageHoldUri.getLastPathSegment());


                mChildStorage.putFile(imageHoldUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        final Uri imageUrl = taskSnapshot.getDownloadUrl();
                        UserDatabase.child("Tittle").setValue(Text);
                        UserDatabase.child("Name").setValue(mName);
                        UserDatabase.child("Designation").setValue(mPost);
                        UserDatabase.child("Address").setValue(mAddress);
                        UserDatabase.child("Email").setValue(mEmail);
                        UserDatabase.child("Mobile No").setValue(mMobileNo);
                        UserDatabase.child("Message").setValue(mMsg);
                        UserDatabase.child("Profile pic url").setValue(imageUrl.toString());

                        mProgress.dismiss();

                        Toast.makeText(MayorProfile.this,"Profile Saved Sucessfully..",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                    }
                });
            } else {

                Toast.makeText(MayorProfile.this, "Please select the profile pic", Toast.LENGTH_LONG).show();

            }
        } else {

            Toast.makeText(MayorProfile.this, "Please fill all the fields", Toast.LENGTH_LONG).show();

        }

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
                    .setAspectRatio(1,1)
                    .setBorderLineColor(Color.RED)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageHoldUri = result.getUri();

                ImageProfileView.setImageURI(imageHoldUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }


}
