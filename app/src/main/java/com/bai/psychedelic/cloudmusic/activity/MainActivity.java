package com.bai.psychedelic.cloudmusic.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.adapter.MusicGridAdapter;
import com.bai.psychedelic.cloudmusic.adapter.MusicListAdapter;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.view.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {
    private RecyclerView mRvGrid,mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        initNavBar(false,getString(R.string.cloud_music),true);
        mRvGrid = findViewById(R.id.rv_grid);
        mRvList = findViewById(R.id.rv_list);
        mRvGrid.setLayoutManager(new GridLayoutManager(this,3));
        mRvGrid.setNestedScrollingEnabled(false);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.albumMarginSize),mRvGrid));
        mGridAdapter = new MusicGridAdapter(this);
        mListAdapter = new MusicListAdapter(this);
        mRvList.setNestedScrollingEnabled(false);
        mRvGrid.setAdapter(mGridAdapter);
        mRvList.setAdapter(mListAdapter);
    }
}
