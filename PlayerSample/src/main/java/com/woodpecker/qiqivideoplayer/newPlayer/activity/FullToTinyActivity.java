package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.woodpecker.qiqivideoplayer.R;
import com.woodpecker.video.config.ConstantKeys;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.ui.view.BasisVideoController;

import cn.ycbjie.ycstatusbarlib.bar.StateAppBar;


/**
 * @author yc
 */
public class FullToTinyActivity extends BaseActivity implements View.OnClickListener {

    private QiqiPlayer mQiqiPlayer;
    private Button mBtnTiny1;
    private Button mBtnTiny2;



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

    @Override
    public int getContentView() {
        return R.layout.activity_full_video1;
    }

    @Override
    public void initView() {
        StateAppBar.translucentStatusBar(this, true);
        adaptCutoutAboveAndroidP();
        mQiqiPlayer = findViewById(R.id.video_player);
        mBtnTiny1 = findViewById(R.id.btn_tiny_1);
        mBtnTiny2 = findViewById(R.id.btn_tiny_2);

        BasisVideoController controller = new BasisVideoController(this);
        Glide.with(this).load(R.drawable.badminton_screenshot).into(controller.getThumb());
        //设置控制器
        mQiqiPlayer.setController(controller);
        //mQiqiPlayer.setUrl(ConstantVideo.VideoPlayerList[0]);
        mQiqiPlayer.setUrl("android.resource://" + getPackageName() + "/" + R.raw.badminton_highlights);
        mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_ORIGINAL);
        mQiqiPlayer.start();
    }

    @Override
    public void initListener() {
        mBtnTiny1.setOnClickListener(this);
        mBtnTiny2.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tiny_1:
                mQiqiPlayer.startFullScreen();
                break;
            case R.id.btn_tiny_2:
                mQiqiPlayer.startTinyScreen(Gravity.CENTER);
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
