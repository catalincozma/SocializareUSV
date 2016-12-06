package com.example.cozma.socializareusv;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cozma.socializareusv.TabActivity.ClientTabActivity;
import com.example.cozma.socializareusv.Utils.Util;

public class SplashScreen extends AppCompatActivity {


    /**
     * Duration for wait
     */
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private Context context = SplashScreen.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        onCreateCalls();
    }


    public void onCreateCalls() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkStoredCredentials();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void checkStoredCredentials() {
        Util.openActivity(this, ClientTabActivity.class);

    }
}
