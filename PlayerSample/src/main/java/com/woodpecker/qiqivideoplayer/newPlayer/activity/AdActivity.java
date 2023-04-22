package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.woodpecker.qiqivideoplayer.newPlayer.view.AdControlView;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.videocache.HttpProxyCacheServer;

import com.woodpecker.qiqivideoplayer.ConstantVideo;
import com.woodpecker.videocache.cache.ProxyVideoCacheManager;

import com.woodpecker.video.config.ConstantKeys;
import com.woodpecker.video.player.SimpleStateListener;
import com.woodpecker.video.tool.BaseToast;
import com.woodpecker.video.ui.view.BasisVideoController;

import com.woodpecker.qiqivideoplayer.R;

public class AdActivity extends AppCompatActivity implements View.OnClickListener {

    private QiqiPlayer mQiqiPlayer;
    private Button mBtnScaleNormal;
    private Button mBtnScale169;
    private Button mBtnScale43;
    private Button mBtnCrop;
    private Button mBtnGif;
    private static final String URL_AD = "https://gslb.miaopai.com/stream/IR3oMYDhrON5huCmf7sHCfnU5YKEkgO2.mp4";
    BasisVideoController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_video);
        initFindViewById();
        initVideoPlayer();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mQiqiPlayer != null) {
            mQiqiPlayer.resume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mQiqiPlayer != null) {
            mQiqiPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mQiqiPlayer != null) {
            mQiqiPlayer.release();
        }
    }

    @Override
    public void onBackPressed() {
        if (mQiqiPlayer == null || !mQiqiPlayer.onBackPressed()) {
            super.onBackPressed();
        }
    }

    private void initFindViewById() {
        mQiqiPlayer = findViewById(R.id.video_player);
        mBtnScaleNormal = findViewById(R.id.btn_scale_normal);
        mBtnScale169 = findViewById(R.id.btn_scale_169);
        mBtnScale43 = findViewById(R.id.btn_scale_43);
        mBtnCrop = findViewById(R.id.btn_crop);
        mBtnGif = findViewById(R.id.btn_gif);
    }

    private void initVideoPlayer() {
        controller = new BasisVideoController(this);
        AdControlView adControlView = new AdControlView(this);
        adControlView.setListener(new AdControlView.AdControlListener() {
            @Override
            public void onAdClick() {
                BaseToast.showRoundRectToast( "广告点击跳转");
            }

            @Override
            public void onSkipAd() {
                playVideo();
            }
        });
        controller.addControlComponent(adControlView);
        //设置视频背景图
        Glide.with(this).load(R.drawable.image_default).into(controller.getThumb());
        //设置控制器
        mQiqiPlayer.setController(controller);
        HttpProxyCacheServer cacheServer = ProxyVideoCacheManager.getProxy(this);
        String proxyUrl = cacheServer.getProxyUrl(URL_AD);
        HttpProxyCacheServer server = new HttpProxyCacheServer(this);
        String proxyVideoUrl = server.getProxyUrl(URL_AD);


        mQiqiPlayer.setUrl(proxyUrl);
        mQiqiPlayer.start();
        //监听播放结束
        mQiqiPlayer.addOnStateChangeListener(new SimpleStateListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                if (playState == ConstantKeys.CurrentState.STATE_BUFFERING_PLAYING) {
                    playVideo();
                }
            }
        });
    }


    /**
     * 播放正片
     */
    private void playVideo() {
        mQiqiPlayer.release();
        controller.removeAllControlComponent();
        controller.addDefaultControlComponent("正片");
        //重新设置数据
        mQiqiPlayer.setUrl(ConstantVideo.VideoPlayerList[0]);
        //开始播放
        mQiqiPlayer.start();
    }

    private void initListener() {
        mBtnScaleNormal.setOnClickListener(this);
        mBtnScale169.setOnClickListener(this);
        mBtnScale43.setOnClickListener(this);
        mBtnCrop.setOnClickListener(this);
        mBtnGif.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mBtnScale169){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_16_9);
        } else if (v == mBtnScaleNormal){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_DEFAULT);
        }else if (v == mBtnScale43){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_4_3);
        } else if (v == mBtnCrop){

        } else if (v == mBtnGif){

        }
    }
}
