package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.woodpecker.qiqivideoplayer.ConstantVideo;

import com.woodpecker.qiqivideoplayer.newPlayer.view.MyDanmakuView;
import com.woodpecker.video.config.ConstantKeys;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.player.SimpleStateListener;
import com.woodpecker.video.ui.view.BasisVideoController;

import com.woodpecker.qiqivideoplayer.R;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import cn.ycbjie.ycstatusbarlib.bar.StateAppBar;

public class BulletScreenActivity extends BaseActivity implements View.OnClickListener {

    private QiqiPlayer mQiqiPlayer;
    private LinearLayout mLayout;
    private Button mBtnShow;
    private Button mBtnHide;
    private Button mBtnAddDan;
    private Button mBtnAddCustomDan;

    private MyDanmakuView mMyDanmakuView;

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
        if (mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
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
        return R.layout.activity_bullet_screen_player;
    }

    @Override
    public void initView() {
        StateAppBar.translucentStatusBar(this, true);
        initFindViewById();

        BasisVideoController controller = new BasisVideoController(this);
        mMyDanmakuView = new MyDanmakuView(this);
        controller.addControlComponent(mMyDanmakuView);
        //设置视频背景图
        Glide.with(this).load(R.drawable.image_default).into(controller.getThumb());
        //设置控制器
        mQiqiPlayer.setController(controller);
        mQiqiPlayer.setUrl(ConstantVideo.VideoPlayerList[0]);
        mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_ORIGINAL);
        mQiqiPlayer.start();
        mQiqiPlayer.addOnStateChangeListener(new SimpleStateListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                if (playState == ConstantKeys.CurrentState.STATE_PREPARED) {
                    simulateBulletScreen();
                } else if (playState == ConstantKeys.CurrentState.STATE_BUFFERING_PLAYING) {
                    mHandler.removeCallbacksAndMessages(null);
                }
            }
        });
    }

    private void initFindViewById() {
        mQiqiPlayer = findViewById(R.id.video_player);
        mLayout = findViewById(R.id.layout);
        mBtnShow = findViewById(R.id.btn_show);
        mBtnHide = findViewById(R.id.btn_hide);
        mBtnAddDan = findViewById(R.id.btn_add_dan);
        mBtnAddCustomDan = findViewById(R.id.btn_add_custom_dan);

    }

    @Override
    public void initListener() {
        mBtnShow.setOnClickListener(this);
        mBtnHide.setOnClickListener(this);
        mBtnAddDan.setOnClickListener(this);
        mBtnAddCustomDan.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                mMyDanmakuView.show();
                break;
            case R.id.btn_hide:
                mMyDanmakuView.hide();
                break;
            case R.id.btn_add_dan:
                mMyDanmakuView.addDanmakuWithDrawable();
                break;
            case R.id.btn_add_custom_dan:
                setCustomizeText();
                break;
            default:
                break;
        }
    }

    private Handler mHandler = new Handler();


    private void simulateBulletScreen() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mMyDanmakuView.addDanmaku("Come on", false);
                mHandler.postDelayed(this, 300);
            }
        });
    }

    private void setCustomizeText(){
        new LovelyTextInputDialog(this, R.style.EditTextTintTheme)
                .setTopColorRes(R.color.lightBlue)
                .setTitle("自定义弹幕内容")
                .setHint("小桥流水人间")
                .setInputFilter("您输入的内容不正确", text -> text.matches("\\w+"))
                .setConfirmButton(android.R.string.ok, text -> mMyDanmakuView.addDanmaku(text, true))
                .show();

    }


}
