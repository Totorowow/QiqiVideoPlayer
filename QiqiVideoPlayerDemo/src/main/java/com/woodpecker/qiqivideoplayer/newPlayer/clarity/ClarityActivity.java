package com.woodpecker.qiqivideoplayer.newPlayer.clarity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.woodpecker.qiqivideoplayer.ConstantVideo;
import com.woodpecker.video.config.ConstantKeys;
import com.woodpecker.video.config.VideoInfoBean;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.ui.view.BasisVideoController;
import com.woodpecker.video.ui.view.CustomBottomView;

import com.woodpecker.qiqivideoplayer.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ClarityActivity extends AppCompatActivity implements View.OnClickListener {

    private QiqiPlayer mQiqiPlayer;
    private Button mBtnScaleNormal;
    private Button mBtnScale169;
    private Button mBtnScale43;
    private Button mBtnCrop;
    private Button mBtnGif;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_video);
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
        BasisVideoController controller = new BasisVideoController(this);
        CustomBottomView bottomView = controller.getBottomView();
        if (bottomView!=null){
            controller.removeControlComponent(bottomView);
        }
        DefinitionControlView mDefinitionControlView = new DefinitionControlView(this);
        mDefinitionControlView.setOnRateSwitchListener(url -> {
            mQiqiPlayer.setUrl(url);
            mQiqiPlayer.replay(false);
        });
        List<VideoInfoBean> clarites = getClarites();
        LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        for (int i=0 ; i<clarites.size() ; i++){
            VideoInfoBean videoClarity = clarites.get(i);
            videos.put(videoClarity.getGrade(),videoClarity.getVideoUrl());
        }
        mDefinitionControlView.setData(videos);
        controller.addControlComponent(mDefinitionControlView);
        //设置视频背景图
        Glide.with(this).load(R.drawable.image_default).into(controller.getThumb());
        //设置控制器
        mQiqiPlayer.setController(controller);
        mQiqiPlayer.setUrl(clarites.get(0).getVideoUrl());
        mQiqiPlayer.start();
    }



    public List<VideoInfoBean> getClarites() {
        List<VideoInfoBean> clarities = new ArrayList<>();
        clarities.add(new VideoInfoBean("标题哈哈哈哈","标清", "270P", ConstantVideo.VideoPlayerList[1]));
        clarities.add(new VideoInfoBean("标题哈哈哈哈","高清", "480P", ConstantVideo.VideoPlayerList[1]));
        clarities.add(new VideoInfoBean("标题哈哈哈哈","超清", "720P", "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        clarities.add(new VideoInfoBean("标题哈哈哈哈","蓝光", "1080P", "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        return clarities;
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
