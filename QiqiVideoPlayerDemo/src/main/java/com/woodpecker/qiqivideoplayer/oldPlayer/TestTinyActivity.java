package com.woodpecker.qiqivideoplayer.oldPlayer;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.woodpecker.qiqivideoplayer.BaseActivity;
import com.woodpecker.qiqivideoplayer.ConstantVideo;

import com.woodpecker.qiqivideoplayer.R;
import com.yc.video.config.ConstantKeys;
import com.yc.video.old.controller.VideoPlayerController;
import com.yc.video.old.listener.OnVideoControlListener;
import com.yc.video.old.other.VideoPlayerManager;
import com.yc.video.old.player.OldVideoPlayer;

import cn.ycbjie.ycstatusbarlib.bar.StateAppBar;


/**
 * @author yc
 */
public class TestTinyActivity extends BaseActivity implements View.OnClickListener {


    private OldVideoPlayer videoPlayer;
    private Button mBtnTiny1;
    private Button mBtnTiny2;

    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) return;
        super.onBackPressed();
    }


    @Override
    public int getContentView() {
        return R.layout.activity_test_video3;
    }

    @Override
    public void initView() {
        StateAppBar.translucentStatusBar(this, true);
        videoPlayer = (OldVideoPlayer) findViewById(R.id.nice_video_player);
        mBtnTiny1 = (Button) findViewById(R.id.btn_tiny_1);
        mBtnTiny2 = (Button) findViewById(R.id.btn_tiny_2);


        videoPlayer.setPlayerType(ConstantKeys.VideoPlayerType.TYPE_IJK);
        videoPlayer.setUp(ConstantVideo.VideoPlayerList[0], null);
        VideoPlayerController controller = new VideoPlayerController(this);
        controller.setLoadingType(ConstantKeys.Loading.LOADING_RING);
        controller.setTitle("办公室小野开番外了，居然在办公室开澡堂！老板还点赞？");
        controller.setLength(98000);
        Glide.with(this)
                .load("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-30-43.jpg")
                .placeholder(R.drawable.image_default)
                .into(controller.imageView());
        controller.setHideTime(2000);
        controller.setTopPadding(24);
        //设置横屏播放时，tv和audio图标是否显示
        controller.setTvAndAudioVisibility(true,true);
        controller.setOnVideoControlListener(new OnVideoControlListener() {
            @Override
            public void onVideoControlClick(int type) {
                switch (type){
                    case ConstantKeys.VideoControl.TV:
                        //BaseToast.showRoundRectToast("投影tv电视");
                        break;
                    case ConstantKeys.VideoControl.HOR_AUDIO:
                        //BaseToast.showRoundRectToast("切换音频");
                        break;
                    default:
                        break;
                }
            }
        });
        videoPlayer.continueFromLastPosition(false);
        videoPlayer.setController(controller);
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
        switch (v.getId()){
            case R.id.btn_tiny_1:
                if (videoPlayer.isIdle()) {
                    Toast.makeText(this, "要点击播放后才能进入小窗口", Toast.LENGTH_SHORT).show();
                } else {
                    videoPlayer.enterTinyWindow();
                }
                break;
            case R.id.btn_tiny_2:
                if (videoPlayer.isIdle()) {
                    videoPlayer.start();
                }
                videoPlayer.enterVerticalScreenScreen();
                break;
            default:
                break;
        }
    }
}
