package com.example.ash.smartamravati.activity.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.other.CircleTransform;

public class Page2 extends AppCompatActivity {
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        imageView=(ImageView)findViewById(R.id.imageView);

        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome_animation);
       imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(),Page0.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
