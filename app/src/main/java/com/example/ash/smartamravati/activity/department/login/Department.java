package com.example.ash.smartamravati.activity.department.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.department.dashboard.DepartMenu;
import com.example.ash.smartamravati.activity.department.profile.DepartProfile;
import com.example.ash.smartamravati.activity.department.verification.DepartVerification;
import com.example.ash.smartamravati.activity.user.login.Page3;
import com.example.ash.smartamravati.activity.user.verification.VerificationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Department extends AppCompatActivity {
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextCode;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private  String Checkcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);


        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), Page3.class));

        }
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCode =  findViewById(R.id.editTextCode);

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        progressDialog = new ProgressDialog(this);

        //attaching click listener
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        String code  = editTextCode.getText().toString().trim();

       //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(code)){
            Toast.makeText(this,"Please enter code",Toast.LENGTH_LONG).show();
            return;
        }



            //if the email and password are not empty
            //displaying a progress dialog

            progressDialog.setMessage("Signing In Please Wait...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    progressDialog.dismiss();
                    //if the task is successful
                    if (task.isSuccessful()) {
                        //start the profile activity
                        finish();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user.isEmailVerified() == true) {


                            startActivity(new Intent(getApplicationContext(), DepartMenu.class));
                        } else {
                            startActivity(new Intent(getApplicationContext(), DepartProfile.class));


                        }

                    } else {
                        Toast.makeText(Department.this, "Login failed....", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


}
