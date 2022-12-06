package com.woodpecker.qiqivideoplayer.newPlayer.tiny;

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
import com.woodpecker.qiqivideoplayer.newPlayer.list.VideoRecyclerViewAdapter;


import com.woodpecker.video.config.ConstantKeys;
import com.woodpecker.video.config.VideoInfoBean;
import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.player.SimpleStateListener;
import com.woodpecker.video.tool.PlayerUtils;
import com.woodpecker.video.ui.view.BasisVideoController;

import com.woodpecker.qiqivideoplayer.R;

import java.util.List;

/**
 * 小窗播放
 */
public class TinyScreenActivity extends AppCompatActivity implements OnItemChildClickListener {

    private BasisVideoController mController;
    private List<VideoInfoBean> mVideos;
    private LinearLayoutManager mLinearLayoutManager;
    private QiqiPlayer mQiqiPlayer;
    private int mCurPos = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_recycler_view);
        initView();
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


    protected void initView() {
        mQiqiPlayer = new QiqiPlayer(this);
        mQiqiPlayer.setOnStateChangeListener(new SimpleStateListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                if (playState == ConstantKeys.CurrentState.STATE_BUFFERING_PLAYING) {
                    if (mQiqiPlayer.isTinyScreen()) {
                        mQiqiPlayer.stopTinyScreen();
                        releaseVideoView();
                    }
                }
            }
        });
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
                if (position == mCurPos) {
                    startPlay(position, false);
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                VideoRecyclerViewAdapter.VideoHolder holder = (VideoRecyclerViewAdapter.VideoHolder) view.getTag();
                int position = holder.mPosition;
                if (position == mCurPos && !mQiqiPlayer.isFullScreen()) {
                    mQiqiPlayer.startTinyScreen();
                    mQiqiPlayer.setController(null);
                    mController.setPlayState(ConstantKeys.CurrentState.STATE_IDLE);
                }
            }
        });
    }

    @Override
    public void onItemChildClick(int position) {
        startPlay(position, true);
    }

    /**
     * 开始播放
     *
     * @param position 列表位置
     */
    protected void startPlay(int position, boolean isRelease) {
        if (mQiqiPlayer.isTinyScreen())
            mQiqiPlayer.stopTinyScreen();
        if (mCurPos != -1 && isRelease) {
            releaseVideoView();
        }
        VideoInfoBean videoBean = mVideos.get(position);
        mQiqiPlayer.setUrl(videoBean.getVideoUrl());
        View itemView = mLinearLayoutManager.findViewByPosition(position);
        if (itemView == null) return;
        //注意：要先设置控制才能去设置控制器的状态。
        mQiqiPlayer.setController(mController);
        mController.setPlayState(mQiqiPlayer.getCurrentPlayState());

        VideoRecyclerViewAdapter.VideoHolder viewHolder = (VideoRecyclerViewAdapter.VideoHolder) itemView.getTag();
        //把列表中预置的PrepareView添加到控制器中，注意isPrivate此处只能为true。
        mController.addControlComponent(viewHolder.mPrepareView, true);
        PlayerUtils.removeViewFormParent(mQiqiPlayer);
        viewHolder.mPlayerContainer.addView(mQiqiPlayer, 0);
        mQiqiPlayer.start();
        mCurPos = position;
    }

    private void releaseVideoView() {
        mQiqiPlayer.release();
        if (mQiqiPlayer.isFullScreen()) {
            mQiqiPlayer.stopFullScreen();
        }
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mCurPos = -1;
    }
}
