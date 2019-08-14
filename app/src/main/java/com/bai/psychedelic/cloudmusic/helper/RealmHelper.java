package com.bai.psychedelic.cloudmusic.helper;

import io.realm.Realm;

public class RealmHelper {

    private Realm mRealm;

    public RealmHelper(){
        mRealm = Realm.getDefaultInstance();

    }
}
