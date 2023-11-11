package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.woodpecker.qiqivideoplayer.R;
import com.woodpecker.qiqivideoplayer.databinding.ActivityFullVideo1Binding;
import com.woodpecker.video.config.ConstantKeys;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.ui.view.BasisVideoController;

import androidx.databinding.DataBindingUtil;
import cn.ycbjie.ycstatusbarlib.bar.StateAppBar;

public class FullToTinyActivity extends BaseActivity implements View.OnClickListener {
    
    private Boolean isTinyScreen=false;
    private ActivityFullVideo1Binding fullVideoBinding;

    @Override
    protected void onResume() {
        super.onResume();
        fullVideoBinding.videoPlayer.resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        fullVideoBinding.videoPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fullVideoBinding.videoPlayer.release();
    }

    @Override
    public void onBackPressed() {
        if (!fullVideoBinding.videoPlayer.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_full_video1;
    }

    @Override
    public void initView() {
        StateAppBar.translucentStatusBar(this, true);
        adaptCutoutAboveAndroidP();
        fullVideoBinding= ActivityFullVideo1Binding.inflate(getLayoutInflater());
        setContentView(fullVideoBinding.getRoot());
        BasisVideoController controller = new BasisVideoController(this);
        Glide.with(this).load(R.drawable.badminton_screenshot).into(controller.getThumb());
        fullVideoBinding.videoPlayer.setController(controller);
        //fullVideoBinding.videoPlayer.setUrl(ConstantVideo.VideoPlayerList[0]);
        fullVideoBinding.videoPlayer.setUrl("android.resource://" + getPackageName() + "/" + R.raw.badminton_highlights);
        fullVideoBinding.videoPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_ORIGINAL);
        fullVideoBinding.videoPlayer.start();
    }

    @Override
    public void initListener() {
        fullVideoBinding.btnTiny1.setOnClickListener(this);
        fullVideoBinding.btnTiny2.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tiny_1:
                if (isTinyScreen){
                    fullVideoBinding.videoPlayer.stopTinyScreen();
                }
                isTinyScreen=false;
                fullVideoBinding.videoPlayer.startFullScreen();
                break;
            case R.id.btn_tiny_2:
                isTinyScreen=true;
                fullVideoBinding.videoPlayer.startTinyScreen(Gravity.CENTER);
                break;
            default:
                break;
        }
    }

    private void adaptCutoutAboveAndroidP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }
    }


}
