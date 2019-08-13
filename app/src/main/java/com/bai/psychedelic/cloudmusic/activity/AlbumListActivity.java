package com.bai.psychedelic.cloudmusic.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.adapter.MusicListAdapter;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;

public class AlbumListActivity extends BaseActivity {
    private RecyclerView mRvList;
    private MusicListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initView();
    }

    private void initView() {
        initNavBar(false,getString(R.string.album_list),true);
        mRvList = findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter = new MusicListAdapter(this,null);
        mRvList.setAdapter(mAdapter);

    }
}
