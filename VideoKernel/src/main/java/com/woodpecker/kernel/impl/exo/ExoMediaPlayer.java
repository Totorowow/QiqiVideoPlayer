package com.woodpecker.kernel.impl.exo;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;


import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManagerProvider;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;
import com.woodpecker.kernel.inter.AbstractVideoPlayer;
import com.woodpecker.kernel.inter.VideoPlayerListener;
import com.woodpecker.kernel.utils.PlayerConstant;
import com.woodpecker.kernel.utils.VideoLogUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.google.android.exoplayer2.ExoPlaybackException.TYPE_SOURCE;

/**
 * <pre>
 *     @author yangchong
 *     blog  : https://github.com/yangchong211
 *     time  : 2018/11/9
 *     desc  :some content was delected by Totorowow,such as class name
 *     revise:
 * </pre>
 */
public class ExoMediaPlayer extends AbstractVideoPlayer implements Player.Listener {

    protected Context mAppContext;
    protected @Nullable
    ExoPlayer exoPlayer;
    protected MediaSource mMediaSource;
    protected ExoMediaSourceHelper mMediaSourceHelper;
    private PlaybackParameters mSpeedPlaybackParameters;
    private int mLastReportedPlaybackState = Player.STATE_IDLE;
    private boolean mLastReportedPlayWhenReady = false;
    private boolean mIsPreparing;
    private boolean mIsBuffering;

    private LoadControl mLoadControl;
    private RenderersFactory mRenderersFactory;
    private TrackSelector mTrackSelector;

    public ExoMediaPlayer(Context context) {
        if (context instanceof Application){
            mAppContext = context;
        } else {
            mAppContext = context.getApplicationContext();
        }
        mMediaSourceHelper = ExoMediaSourceHelper.getInstance(context);
    }

    @Override
    public void initPlayer() {
        //创建exo播放器

        mRenderersFactory = mRenderersFactory == null ?  new DefaultRenderersFactory(mAppContext) : mRenderersFactory;
        mTrackSelector = mTrackSelector == null ?  new DefaultTrackSelector(mAppContext) : mTrackSelector;
        mLoadControl = mLoadControl == null ?  new DefaultLoadControl() : mLoadControl;
        exoPlayer = new ExoPlayer.Builder(mAppContext, mRenderersFactory)
                .setTrackSelector(mTrackSelector)
                .setLoadControl(mLoadControl)
                .setBandwidthMeter(DefaultBandwidthMeter.getSingletonInstance(mAppContext))
                .setLooper(Util.getCurrentOrMainLooper())
                //.setAnalyticsCollector(new AnalyticsCollector(Clock.DEFAULT))
                .build();

        exoPlayer = new ExoPlayer.Builder(mAppContext).build();

        setOptions();

        //播放器日志
        if (VideoLogUtils.isIsLog() && mTrackSelector instanceof MappingTrackSelector) {
            exoPlayer.addAnalyticsListener(new EventLogger((MappingTrackSelector) mTrackSelector, "ExoPlayer"));
        }
        initListener();
    }

    /**
     * exo视频播放器监听listener
     */
    private void initListener() {
        exoPlayer.addListener(this);
        //exoPlayer.addVideoListener(this);
    }

    public void setTrackSelector(TrackSelector trackSelector) {
        mTrackSelector = trackSelector;
    }

    public void setRenderersFactory(RenderersFactory renderersFactory) {
        mRenderersFactory = renderersFactory;
    }



    public void setLoadControl(LoadControl loadControl) {
        mLoadControl = loadControl;
    }

    /**
     * 设置播放地址
     *
     * @param path    播放地址
     * @param headers 播放地址请求头
     */
    @Override
    public void setDataSource(String path, Map<String, String> headers) {
        // 设置dataSource
        if(path==null || path.length()==0){
            if (mPlayerEventListener!=null){
                mPlayerEventListener.onInfo(PlayerConstant.MEDIA_INFO_URL_NULL, 0);
            }
            return;
        }
        mMediaSource = mMediaSourceHelper.getMediaSource(path, headers);
    }

    @Override
    public void setDataSource(AssetFileDescriptor fd) {
        //no support
    }

