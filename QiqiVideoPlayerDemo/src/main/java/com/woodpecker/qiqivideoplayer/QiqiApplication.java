package com.woodpecker.qiqivideoplayer;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.woodpecker.kernel.factory.PlayerFactory;
import com.woodpecker.kernel.utils.PlayerConstant;
import com.woodpecker.kernel.utils.PlayerFactoryUtils;

import com.woodpecker.music.utils.MusicSpUtils;
import com.woodpecker.video.config.VideoPlayerConfig;
import com.woodpecker.video.player.VideoViewManager;
import com.woodpecker.videosqllite.manager.CacheConfig;
import com.woodpecker.videosqllite.manager.LocationManager;


public class QiqiApplication extends Application {


    private static QiqiApplication instance;
    public static synchronized QiqiApplication getInstance() {
        if (null == instance) {
            instance = new QiqiApplication();
        }
        return instance;
    }

    public QiqiApplication(){}

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ScreenDensityUtils.setup(this);
        ScreenDensityUtils.register(this,375.0f,
                ScreenDensityUtils.MATCH_BASE_WIDTH,ScreenDensityUtils.MATCH_UNIT_DP);
        PlayerFactory player = PlayerFactoryUtils.getPlayer(PlayerConstant.PlayerType.TYPE_EXO);
        VideoViewManager.setConfig(VideoPlayerConfig.newBuilder()
                .setContext(this)
                .setBuriedPointEvent(new BuriedPointEventImpl())
                .setLogEnabled(true)
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


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


}


