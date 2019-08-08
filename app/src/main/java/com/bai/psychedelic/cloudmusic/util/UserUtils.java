package com.bai.psychedelic.cloudmusic.util;

import android.content.Context;
import android.text.TextUtils;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.activity.LoginActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;

public class UserUtils {
    /**
     * 验证登录用户合法性
     */
    public static boolean vaildateLogin(Context context,String phoneNum,String password){
        if (!RegexUtils.isMobileExact(phoneNum)){
            ToastUtils.showLong(R.string.invaild_phone_num);
            return false;
        }
        if (!TextUtils.isEmpty(password)){
            ToastUtils.showLong(R.string.password_null);
            return false;
        }
        return true;
    }
    /**
     * 退出登录
     */
    public static void logout(Context context){
        ActivityUtils.startActivity(LoginActivity.class);
    }
}
