package com.bai.psychedelic.cloudmusic.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bai.psychedelic.autoshell.Book;
import com.bai.psychedelic.autoshell.BookController;
import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.adapter.MusicGridAdapter;
import com.bai.psychedelic.cloudmusic.adapter.MusicListAdapter;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.view.GridSpaceItemDecoration;

import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView mRvGrid,mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    private List<Book> bookList;
    private BookController bookController;
    private boolean connected;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            bookController = BookController.Stub.asInterface(iBinder);
            connected = true;
            try {
                bookController.addBookInOut(new Book("123"));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                List<Book> bookList = bookController.getBookList();
               for (Book book:bookList){
                   Log.d("weeqqq","book name = "+book.getName());
               }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d("123","connected = "+connected);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            connected = false;
            Log.d("123","connected = "+connected);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindService();

        Intent intent  = getIntent();
        if (intent!=null){
            Uri schema = intent.getData();
            if (schema!=null){
                String result = schema.toString();
                Log.d("weeqqq","result = "+result);
            }

        }


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.bai.psychedelic.autoshell");
        intent.setAction("com.bai.psychedelic.autoshell.action");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
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
        mListAdapter = new MusicListAdapter(this,mRvList);
        mRvList.setNestedScrollingEnabled(false);
        mRvGrid.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRvGrid.setAdapter(mGridAdapter);
        mRvList.setAdapter(mListAdapter);



    }
}
