package com.woodpecker.qiqivideoplayer.newPlayer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.woodpecker.kernel.utils.VideoLogUtils;

import com.woodpecker.qiqivideoplayer.R;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class QiqiSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    public QiqiSurfaceView(Context context) {
        super(context);
    }
    public QiqiSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        if (mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
        }
    }
    public QiqiSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public QiqiSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setDataPath(String path){
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(surfaceHolder);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    //暂停/开始播放
    public void stop(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    public void release(){
        if (mediaPlayer!=null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop(); //停止播放视频
            }
            mediaPlayer.release(); //释放资源
        }
    }
    public void playOrNo(){
        if (mediaPlayer!=null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }else {
                mediaPlayer.start();
            }
        }
    }
    //拖动更新进度
    public void seekTo(int progress){
        if (mediaPlayer!=null){
            int duration = mediaPlayer.getDuration();
            int current = progress * duration /100;
            mediaPlayer.seekTo(current);
        }
    }
    //获取播放进度
    public int getProgress(){
        if (mediaPlayer!=null){
            int druation = mediaPlayer.getDuration();
            int currentPosition = mediaPlayer.getCurrentPosition();
            int progress = currentPosition * 100 / druation;
            return progress;
        }
        return 0;
    }
    //获取播放时长
    public String getCurrentTime(){
        if (mediaPlayer!=null){
            long currentPostion = mediaPlayer.getCurrentPosition();
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            String f = format.format(currentPostion);
            return f+"";
        }
        return "";
    }
    //获取时长
    public String getDuration(){
        if (mediaPlayer!=null){
            long duration = mediaPlayer.getDuration();
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            return format.format(duration)+"";
        }
        return "";
    }
}