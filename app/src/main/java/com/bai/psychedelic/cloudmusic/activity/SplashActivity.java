package com.bai.psychedelic.cloudmusic.activity;

import android.content.Intent;
import android.os.Bundle;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.util.MyLog;
import com.bai.psychedelic.cloudmusic.util.UserUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        final boolean isLogin = UserUtils.validateUserLogin();

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isLogin) {
                    toMain();
                } else {
                    toLogin();
                }
            }
        }, 3000);
    }

    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
