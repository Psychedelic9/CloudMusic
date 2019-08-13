package com.bai.psychedelic.cloudmusic.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bai.psychedelic.cloudmusic.R;
import com.bai.psychedelic.cloudmusic.base.BaseActivity;
import com.bai.psychedelic.cloudmusic.view.PlayMusicView;

public class PlayMusicActivity extends BaseActivity {
    private ImageView mIvBg;
    private PlayMusicView mPlayMusicView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView() {
        //TODO:加载专辑封面图片使用高斯模糊，Picasso
        mIvBg = findViewById(R.id.iv_bg);

        mPlayMusicView = findViewById(R.id.play_music_view);
        //TODO:传入专辑封面URL
        mPlayMusicView.setMusicIcon("");
        mPlayMusicView.playMusic();
    }

    public void OnBackClick(View view) {
        onBackPressed();
    }
}
