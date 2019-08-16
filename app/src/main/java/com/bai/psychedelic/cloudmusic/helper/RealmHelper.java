package com.bai.psychedelic.cloudmusic.helper;

import com.bai.psychedelic.cloudmusic.model.UserModel;

import io.realm.Realm;

public class RealmHelper {

    private Realm mRealm;

    public RealmHelper(){
        mRealm = Realm.getDefaultInstance();
    }

    public void saveUser(UserModel userModel){
        mRealm.beginTransaction();
        mRealm.insert(userModel);
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
}
