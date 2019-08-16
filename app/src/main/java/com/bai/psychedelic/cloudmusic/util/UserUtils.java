package com.bai.psychedelic.cloudmusic.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.activity.LoginActivity;
import com.bai.psychedelic.cloudmusic.helper.RealmHelper;
import com.bai.psychedelic.cloudmusic.model.UserModel;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

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

    public static boolean registerUser(Context context,String phone,String password,String confirmPassword){
        if (!RegexUtils.isMobileExact(phone)){
            ToastUtils.showLong(R.string.invalidPhoneNum);
            return false;
        }
        if (StringUtils.isEmpty(password)||!password.equals(confirmPassword)){
            ToastUtils.showLong(R.string.please_confirm_input_password);
            return false;
        }
        //TODO:当前手机号是否已经被注册
        if (userExistFromPhone(phone)){
            ToastUtils.showLong(R.string.this_phone_num_already_register);
            return false;
        }


        UserModel userModel = new UserModel();
        userModel.setPhone(phone);

        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        setUser(userModel);
        return true;
    }


    /**
     * 保存User信息到数据库
     */
    public static void setUser(UserModel userModel){
        RealmHelper realmHelper = new RealmHelper();
        realmHelper.saveUser(userModel);
        realmHelper.close();
    }

    /**
     * 根据手机号判断用户是否已注册
     */
    public static boolean userExistFromPhone(String phone){
        boolean result = false;
        RealmHelper realmHelper = new RealmHelper();
        List<UserModel> allUser = realmHelper.getAllUser();
        for (UserModel userModel:allUser){
            if (userModel.getPhone().equals(phone)){
                result = true;
                break;
            }
        }
        return result;

    }

}
