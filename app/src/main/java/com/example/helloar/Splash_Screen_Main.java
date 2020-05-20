package com.example.helloar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Screen_Main extends AppCompatActivity {
    private static int SPLASH_TIMER= 5000;

    //variables
    ImageView logo;
    ImageView text;

    //Animation Variables
    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen__main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hooks
        logo=findViewById(R.id.iv_logo);
        text=findViewById(R.id.iv_slogan);

        //Animations
        sideAnim= AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim= AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //set Animation on elements
        logo.setAnimation(bottomAnim);
        text.setAnimation(sideAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIMER);



    }
}
