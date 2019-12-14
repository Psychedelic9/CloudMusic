package com.bai.psychedelic.cloudmusic.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.helper.MediaPlayerHelper;
import com.bai.psychedelic.cloudmusic.util.BlurTransformation;
import com.squareup.picasso.Picasso;

public class PlayMusicView extends FrameLayout {
    private Context mContext;
    private View mView;
    private ImageView mIvIcon, mIvNeedle, mIvPlay;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    private FrameLayout mFlPlayMusic;
    private boolean isPlaying;
    private MediaPlayerHelper mMediaPlayerHelper;
    private String mPath;


    public PlayMusicView(Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music, this, false);
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mIvPlay = mView.findViewById(R.id.iv_play);
        mFlPlayMusic = mView.findViewById(R.id.fl_play_music);
        mIvNeedle = mView.findViewById(R.id.iv_needle);
        mMediaPlayerHelper = MediaPlayerHelper.getInstance(mContext);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                trigger();
            }
        });

        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);

        addView(mView);
    }

    private void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic(mPath);
        }
    }

    public void playMusic(String path) {
        mPath = path;
        isPlaying = true;
        mIvPlay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);
        /**
         * 1.判断当前音乐是否已经播放状态
         * 2.如果当前音乐已经在播放，直接start
         * 3.如果当前非已经播放状态，调用setPath方法
         */
        if (mMediaPlayerHelper.getPath() != null && mMediaPlayerHelper.getPath().equals(path)) {
            mMediaPlayerHelper.start();
        } else {
            mMediaPlayerHelper.setPath(path);
            mMediaPlayerHelper.setOnMediaPlayerHelperListener(new MediaPlayerHelper.OnMediaPlayerHelperListener() {
                @Override
                public void onPrepare(MediaPlayer mp) {
                    mMediaPlayerHelper.start();
                }
            });
        }
    }

    public void stopMusic() {
        isPlaying = false;
        mIvPlay.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);
        mMediaPlayerHelper.pause();
    }


    public void setMusicIcon(String icon) {
        //TODO:展示网络专辑封面图片
        Picasso.get().load(R.mipmap.img1).into(mIvIcon);

    }
}
