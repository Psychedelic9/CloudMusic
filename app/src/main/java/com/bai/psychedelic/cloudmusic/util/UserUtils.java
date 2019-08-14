package com.bai.psychedelic.cloudmusic.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        Intent intent = new Intent(context,LoginActivity.class);
        //添加intent标识符，清理task站并重启一个新的task
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //定义Activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

    public static void registerUser(Context context,String phone,String password,String confirmPassword){
        
    }

}
