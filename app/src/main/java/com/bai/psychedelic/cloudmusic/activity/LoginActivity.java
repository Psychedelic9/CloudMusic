package com.bai.psychedelic.cloudmusic.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.util.UserUtils;
import com.bai.psychedelic.cloudmusic.view.InputView;
import com.blankj.utilcode.util.ActivityUtils;

public class LoginActivity extends BaseActivity {
    private Context mContext;
    private InputView mInputPhone,mInputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        initView();
    }
    private void initView(){
        initNavBar(false,mContext.getString(R.string.logon),false);
        mInputPassword = findViewById(R.id.input_password);
        mInputPhone = findViewById(R.id.input_phone);
    }

    public void onRegisterClick(View view) {
        ActivityUtils.startActivity(RegisterActivity.class);
    }

    public void onCommitClick(View view) {
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        if (!UserUtils.vaildateLogin(mContext,phone,password)){
            return;
        }
        ActivityUtils.startActivity(MainActivity.class);
        finish();
    }
}
