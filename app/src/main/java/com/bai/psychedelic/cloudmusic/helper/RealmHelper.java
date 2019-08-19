package com.bai.psychedelic.cloudmusic.helper;

import com.bai.psychedelic.cloudmusic.model.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelper {

    private Realm mRealm;

    public RealmHelper(){
        mRealm = Realm.getDefaultInstance();
    }

    public void saveUser(UserModel userModel){
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(userModel);
        mRealm.commitTransaction();
    }

    /**
     * 关闭数据库，释放资源
     */
    public void close(){
        if (mRealm!=null && !mRealm.isClosed()){
            mRealm.close();
        }
    }

    /**
     * 返回所有用户
     */
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    /**
     * 验证用户信息
     */
    public boolean validateUser(String phone,String password){
        boolean result = false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query = query.equalTo("phone", phone).equalTo("password", password);
        UserModel userModel = query.findFirst();
        if (userModel!=null){
           result = true;
        }
        return result;
    }

}
