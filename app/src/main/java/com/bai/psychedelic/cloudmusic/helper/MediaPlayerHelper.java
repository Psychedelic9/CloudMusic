package com.bai.psychedelic.cloudmusic.helper;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayerHelper  {
    private static MediaPlayerHelper instance;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private String mPath;

    public void setOnMediaPlayerHelperListener(OnMediaPlayerHelperListener onMediaPlayerHelperListener) {
        this.onMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    private OnMediaPlayerHelperListener onMediaPlayerHelperListener;
    public static MediaPlayerHelper getInstance(Context context){
        if (instance == null){
            synchronized (MediaPlayerHelper.class){
                if (instance == null){
                    instance = new MediaPlayerHelper(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerHelper(Context context){
        mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    /**
     * 1.setPath 当前需要播放的音乐
     * 2.start:播放音乐
     * 3.pause:暂停音乐
     */

    /**
     * 1,音乐正在播放：重置音乐播放状态
     * 2，设置播放音乐路径
     * 3，准备播放
     * @param path
     */
    public void setPath(String path){
        mPath = path;
        if (mMediaPlayer.isPlaying()){
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (onMediaPlayerHelperListener!=null){
                    onMediaPlayerHelperListener.onPrepare(mediaPlayer);
                }
            }
        });
    }

    public void start(){
        if (mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }

    public void pause(){
        mMediaPlayer.pause();
    }

    public String getPath(){
        return mPath;
    }

    public interface OnMediaPlayerHelperListener{
        void onPrepare(MediaPlayer mp);
    }
}
