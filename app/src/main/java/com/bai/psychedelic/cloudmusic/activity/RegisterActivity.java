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

    }
}
