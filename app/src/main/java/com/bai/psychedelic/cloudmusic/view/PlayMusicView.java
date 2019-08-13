package com.bai.psychedelic.cloudmusic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bai.psychedelic.cloudmusic.R;

public class PlayMusicView extends FrameLayout {
    private Context mContext;
    private View mView;
    private ImageView mIvIcon,mIvNeedle,mIvPlay;
    private Animation mPlayMusicAnim,mPlayNeedleAnim,mStopNeedleAnim;
    private FrameLayout mFlPlayMusic;
    private boolean isPlaying;

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

    private void init (Context context){
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music,this,false);
        mIvIcon = findViewById(R.id.iv_icon);
        mIvPlay = findViewById(R.id.iv_play);
        mFlPlayMusic = findViewById(R.id.fl_play_music);
        mIvNeedle = findViewById(R.id.iv_needle);

        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                trigger();
            }
        });

        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext,R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext,R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext,R.anim.stop_needle_anim);

        addView(mView);
    }

    private void trigger(){
        if (isPlaying){
            stopMusic();
        }else {
            playMusic();
        }
    }

    public void playMusic(){
        isPlaying = true;
        mIvPlay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);
    }

    public void stopMusic(){
        isPlaying = false;
        mIvPlay.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);

    }


    public void setMusicIcon(String icon){
        //TODO:展示网络专辑封面图片
    }
}
