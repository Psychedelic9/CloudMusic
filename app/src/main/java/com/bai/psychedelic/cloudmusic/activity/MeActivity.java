package com.bai.psychedelic.cloudmusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.helper.UserHelper;
import com.bai.psychedelic.cloudmusic.util.UserUtils;
import com.blankj.utilcode.util.ActivityUtils;

public class MeActivity extends BaseActivity {
    private TextView mTvUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    private void initView() {
        initNavBar(true,getString(R.string.user_center),false);
        mTvUserId = findViewById(R.id.tv_user_id);
        mTvUserId.setText(String.format("%s%s", getString(R.string.user_id), UserHelper.getInstance().getPhone()));
    }

    public void OnChangePasswordClick(View view) {
        ActivityUtils.startActivity(ChangePasswordActivity.class);
    }

    public void onLogoutClick(View view) {
        UserUtils.logout(this);
    }
}
