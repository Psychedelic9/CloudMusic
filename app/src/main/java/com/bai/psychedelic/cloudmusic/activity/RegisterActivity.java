package com.bai.psychedelic.cloudmusic.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;

public class RegisterActivity extends BaseActivity {
    public static final String TAG = "RegisterActivity";
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        initView();
    }
    private void initView(){
        initNavBar(true,mContext.getString(R.string.logon),false);
    }
    public void onRegisterClick(View view) {

    }
}
