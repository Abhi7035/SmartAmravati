package com.example.ash.smartamravati.activity.user.register;

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
import com.example.ash.smartamravati.activity.user.login.Page3;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Page6 extends AppCompatActivity implements View.OnClickListener {
    public Button btn1;
    private EditText edtEmail;
    private EditText edtPassword;
    private TextView textLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);

        btn1 = (Button) findViewById(R.id.btn1);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        textLogin = (TextView) findViewById(R.id.textLogin);
        progressDialog = new ProgressDialog(this);

        btn1.setOnClickListener(this);
        textLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {

        if (view==btn1)
        {
            registerUser();


        }
        if (view==textLogin)
        {
            finish();
            Intent toy=new Intent(Page6.this,Page3.class);
            startActivity(toy);
        }

    }

    private void registerUser() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(Page6.this,"Successfully registered...",Toast.LENGTH_LONG).show();
                            Toast.makeText(Page6.this,"Proceed to profile Section...",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Page4.class));


                        }else{
                            //display some message here
                            Toast.makeText(Page6.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}
