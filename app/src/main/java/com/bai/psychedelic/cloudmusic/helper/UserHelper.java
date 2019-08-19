package com.bai.psychedelic.cloudmusic.helper;

import android.text.TextUtils;

import com.bai.psychedelic.cloudmusic.constants.SPConstant;
import com.blankj.utilcode.util.SPUtils;

public class UserHelper {
    private static UserHelper instance = null;

    public static void setInstance(UserHelper instance) {
        UserHelper.instance = instance;
    }


    private String phone;

    private UserHelper() {

    }

    public static UserHelper getInstance() {
        if (instance != null) {
            synchronized (UserHelper.class) {
                if (instance != null) {
                    instance = new UserHelper();
                }
            }
        }
        return instance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void saveUser(String phone){
        SPUtils.getInstance().put(SPConstant.SP_KEY_PHONE,phone);
    }

    public boolean isLoginUser(){
        boolean result = false;
        String string = SPUtils.getInstance().getString(SPConstant.SP_KEY_PHONE, "");
        if (!TextUtils.isEmpty(string)){
            result = true;
            UserHelper.getInstance().setPhone(string);
        }
        return result;
    }
    public void removeUser(){
        SPUtils.getInstance().remove(SPConstant.SP_KEY_PHONE);
    }
}