    /**
     * 准备开始播放（异步）
     */
    @Override
    public void prepareAsync() {
        if (exoPlayer == null){
            return;
        }
        if (mMediaSource == null){
            return;
        }
        if (mSpeedPlaybackParameters != null) {
            exoPlayer.setPlaybackParameters(mSpeedPlaybackParameters);
        }
        mIsPreparing = true;
        mMediaSource.addEventListener(new Handler(), mMediaSourceEventListener);
        //准备播放
        exoPlayer.setMediaSource(mMediaSource);
        exoPlayer.prepare();
    }

    /**
     * 播放
     */
    @Override
    public void start() {
        if (exoPlayer == null){
            return;
        }
        exoPlayer.setPlayWhenReady(true);
    }

    /**
     * 暂停
     */
    @Override
    public void pause() {
        if (exoPlayer == null){
            return;
        }
        exoPlayer.setPlayWhenReady(false);
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        if (exoPlayer == null){
            return;
        }
        exoPlayer.stop();
    }


    private MediaSourceEventListener mMediaSourceEventListener = new MediaSourceEventListener() {
        @Override
        public void onLoadStarted(int windowIndex, @Nullable @org.jetbrains.annotations.Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (mPlayerEventListener != null && mIsPreparing) {
                mPlayerEventListener.onPrepared();
            }
        }
    };


