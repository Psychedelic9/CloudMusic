package com.bai.psychedelic.cloudmusic.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.util.UserUtils;
import com.bai.psychedelic.cloudmusic.view.InputView;
import com.blankj.utilcode.util.ToastUtils;

public class RegisterActivity extends BaseActivity {
    private InputView mInputPhone,mInputPassword,mInputConfirmPassword;
    public static final String TAG = "RegisterActivity";
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        initView();
    }
    private void initView()
    {
        initNavBar(true,mContext.getString(R.string.logon),false);
        mInputPhone = findViewById(R.id.input_phone);
        mInputPassword = findViewById(R.id.input_password);
        mInputConfirmPassword = findViewById(R.id.input_confirm_password);
    }

    /**
     * 注册按钮点击事件
     * @param view
     */
    public void onRegisterClick(View view) {
        /**
         * 1.验证手机号是否合法
         * 2.验证是否输入了密码，确认密码
         * 3.验证输入的手机号是否已经被注册
         * 4.保存用户输入的手机号和密码（MD5加密密码）
         */
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        String confirmPassword = mInputConfirmPassword.getInputStr();

        boolean result = UserUtils.registerUser(mContext,phone,password,confirmPassword);
        if (!result) return;
        ToastUtils.showLong(R.string.register_sucess);
        onBackPressed();

    }
}
