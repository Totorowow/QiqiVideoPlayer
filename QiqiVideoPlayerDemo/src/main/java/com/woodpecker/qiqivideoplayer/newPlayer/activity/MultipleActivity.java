package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.woodpecker.qiqivideoplayer.ConstantVideo;

import com.yc.video.player.VideoPlayer;
import com.yc.video.player.VideoPlayerBuilder;
import com.yc.video.ui.view.BasisVideoController;

import com.woodpecker.qiqivideoplayer.R;

import java.util.ArrayList;
import java.util.List;


public class MultipleActivity extends AppCompatActivity {

    private static String videoPath;
    private static String chestnutPath;
    private VideoPlayer player1;
    private VideoPlayer player2;
    private List<VideoPlayer> mVideoViews = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_play);

        initFindViewById();
        initVideoPlayer();
    }

    private void initFindViewById() {
        player1 = findViewById(R.id.video_player1);
        player2 = findViewById(R.id.video_player2);
    }

    private void initVideoPlayer() {
        //必须设置
        videoPath="android.resource://" + getPackageName() + "/" + R.raw.flower;
        player1.setUrl(videoPath);
        VideoPlayerBuilder.Builder builder = VideoPlayerBuilder.newBuilder();
        builder.setEnableAudioFocus(false);
        VideoPlayerBuilder videoPlayerBuilder = new VideoPlayerBuilder(builder);
        player1.setVideoBuilder(videoPlayerBuilder);
        BasisVideoController controller1 = new BasisVideoController(this);
        player1.setController(controller1);
        mVideoViews.add(player1);

        chestnutPath="android.resource://" + getPackageName() + "/" + R.raw.gold_flower;
        player2.setUrl(chestnutPath);
        VideoPlayerBuilder.Builder builder2 = VideoPlayerBuilder.newBuilder();
        builder.setEnableAudioFocus(false);
        VideoPlayerBuilder videoPlayerBuilder2 = new VideoPlayerBuilder(builder2);
        player2.setVideoBuilder(videoPlayerBuilder2);
        BasisVideoController controller2 = new BasisVideoController(this);
        player2.setController(controller2);
        mVideoViews.add(player2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (VideoPlayer vv : mVideoViews) {
            vv.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (VideoPlayer vv : mVideoViews) {
            vv.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (VideoPlayer vv : mVideoViews) {
            vv.release();
        }
    }

    @Override
    public void onBackPressed() {
        for (VideoPlayer vv : mVideoViews) {
            if (vv.onBackPressed())
                return;
        }
        super.onBackPressed();
    }
}