    /**
     * 重置播放器
     */
    @Override
    public void reset() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.clearMediaItems();
            exoPlayer.setVideoSurface(null);
            mIsPreparing = false;
            mIsBuffering = false;
            mLastReportedPlaybackState = Player.STATE_IDLE;
            mLastReportedPlayWhenReady = false;
        }
    }

    /**
     * 是否正在播放
     */
    @Override
    public boolean isPlaying() {
        if (exoPlayer == null){
            return false;
        }
        int state = exoPlayer.getPlaybackState();
        switch (state) {
            case Player.STATE_BUFFERING:
            case Player.STATE_READY:
                return exoPlayer.getPlayWhenReady();
            case Player.STATE_IDLE:
            case Player.STATE_ENDED:
            default:
                return false;
        }
    }

    /**
     * 调整进度
     */
    @Override
    public void seekTo(long time) {
        if (exoPlayer == null){
            return;
        }
        exoPlayer.seekTo(time);
    }

    /**
     * 释放播放器
     */
    @Override
    public void release() {
        if (exoPlayer != null) {
            exoPlayer.removeListener(this);
            exoPlayer.release();
            exoPlayer = null;

        }

        mIsPreparing = false;
        mIsBuffering = false;
        mLastReportedPlaybackState = Player.STATE_IDLE;
        mLastReportedPlayWhenReady = false;
        mSpeedPlaybackParameters = null;
    }

    /**
     * 获取当前播放的位置
     */
    @Override
    public long getCurrentPosition() {
        if (exoPlayer == null){
            return 0;
        }
        return exoPlayer.getCurrentPosition();
    }

    /**
     * 获取视频总时长
     */
    @Override
    public long getDuration() {
        if (exoPlayer == null){
            return 0;
        }
        return exoPlayer.getDuration();
    }

    /**
     * 获取缓冲百分比
     */
    @Override
    public int getBufferedPercentage() {
        return exoPlayer == null ? 0 : exoPlayer.getBufferedPercentage();
    }

    /**
     * 设置渲染视频的View,主要用于SurfaceView
     */
    @Override
    public void setSurface(Surface surface) {
        if (surface!=null){
            try {
                if (exoPlayer != null) {
                    exoPlayer.setVideoSurface(surface);
                }
            } catch (Exception e) {
                mPlayerEventListener.onError(PlayerConstant.ErrorType.TYPE_UNEXPECTED,e.getMessage());
            }
        }
    }

    @Override
    public void setDisplay(SurfaceHolder holder) {
        if (holder == null){
            setSurface(null);
        } else{
            setSurface(holder.getSurface());
        }
    }

    /**
     * 设置音量
     */
    @Override
    public void setVolume(float leftVolume, float rightVolume) {
        if (exoPlayer != null){
            exoPlayer.setVolume((leftVolume + rightVolume) / 2);
        }
    }

    /**
     * 设置是否循环播放
     */
    @Override
    public void setLooping(boolean isLooping) {
        if (exoPlayer != null){
            exoPlayer.setRepeatMode(isLooping ? Player.REPEAT_MODE_ALL : Player.REPEAT_MODE_OFF);
        }
    }

    @Override
    public void setOptions() {
        //准备好就开始播放
        exoPlayer.setPlayWhenReady(true);
    }

    /**
     * 设置播放速度
     */
    @Override
    public void setSpeed(float speed) {
        PlaybackParameters playbackParameters = new PlaybackParameters(speed);
        mSpeedPlaybackParameters = playbackParameters;
        if (exoPlayer != null) {
            exoPlayer.setPlaybackParameters(playbackParameters);
        }
    }

    /**
     * 获取播放速度
     */
    @Override
    public float getSpeed() {
        if (mSpeedPlaybackParameters != null) {
            return mSpeedPlaybackParameters.speed;
        }
        return 1f;
    }

    /**
     * 获取当前缓冲的网速
     */
    @Override
    public long getTcpSpeed() {
        // no support
        return 0;
    }


    @Override
    public void onPlaybackStateChanged(@Player.State int state) {
        playerStateChanged();
    }

    @Override
    public void onPlayWhenReadyChanged(
            boolean playWhenReady, @Player.PlayWhenReadyChangeReason int reason
    ) {
        playerStateChanged();
    }

    private void playerStateChanged() {
        if (mPlayerEventListener == null || exoPlayer == null) {
            return;
        }
        final int state = exoPlayer.getPlaybackState();
        switch (state) {
            case Player.STATE_IDLE:
                break;
            //开始缓充
            case Player.STATE_BUFFERING:
                mPlayerEventListener.onInfo(PlayerConstant.MEDIA_INFO_BUFFERING_START, getBufferedPercentage());
                mIsBuffering = true;
                break;
            //开始播放
            case Player.STATE_READY:
                if (mIsBuffering) {
                    mPlayerEventListener.onInfo(PlayerConstant.MEDIA_INFO_BUFFERING_END, getBufferedPercentage());
                    mIsBuffering = false;
                }
                break;
            //播放器已经播放完了媒体
            case Player.STATE_ENDED:
                mPlayerEventListener.onCompletion();
                break;
            default:
                break;

        }
    }

    @Override
    public void onPlayerError(@NonNull PlaybackException error) {
        if (mPlayerEventListener != null) {
            if (error instanceof ExoPlaybackException) {
                ExoPlaybackException e = (ExoPlaybackException) error;
                if (e.type == TYPE_SOURCE) {
                    //错误的链接
                    mPlayerEventListener.onError(PlayerConstant.ErrorType.TYPE_SOURCE, error.getMessage());
                } else if (e.type == ExoPlaybackException.TYPE_RENDERER
                        || e.type == ExoPlaybackException.TYPE_UNEXPECTED
                        || e.type == ExoPlaybackException.TYPE_REMOTE) {
                    mPlayerEventListener.onError(PlayerConstant.ErrorType.TYPE_UNEXPECTED, error.getMessage());
                }
            }else{
                // TODO: impl!!
            }
        }
    }



    @Override
    public void onVideoSizeChanged(@NotNull VideoSize videoSize) {
        if (mPlayerEventListener != null) {
            mPlayerEventListener.onVideoSizeChanged(videoSize.width,videoSize.height);
            //if (unappliedRotationDegrees > 0) {
              //  mPlayerEventListener.onInfo(PlayerConstant.MEDIA_INFO_VIDEO_ROTATION_CHANGED, unappliedRotationDegrees);
            //}
        }
    }

    @Override
    public void onRenderedFirstFrame() {
        if (mPlayerEventListener != null && mIsPreparing) {
            mPlayerEventListener.onInfo(PlayerConstant.MEDIA_INFO_VIDEO_RENDERING_START, 0);
            mIsPreparing = false;
        }
    }

    @Override
    public void setPlayerEventListener(VideoPlayerListener playerEventListener) {
        super.setPlayerEventListener(playerEventListener);
    }


}
