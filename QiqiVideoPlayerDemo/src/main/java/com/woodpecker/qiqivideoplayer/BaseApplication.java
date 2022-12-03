package com.woodpecker.qiqivideoplayer;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;


import com.woodpecker.kernel.factory.PlayerFactory;
import com.woodpecker.kernel.utils.PlayerConstant;
import com.woodpecker.kernel.utils.PlayerFactoryUtils;

import com.woodpecker.music.utils.MusicSpUtils;
import com.woodpecker.video.config.VideoPlayerConfig;
import com.woodpecker.video.player.VideoViewManager;
import com.woodpecker.videosqllite.manager.CacheConfig;
import com.woodpecker.videosqllite.manager.LocationManager;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/8/18
 * 描    述：BaseApplication
 * 修订历史：
 * ================================================
 */
public class BaseApplication extends Application {


    private static BaseApplication instance;
    public static synchronized BaseApplication getInstance() {
        if (null == instance) {
            instance = new BaseApplication();
        }
        return instance;
    }

    public BaseApplication(){}

    /**
     * 这个最先执行
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    /**
     * 程序启动的时候执行
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ScreenDensityUtils.setup(this);
        ScreenDensityUtils.register(this,375.0f,
                ScreenDensityUtils.MATCH_BASE_WIDTH,ScreenDensityUtils.MATCH_UNIT_DP);
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
                //创建SurfaceView
                //.setRenderViewFactory(SurfaceViewFactory.create())
                .build());
        MusicSpUtils.init(this);

        initVideoCache();
    }

    private void initVideoCache() {
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setIsEffective(true);
        cacheConfig.setType(2);
        cacheConfig.setContext(this);
        cacheConfig.setCacheMax(1000);
        cacheConfig.setLog(false);
        LocationManager.getInstance().init(cacheConfig);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        Log.d("Application", "onTerminate");
        super.onTerminate();
    }


    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        Log.d("Application", "onLowMemory");
        super.onLowMemory();
    }


    /**
     * HOME键退出应用程序
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        Log.d("Application", "onTrimMemory");
        super.onTrimMemory(level);
    }


    /**
     * onConfigurationChanged
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("Application", "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }


}


