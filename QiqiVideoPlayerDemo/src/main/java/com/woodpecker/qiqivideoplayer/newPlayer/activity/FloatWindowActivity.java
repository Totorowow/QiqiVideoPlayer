package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Special;
import com.qw.soul.permission.callbcak.SpecialPermissionListener;
import com.woodpecker.qiqivideoplayer.ConstantVideo;
import com.woodpecker.qiqivideoplayer.R;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.player.VideoViewManager;
import com.woodpecker.video.ui.pip.FloatVideoManager;
import com.woodpecker.video.ui.view.BasisVideoController;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FloatWindowActivity extends AppCompatActivity{

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
            mPIPManager.setActClass(FloatWindowActivity.class);
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

            SoulPermission.getInstance().checkAndRequestPermission(Special.SYSTEM_ALERT, new SpecialPermissionListener() {
                @Override
                public void onGranted(Special permission) {
                    mPIPManager.startFloatWindow();
                    //mPIPManager.setFloatViewVisible();
                    mPIPManager.resume();
                }

                @Override
                public void onDenied(Special permission) {
                    Toast.makeText(FloatWindowActivity.this, "获取权限失败！", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }

}
