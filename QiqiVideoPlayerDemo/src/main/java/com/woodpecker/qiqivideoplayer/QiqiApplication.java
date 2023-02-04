package com.woodpecker.qiqivideoplayer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.woodpecker.kernel.factory.PlayerFactory;
import com.woodpecker.kernel.utils.PlayerConstant;
import com.woodpecker.kernel.utils.PlayerFactoryUtils;

import com.woodpecker.music.utils.MusicSpUtils;
import com.woodpecker.qiqivideoplayer.util.ScreenDensityUtils;
import com.woodpecker.video.config.VideoPlayerConfig;
import com.woodpecker.video.player.VideoViewManager;
import com.woodpecker.videosqllite.manager.CacheConfig;
import com.woodpecker.videosqllite.manager.LocationManager;

import java.util.Locale;


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
        updateConfiguration(this);
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

    private void updateConfiguration(Context context){
        SharedPreferences sharedPreferences=getSharedPreferences("qiqi_sharedPreferences",MODE_PRIVATE);
        String language = sharedPreferences.getString("application_language", "zh-rCN");
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration.setLocale(Locale.getDefault());
        if (language != null) {
            switch (language) {
                case "en":
                    configuration.setLocale(Locale.ENGLISH);
                    break;
                case "es":
                    configuration.setLocale(new Locale("es"));
                    break;
                case "zh-rCN":
                    configuration.setLocale(Locale.SIMPLIFIED_CHINESE);
                    break;
            }
        }
        resources.updateConfiguration(configuration,displayMetrics);
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


