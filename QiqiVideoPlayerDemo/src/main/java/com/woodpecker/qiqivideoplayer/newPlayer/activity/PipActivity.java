package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import com.bumptech.glide.Glide;

import com.luck.picture.lib.utils.ToastUtils;
import com.woodpecker.qiqivideoplayer.ConstantVideo;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.player.VideoViewManager;
import com.woodpecker.video.ui.pip.FloatVideoManager;
import com.woodpecker.video.ui.view.BasisVideoController;

import com.woodpecker.qiqivideoplayer.R;
import com.woodpecker.videoview.PermissionActivity;

public class PipActivity extends AppCompatActivity{

    private FloatVideoManager mPIPManager;
    private FrameLayout mPlayerContainer;
    private Button mBtnFloat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip_video);
        initFindViewById();
        initVideoPlayer();
        initListener();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mPIPManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPIPManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPIPManager.reset();
    }


    @Override
    public void onBackPressed() {
        if (mPIPManager.onBackPress()) return;
        super.onBackPressed();
    }


    private void initFindViewById() {
        mPlayerContainer = findViewById(R.id.player_container);
        mBtnFloat = findViewById(R.id.btn_float);
    }

    private void initVideoPlayer() {
        mPIPManager = FloatVideoManager.getInstance(this);
        QiqiPlayer videoView = VideoViewManager.instance().get(FloatVideoManager.PIP);
        BasisVideoController mController = new BasisVideoController(this);
        videoView.setController(mController);
        if (mPIPManager.isStartFloatWindow()) {
            mPIPManager.stopFloatWindow();
            mController.setPlayerState(videoView.getCurrentPlayerState());
            mController.setPlayState(videoView.getCurrentPlayState());
        } else {
            mPIPManager.setActClass(PipActivity.class);
            ImageView thumb = mController.getThumb();
            Glide.with(this)
                    .load(R.drawable.image_default)
                    .placeholder(android.R.color.darker_gray)
                    .into(thumb);
            videoView.setUrl(ConstantVideo.VideoPlayerList[0]);
        }
        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent != null) {
            parent.removeView(videoView);
        }

        mPlayerContainer.addView(videoView);



    }

    private void initListener() {

        mBtnFloat.setOnClickListener(v -> {

                mPIPManager.startFloatWindow();
                //mPIPManager.setFloatViewVisible();

                //mController.setPlayState(ConstantKeys.CurrentState.STATE_IDLE);
                mPIPManager.resume();





        });
    }


}
