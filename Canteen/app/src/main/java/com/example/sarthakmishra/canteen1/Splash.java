package com.example.sarthakmishra.canteen1;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
private TextView tv;
private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
      //  tv=(TextView)findViewById(R.id.txtsplash);
        iv=(ImageView)findViewById(R.id.imgsplash);

        Animation myanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
     // tv.startAnimation(myanim);
      iv.startAnimation(myanim);

     final String user=  PreferenceManager.getDefaultSharedPreferences(Splash.this).getString("userwent", "false");

        Thread timer = new Thread(){
            public void run()
            {

                try{
                   if(user.equals("false"))
                   {
                       PreferenceManager.getDefaultSharedPreferences(Splash.this).edit().putString("userwent", "true").apply();
                       Intent i= new Intent(Splash.this,Homepage.class);
                       sleep(4000);
                       startActivity(i);
                   }

                  else
                   {
                       Intent i= new Intent(Splash.this,Homepage.class);
                       sleep(4000);
                       startActivity(i);
                   }

                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}