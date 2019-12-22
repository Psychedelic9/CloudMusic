package com.bai.psychedelic.cloudmusic.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.bai.psychedelic.cloudmusic.service.MusicService;
import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.util.BlurTransformation;
import com.bai.psychedelic.cloudmusic.util.MyLog;
import com.bai.psychedelic.cloudmusic.view.PlayMusicView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class PlayMusicActivity extends BaseActivity implements PlayMusicView.PlayMusicViewInterface {
    public static final String TAG = "PlayMusicActivity";
    private ImageView mIvBg;
    private PlayMusicView mPlayMusicView;
    private SeekBar mSeekBar;
    private volatile static MusicService.MusicBinder mBinder;
    public static final int MSG_GET_PROGRESS = 998;
    public static final int MSG_PLAY_MUSIC = 999;
    private MyHandler mHandler;
    private MyConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

        mHandler = new MyHandler(this);
        connection = new MyConnection();
        bindService();


    }

    private static class MyHandler extends Handler{

        private WeakReference<PlayMusicActivity> activity;

        public MyHandler(PlayMusicActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_GET_PROGRESS:
                    if (activity.get().mBinder!=null){
                        int currentPosition = activity.get().mBinder.getCurrentPosition();
                        activity.get().mSeekBar.setProgress(currentPosition);
                    }
                    sendEmptyMessageDelayed(MSG_GET_PROGRESS,500);
                    break;
                case MSG_PLAY_MUSIC:
                    if (activity.get().mBinder!=null){
                        activity.get().mPlayMusicView.playMusic((String) msg.obj);

                    }
                    break;
            }
        }
    }

    private void bindService() {
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    private void initView() {
        mSeekBar = findViewById(R.id.play_music_seek_bar);
        //TODO:加载专辑封面图片使用高斯模糊，Picasso
        mIvBg = findViewById(R.id.iv_bg);
        Picasso.get().load(R.mipmap.img1).transform(new BlurTransformation(this)).into(mIvBg);
        mPlayMusicView = findViewById(R.id.play_music_view);
        //TODO:传入专辑封面URL
        mPlayMusicView.setMusicIcon("");

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mBinder.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void OnBackClick(View view) {
        onBackPressed();
    }

    @Override
    public void playMusic() {
        MyLog.d(TAG,"playMusicClick binder = "+mBinder);
        if (mBinder!=null){
            mBinder.play();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }

    class MyConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyLog.d(TAG,"onServiceConnected");
            mBinder = (MusicService.MusicBinder) iBinder;
            MyLog.d(TAG,"onServiceConnected mBinder = "+mBinder);
            mBinder.prepareMusic("http://122.51.231.196:8080/moon.mp3");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!mBinder.isPrepared()){
                        try {
                            Thread.sleep(500);
                            MyLog.d(TAG,"not Prepared");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Message msg = Message.obtain();
                    msg.what = MSG_PLAY_MUSIC;
                    msg.obj = "http://122.51.231.196:8080/moon.mp3";
                    mHandler.sendMessageDelayed(msg,50);
                    mHandler.sendEmptyMessageDelayed(MSG_GET_PROGRESS,50);
                    mSeekBar.setMax(mBinder.getDuration());
                    MyLog.d(TAG,"duration = "+mBinder.getDuration());
                    setProgress(mBinder.getCurrentPosition());
                }
            }).start();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            MyLog.d(TAG,"onServiceDisconnected");

        }
    }

}
