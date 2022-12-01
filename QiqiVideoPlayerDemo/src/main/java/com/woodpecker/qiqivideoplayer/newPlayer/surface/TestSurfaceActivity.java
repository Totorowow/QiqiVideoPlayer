package com.woodpecker.qiqivideoplayer.newPlayer.surface;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.woodpecker.qiqivideoplayer.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class TestSurfaceActivity extends AppCompatActivity {

    private MySurfaceView mSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FrameLayout fl = (FrameLayout) findViewById(R.id.fl);


        mSurfaceView = new MySurfaceView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        fl.addView(mSurfaceView, params);

        SurfaceHolder holder = mSurfaceView.getHolder();
        //增加surfaceView的监听
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }
        });
    }
}
