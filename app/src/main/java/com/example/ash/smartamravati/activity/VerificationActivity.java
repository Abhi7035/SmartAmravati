package com.example.ash.smartamravati.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ash.smartamravati.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerificationActivity extends AppCompatActivity {

    TextView txt_email,txt_status,txt_uid;
    Button btn_send,btn_refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_uid = (TextView) findViewById(R.id.txt_uid);
        txt_status = (TextView) findViewById(R.id.txt_status);

        btn_send = (Button) findViewById(R.id.btn_send);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);

        setInfo();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_send.setEnabled(false);

                FirebaseAuth.getInstance().getCurrentUser()
                        .sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_send.setEnabled(true);

                                if (task.isSuccessful()) {
                                    btn_send.setEnabled(false);
                                    Toast.makeText(VerificationActivity.this, " Verification Email is Send to :" + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                                } else {

                                    Toast.makeText(VerificationActivity.this, "Failed to send", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().getCurrentUser()
                        .reload()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                setInfo();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user.isEmailVerified()== true){
                                    finish();
                                    Toast.makeText(VerificationActivity.this, " Verification Successful for => :" + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));
                                }else {
                                    Toast.makeText(VerificationActivity.this, "Please verify...", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }

    private void setInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        txt_email.setText(new StringBuilder("Email :").append(user.getEmail()));
        txt_uid.setText(new StringBuilder("UID :").append(user.getUid()));
        txt_status.setText(new StringBuilder("Status :").append(String.valueOf(user.isEmailVerified())));
    }
}
