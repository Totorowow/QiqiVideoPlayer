package com.woodpecker.qiqivideoplayer.newPlayer.surface;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.woodpecker.qiqivideoplayer.ConstantVideo;
import com.woodpecker.qiqivideoplayer.R;

import androidx.appcompat.app.AppCompatActivity;


public class TestSurfaceActivity extends AppCompatActivity implements View.OnClickListener{

    private QiqiSurfaceView surfaceView;
    private Button btn_play,btn_pause,btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        surfaceView = findViewById(R.id.surfaceView);
        btn_play = findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);
        btn_pause = findViewById(R.id.btn_pause);
        btn_pause.setOnClickListener(this);
        btn_stop = findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //播放，继续播放的按钮
            case R.id.btn_play:
                surfaceView.setDataPath(ConstantVideo.VideoPlayerList[3]);
            case R.id.btn_pause:
                surfaceView.playOrNo();
                break;
            //停止播放视频 后如果再次点击播放视频按钮 则视频是从头播放
            case R.id.btn_stop:
                surfaceView.stop();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        surfaceView.release();
    }
}
