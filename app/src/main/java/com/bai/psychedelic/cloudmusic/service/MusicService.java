package com.bai.psychedelic.cloudmusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.bai.psychedelic.cloudmusic.util.MyLog;

import java.io.IOException;

public class MusicService extends Service {
    public static final String TAG = "MusicService";
    private IBinder binder;
    private MediaPlayer mPlayer;
    private String path;


    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    public class  MusicBinder extends Binder {
        private boolean isPrepared = false;
        public boolean isPrepared(){
            return isPrepared;
        }
        public void prepareMusic(String path){
            try {
                mPlayer.setDataSource(path);
                mPlayer.prepareAsync();
                mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        isPrepared = true;
                        MyLog.d(TAG,"准备完成");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            MyLog.d(TAG,"prepareMusic path = "+path);
        }

        public boolean isPlaying(){
            return mPlayer.isPlaying();
        }

        public void play(){
            MyLog.d(TAG,"playclick");
            if (isPlaying()){
                mPlayer.pause();
                MyLog.d(TAG,"pause");
            }else {
                mPlayer.start();
                MyLog.d(TAG,"start");

            }
        }

        public int getDuration(){
            if (isPrepared){
                return mPlayer.getDuration();
            }else {
                return 0;
            }
        }

        public int getCurrentPosition(){
            if (isPrepared){
                return mPlayer.getCurrentPosition();
            }else {
                return 0;
            }
        }

        public void seekTo(int mesc){
            mPlayer.seekTo(mesc);
        }

    }
}
