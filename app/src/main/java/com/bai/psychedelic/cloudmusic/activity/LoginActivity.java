package com.bai.psychedelic.cloudmusic.activity;

import android.content.Context;
import android.os.Bundle;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;

public class LoginActivity extends BaseActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        initView();
    }
    private void initView(){
        initNavBar(false,mContext.getString(R.string.login),false);
    }
}
