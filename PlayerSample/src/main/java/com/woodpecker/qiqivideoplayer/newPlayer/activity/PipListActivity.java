package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.woodpecker.qiqivideoplayer.ConstantVideo;
import com.woodpecker.qiqivideoplayer.newPlayer.list.OnItemChildClickListener;
import com.woodpecker.qiqivideoplayer.newPlayer.adapter.VideoRecyclerViewAdapter;


import com.woodpecker.video.config.ConstantKeys;
import com.woodpecker.video.config.VideoInfoBean;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.player.VideoViewManager;
import com.woodpecker.video.tool.PlayerUtils;
import com.woodpecker.video.ui.pip.FloatVideoManager;
import com.woodpecker.video.ui.view.BasisVideoController;

import com.woodpecker.qiqivideoplayer.R;

import java.util.List;


public class PipListActivity extends AppCompatActivity implements OnItemChildClickListener {

    private FloatVideoManager mPIPManager;
    private QiqiPlayer mVideoView;
    private BasisVideoController mController;
    private List<VideoInfoBean> mVideos;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_recycler_view);
        initView();
    }

    protected void initView() {
        mPIPManager = FloatVideoManager.getInstance(this);
        mVideoView = VideoViewManager.instance().get(FloatVideoManager.PIP);
        mController = new BasisVideoController(this);
        initRecyclerView();
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mVideos = ConstantVideo.getVideoList();
        VideoRecyclerViewAdapter adapter = new VideoRecyclerViewAdapter(mVideos);
        adapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                VideoRecyclerViewAdapter.VideoHolder holder = (VideoRecyclerViewAdapter.VideoHolder) view.getTag();
                int position = holder.mPosition;
                if (position == mPIPManager.getPlayingPosition()) {
                    startPlay(position, false);
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                VideoRecyclerViewAdapter.VideoHolder holder = (VideoRecyclerViewAdapter.VideoHolder) view.getTag();
                int position = holder.mPosition;
                if (position == mPIPManager.getPlayingPosition()) {
                    startFloatWindow();
                    mController.setPlayState(ConstantKeys.CurrentState.STATE_IDLE);
                }
            }
        });
    }

    private void startFloatWindow() {
        mPIPManager.startFloatWindow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPIPManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPIPManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPIPManager.reset();
    }

    @Override
    public void onBackPressed() {
        if (mPIPManager.onBackPress()) return;
        super.onBackPressed();

    }

    @Override
    public void onItemChildClick(int position) {
        startPlay(position, true);
    }


    protected void startPlay(int position, boolean isRelease) {
        if (mPIPManager.isStartFloatWindow())
            mPIPManager.stopFloatWindow();
        if (mPIPManager.getPlayingPosition() != -1 && isRelease) {
            releaseVideoView();
        }
        VideoInfoBean videoBean = mVideos.get(position);
        mVideoView.setUrl(videoBean.getVideoUrl());
        View itemView = mLinearLayoutManager.findViewByPosition(position);
        if (itemView == null) return;
        //注意：要先设置控制才能去设置控制器的状态。
        mVideoView.setController(mController);
        mController.setPlayState(mVideoView.getCurrentPlayState());

        VideoRecyclerViewAdapter.VideoHolder viewHolder = (VideoRecyclerViewAdapter.VideoHolder) itemView.getTag();
        //把列表中预置的PrepareView添加到控制器中，注意isPrivate此处只能为true。
        mController.addControlComponent(viewHolder.mPrepareView, true);
        PlayerUtils.removeViewFormParent(mVideoView);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        mVideoView.start();
        mPIPManager.setPlayingPosition(position);
    }

    private void releaseVideoView() {
        mVideoView.release();
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
        }
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mPIPManager.setPlayingPosition(-1);
    }
}
