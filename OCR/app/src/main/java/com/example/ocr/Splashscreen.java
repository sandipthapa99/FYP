package com.example.ocr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ocr.MainActivity;

public class Splashscreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 2500;

    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView appName,developed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //Hooks
        logo = findViewById(R.id.splashLogo);
        appName = findViewById(R.id.text1);
        developed = findViewById(R.id.text2);

        logo.setAnimation(topAnim);
        appName.setAnimation(bottomAnim);
        developed.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent =new Intent(Splashscreen.this, Home_Activity.class);
                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View, String>(logo,"logo_image");
                pairs[1]=new Pair<View, String>(appName,"logo_text");

                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(Splashscreen.this,pairs);
                    startActivity(splashIntent);
                    finish();
                }

            }
        },SPLASH_SCREEN);
    }
}