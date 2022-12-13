package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.woodpecker.kernel.factory.PlayerFactory;
import com.woodpecker.kernel.utils.PlayerConstant;
import com.woodpecker.kernel.utils.PlayerFactoryUtils;
import com.woodpecker.qiqivideoplayer.BuriedPointEventImpl;

import com.woodpecker.qiqivideoplayer.ConstantVideo;
import com.woodpecker.qiqivideoplayer.util.GlideEngine;
import com.woodpecker.video.config.ConstantKeys;
import com.woodpecker.video.config.VideoPlayerConfig;
import com.woodpecker.video.player.OnVideoStateListener;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.player.VideoPlayerBuilder;
import com.woodpecker.video.player.VideoViewManager;
import com.woodpecker.video.ui.view.BasisVideoController;
import com.woodpecker.video.ui.view.CustomErrorView;

import com.woodpecker.qiqivideoplayer.R;
import com.woodpecker.video.ui.view.CustomTitleView;

import java.util.ArrayList;

public class CustomVideoActivity extends AppCompatActivity implements View.OnClickListener {

    private QiqiPlayer mQiqiPlayer;
    private Button mBtnScaleNormal;
    private Button mBtnScale169;
    private Button mBtnScale43;
    private Button mBtnScaleFull;
    private Button mBtnScaleOriginal;
    private Button mBtnScaleCrop;
    private Button mBtnCrop;
    private Button mBtnGif;
    private ImageView selectVideo;
    private ImageView closeCurrentPage;
    private BasisVideoController controller;
    private String videoPath;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_video);
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
        mBtnScaleFull = findViewById(R.id.btn_scale_full);
        mBtnScaleOriginal = findViewById(R.id.btn_scale_original);
        mBtnScaleCrop = findViewById(R.id.btn_scale_crop);
        mBtnCrop = findViewById(R.id.btn_crop);
        mBtnGif = findViewById(R.id.btn_gif);
        selectVideo=findViewById(R.id.select_video);
        closeCurrentPage=findViewById(R.id.close_current_page);
    }

    private void initVideoPlayer() {
        if (videoPath==null || videoPath.length()==0){
            videoPath = ConstantVideo.VideoPlayerList[1];
            //url ="android.resource://" + getPackageName() + "/" + R.raw.gold_flower;
        }
        controller = new BasisVideoController(this);
        CustomTitleView titleView = new CustomTitleView(this);
        //titleView.setTitle("");
        //titleView.setVisibility(View.GONE);//hide top back arrow
        //controller.addControlComponent(titleView);
        controller.removeControlComponent(titleView);//remove top native view
        mQiqiPlayer.setController(controller);
        mQiqiPlayer.setUrl(videoPath);
        mQiqiPlayer.start();
        mQiqiPlayer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mQiqiPlayer.start();
            }
        },300);


        Glide.with(this).load(R.drawable.image_default).into(controller.getThumb());
    }

    private void initListener() {
        mBtnScaleNormal.setOnClickListener(this);
        mBtnScale169.setOnClickListener(this);
        mBtnScale43.setOnClickListener(this);
        mBtnScaleFull.setOnClickListener(this);
        mBtnScaleOriginal.setOnClickListener(this);
        mBtnScaleCrop.setOnClickListener(this);
        mBtnCrop.setOnClickListener(this);
        mBtnGif.setOnClickListener(this);
        selectVideo.setOnClickListener(this);
        closeCurrentPage.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mBtnScale169){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_16_9);
        } else if (v == mBtnScaleNormal){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_DEFAULT);
        }else if (v == mBtnScale43){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_4_3);
        } else if (v == mBtnScaleFull){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_MATCH_PARENT);
        }else if (v == mBtnScaleOriginal){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_ORIGINAL);
        }else if (v == mBtnScaleCrop){
            mQiqiPlayer.setScreenScaleType(ConstantKeys.PlayerScreenScaleType.SCREEN_SCALE_CENTER_CROP);
        }else if (v==selectVideo){
            selectLocalVideo();
        }else if (v==closeCurrentPage){
            finish();
        }
        else if (v == mBtnCrop){


        } else if (v == mBtnGif){

        }
    }

    /**
     * load local video
     */

    private void selectLocalVideo(){

        PictureSelector.create(this)
                .openGallery(SelectMimeType.ofVideo())
                .setImageEngine(GlideEngine.createGlideEngine())
                .setMaxSelectNum(1)
                .setMinSelectNum(1)
                .isPreviewVideo(true)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        for (LocalMedia media : result){
                            if (mQiqiPlayer != null) {
                                mQiqiPlayer.release();
                            }
                            videoPath=media.getPath();
                            initVideoPlayer();
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }






    private void test(){
        //VideoPlayer相关
        VideoPlayerBuilder.Builder builder = VideoPlayerBuilder.newBuilder();
        VideoPlayerBuilder videoPlayerBuilder = new VideoPlayerBuilder(builder);
        //设置视频播放器的背景色
        builder.setPlayerBackgroundColor(Color.BLACK);
        //设置小屏的宽高
        int[] mTinyScreenSize = {0, 0};
        builder.setTinyScreenSize(mTinyScreenSize);
        //是否开启AudioFocus监听， 默认开启
        builder.setEnableAudioFocus(false);
        mQiqiPlayer.setVideoBuilder(videoPlayerBuilder);
        //截图
        Bitmap bitmap = mQiqiPlayer.doScreenShot();
        //移除所有播放状态监听
        mQiqiPlayer.clearOnStateChangeListeners();
        //获取当前缓冲百分比
        int bufferedPercentage = mQiqiPlayer.getBufferedPercentage();
        //获取当前播放器的状态
        int currentPlayerState = mQiqiPlayer.getCurrentPlayerState();
        //获取当前的播放状态
        int currentPlayState = mQiqiPlayer.getCurrentPlayState();
        //获取当前播放的位置
        long currentPosition = mQiqiPlayer.getCurrentPosition();
        //获取视频总时长
        long duration = mQiqiPlayer.getDuration();
        //获取倍速速度
        float speed = mQiqiPlayer.getSpeed();
        //获取缓冲速度
        long tcpSpeed = mQiqiPlayer.getTcpSpeed();
        //获取视频宽高
        int[] videoSize = mQiqiPlayer.getVideoSize();
        //是否处于静音状态
        boolean mute = mQiqiPlayer.isMute();
        //判断是否处于全屏状态
        boolean fullScreen = mQiqiPlayer.isFullScreen();
        //是否是小窗口模式
        boolean tinyScreen = mQiqiPlayer.isTinyScreen();

        //是否处于播放状态
        boolean playing = mQiqiPlayer.isPlaying();
        //暂停播放
        mQiqiPlayer.pause();
        //视频缓冲完毕，准备开始播放时回调
        mQiqiPlayer.onPrepared();
        //重新播放
        mQiqiPlayer.replay(true);
        //继续播放
        mQiqiPlayer.resume();
        //调整播放进度
        mQiqiPlayer.seekTo(100);
        //循环播放， 默认不循环播放
        mQiqiPlayer.setLooping(true);
        //设置播放速度
        mQiqiPlayer.setSpeed(1.1f);
        //设置音量 0.0f-1.0f 之间
        mQiqiPlayer.setVolume(1,1);
        //开始播放
        mQiqiPlayer.start();


        //进入全屏
        mQiqiPlayer.startFullScreen();
        //退出全屏
        mQiqiPlayer.stopFullScreen();
        //开启小屏
        mQiqiPlayer.startTinyScreen();
        //退出小屏
        mQiqiPlayer.stopTinyScreen();

        mQiqiPlayer.setOnStateChangeListener(new OnVideoStateListener() {
            /**
             * 播放模式
             * 普通模式，小窗口模式，正常模式三种其中一种
             * MODE_NORMAL              普通模式
             * MODE_FULL_SCREEN         全屏模式
             * MODE_TINY_WINDOW         小屏模式
             * @param playerState                       播放模式
             */
            @Override
            public void onPlayerStateChanged(int playerState) {
                switch (playerState) {
                    case ConstantKeys.PlayMode.MODE_NORMAL:
                        //普通模式
                        break;
                    case ConstantKeys.PlayMode.MODE_FULL_SCREEN:
                        //全屏模式
                        break;
                    case ConstantKeys.PlayMode.MODE_TINY_WINDOW:
                        //小屏模式
                        break;
                }
            }

            /**
             * 播放状态
             * -1               播放错误
             * 0                播放未开始
             * 1                播放准备中
             * 2                播放准备就绪
             * 3                正在播放
             * 4                暂停播放
             * 5                正在缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，缓冲区数据足够后恢复播放)
             * 6                暂停缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放器，继续缓冲，缓冲区数据足够后恢复暂停
             * 7                播放完成
             * 8                开始播放中止
             * @param playState                         播放状态，主要是指播放器的各种状态
             */
            @Override
            public void onPlayStateChanged(int playState) {
                switch (playState) {
                    case ConstantKeys.CurrentState.STATE_IDLE:
                        //播放未开始，初始化
                        break;
                    case ConstantKeys.CurrentState.STATE_START_ABORT:
                        //开始播放中止
                        break;
                    case ConstantKeys.CurrentState.STATE_PREPARING:
                        //播放准备中
                        break;
                    case ConstantKeys.CurrentState.STATE_PREPARED:
                        //播放准备就绪
                        break;
                    case ConstantKeys.CurrentState.STATE_ERROR:
                        //播放错误
                        break;
                    case ConstantKeys.CurrentState.STATE_BUFFERING_PLAYING:
                        //正在缓冲
                        break;
                    case ConstantKeys.CurrentState.STATE_PLAYING:
                        //正在播放
                        break;
                    case ConstantKeys.CurrentState.STATE_PAUSED:
                        //暂停播放
                        break;
                    case ConstantKeys.CurrentState.STATE_BUFFERING_PAUSED:
                        //暂停缓冲
                        break;
                    case ConstantKeys.CurrentState.STATE_COMPLETED:
                        //播放完成
                        break;
                }
            }
        });

        //设置视频背景图
        ImageView thumb = controller.getThumb();
        Glide.with(this).load(R.drawable.image_default).into(controller.getThumb());
        //设置视频标题
        controller.setTitle("视频标题");
        //添加自定义视图。每添加一个视图，都是方式层级树的最上层
        CustomErrorView customErrorView = new CustomErrorView(this);
        controller.addControlComponent(customErrorView);
        //移除控制组件
        controller.removeControlComponent(customErrorView);
        //移除所有的组件
        controller.removeAllControlComponent();
        //隐藏播放视图
        controller.hide();
        //显示播放视图
        controller.show();
        //是否开启根据屏幕方向进入/退出全屏
        controller.setEnableOrientation(true);
        //显示移动网络播放提示
        controller.showNetWarning();
        //刘海的高度
        int cutoutHeight = controller.getCutoutHeight();
        //是否有刘海屏
        boolean b = controller.hasCutout();
        //设置是否适配刘海屏
        controller.setAdaptCutout(true);
        //停止刷新进度
        controller.stopProgress();
        //开始刷新进度，注意：需在STATE_PLAYING时调用才会开始刷新进度
        controller.startProgress();
        //判断是否锁屏
        boolean locked = controller.isLocked();
        //设置是否锁屏
        controller.setLocked(true);
        //取消计时
        controller.stopFadeOut();
        //开始计时
        controller.startFadeOut();
        //设置播放视图自动隐藏超时
        controller.setDismissTimeout(8);
        //销毁
        controller.destroy();



        //播放器配置，注意：此为全局配置，按需开启
        PlayerFactory player = PlayerFactoryUtils.getPlayer(PlayerConstant.PlayerType.TYPE_IJK);
        VideoViewManager.setConfig(VideoPlayerConfig.newBuilder()
                //设置上下文
                .setContext(this)
                //设置视频全局埋点事件
                .setBuriedPointEvent(new BuriedPointEventImpl())
                //调试的时候请打开日志，方便排错
                .setLogEnabled(true)
                //设置ijk
                .setPlayerFactory(player)
                //在移动环境下调用start()后是否继续播放，默认不继续播放
                .setPlayOnMobileNetwork(false)
                //是否开启AudioFocus监听， 默认开启
                .setEnableAudioFocus(true)
                //是否适配刘海屏，默认适配
                .setAdaptCutout(true)
                //监听设备方向来切换全屏/半屏， 默认不开启
                .setEnableOrientation(false)
                //设置自定义渲染view，自定义RenderView
                //.setRenderViewFactory(null)
                //创建
                .build());

    }

}
