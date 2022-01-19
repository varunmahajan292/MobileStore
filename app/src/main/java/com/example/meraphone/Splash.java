package com.example.meraphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView imageView;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=(ImageView)findViewById(R.id.imageView1);

        Animation animation = new AlphaAnimation(0,1); // Change alpha from fully visible to invisible
        animation.setDuration(3000); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter
        // animation
        // rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation
        // infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the
        // end so the button will
        // fade back in
        imageView.startAnimation(animation);



        // Animation an2= AnimationUtils.loadAnimation(this,R.anim.move);
        //imageView.startAnimation(an2);
        ///MediaPlayer ring= MediaPlayer.create(splash.this,R.raw.sound2);
        //ring.start();
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent=new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }

}
