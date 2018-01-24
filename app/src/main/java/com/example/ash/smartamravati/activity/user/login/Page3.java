package com.example.ash.smartamravati.activity.user.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.Profile.Page4;
import com.example.ash.smartamravati.activity.user.forgotpass.Page5;
import com.example.ash.smartamravati.activity.user.register.Page6;
import com.example.ash.smartamravati.activity.user.dashboard.NavigationDrawer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Page3 extends AppCompatActivity implements View.OnClickListener{
public TextView res;
public TextView fgp;
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;



    public void init()
    {
        res=(TextView) findViewById(R.id.res);
        fgp=(TextView) findViewById(R.id.fgp);
        res.setOnClickListener(new View.OnClickListener()


        {
            @Override
            public void onClick(View v) {
                Intent toy=new Intent(Page3.this,Page6.class);
                startActivity(toy);
            }
        });
        fgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ty=new Intent(Page3.this,Page5.class);
                startActivity(ty);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), Page3.class));

        }
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        progressDialog = new ProgressDialog(this);

        //attaching click listener
        buttonSignIn.setOnClickListener(this);
        init();
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
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
                if(task.isSuccessful()){
                    //start the profile activity
                    finish();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified() ==false){


                        startActivity(new Intent(getApplicationContext(), Page4.class));
                    }

                    else{
                        startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));


                    }

                }else {
                    Toast.makeText(Page3.this,"Login failed....",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View view) {

        if(view == buttonSignIn){


            userLogin();



        }
    }
}
