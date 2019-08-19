package com.bai.psychedelic.cloudmusic.activity;

import android.os.Bundle;
import android.view.View;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.view.InputView;

public class ChangePasswordActivity extends BaseActivity {
    private InputView mOldPassword,mPassword,mConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        initNavBar(true,getString(R.string.change_pwd),false);
        mOldPassword = findViewById(R.id.input_old_password);
        mPassword = findViewById(R.id.input_new_password);
        mConfirmPassword = findViewById(R.id.input_confirm_password);
    }

    public void onChangePassword(View view) {
        String oldPwd = mOldPassword.getInputStr();
        String pwd = mPassword.getInputStr();
        String confirmPwd = mConfirmPassword.getInputStr();
    }
}
