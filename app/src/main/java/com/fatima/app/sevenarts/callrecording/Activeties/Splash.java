package com.fatima.app.sevenarts.callrecording.Activeties;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.fatima.app.sevenarts.callrecording.R;
//import com.fatima.app.sevenarts.callrecording.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {




    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarGradiant (Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow ();
            DisplayMetrics displayMetrics = new DisplayMetrics ();
            getWindowManager ().getDefaultDisplay ().getMetrics (displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            Drawable background = activity.getResources ().getDrawable (R.drawable.gradient_bg);
            window.addFlags (WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor (activity.getResources ().getColor (android.R.color.transparent));
            window.setNavigationBarColor (activity.getResources ().getColor (android.R.color.transparent));
            window.setBackgroundDrawable (background);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        splash();
    }


    private void splash () {
        Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            public void run () {


                Splash.this.startActivity (new Intent(Splash.this, Login_number.class));
                finish ();
            }
        }, 3000);
    }
}