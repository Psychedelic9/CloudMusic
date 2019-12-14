package com.bai.psychedelic.cloudmusic.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.activity.LoginActivity;
import com.bai.psychedelic.cloudmusic.helper.RealmHelper;
import com.bai.psychedelic.cloudmusic.helper.UserHelper;
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
    public static boolean validateLogin(Context context, String phoneNum, String password){
        if (!RegexUtils.isMobileExact(phoneNum)){
            ToastUtils.showLong(R.string.invaild_phone_num);
            return false;
        }

        if (TextUtils.isEmpty(password)){
            ToastUtils.showLong(R.string.password_null);
            return false;
        }

        if (!userExistFromPhone(phoneNum)){
            ToastUtils.showLong(R.string.this_phone_num_not_sign_up);
            return false;
        }

        RealmHelper realmHelper = new RealmHelper();
        boolean result = realmHelper.validateUser(phoneNum, EncryptUtils.encryptMD5ToString(password));
        realmHelper.close();
        if (!result){
            ToastUtils.showLong(R.string.phone_or_password_wrong);
            return false;
        }
        //保存用户登录记录
        UserHelper.getInstance().saveUser(phoneNum);
        UserHelper.getInstance().setPhone(phoneNum);

        ToastUtils.showLong(R.string.login_success);
        return true;
    }
    /**
     * 退出登录
     */
    public static void logout(Context context){

        UserHelper.getInstance().removeUser();

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
        realmHelper.close();
        return result;

    }

    public static boolean validateUserLogin(){
        return UserHelper.getInstance().isLoginUser();
    }

    public static boolean changePassword(String oldPwd,String newPwd,String confirmPwd){
        if (TextUtils.isEmpty(oldPwd)){
            ToastUtils.showLong(R.string.please_input_origin_pwd);
            return false;
        }
        if (TextUtils.isEmpty(newPwd)){
            ToastUtils.showLong(R.string.please_input_new_pwd);
            return false;
        }
        if (TextUtils.isEmpty(confirmPwd)||!newPwd.equals(confirmPwd)){
            ToastUtils.showLong(R.string.please_confirm_new_pwd);
            return false;
        }
        RealmHelper realmHelper = new RealmHelper();
        UserModel userModel = realmHelper.getUser();
        if (!EncryptUtils.encryptMD5ToString(oldPwd).equals(userModel.getPassword())){
            ToastUtils.showLong(R.string.origin_pwd_wrong);
            return false;
        }
        realmHelper.changePassword(EncryptUtils.encryptMD5ToString(newPwd));
        realmHelper.close();

        ToastUtils.showLong(R.string.change_success);
        return true;

    }

}
