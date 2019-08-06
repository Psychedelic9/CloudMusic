package com.bai.psychedelic.cloudmusic.util;

import android.content.Context;
import android.text.TextUtils;

import com.bai.psychedelic.cloudmusic.R;
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
}
