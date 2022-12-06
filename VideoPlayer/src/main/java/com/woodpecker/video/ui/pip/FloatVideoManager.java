/*
Copyright 2017 yangchong211（github.com/yangchong211）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.woodpecker.video.ui.pip;

import android.content.Context;
import android.view.View;

import com.woodpecker.video.player.QiqiPlayer;
import com.woodpecker.video.player.VideoViewManager;
import com.woodpecker.video.tool.PlayerUtils;


/**
 * <pre>
 *     @author yangchong
 *     blog  : https://github.com/yangchong211
 *     time  : 2017/11/9
 *     desc  : 悬浮播放
 *     revise:
 * </pre>
 */
public class FloatVideoManager {

    //画中画
    public static final String PIP = "pip";
    private static FloatVideoManager instance;
    private QiqiPlayer mQiqiPlayer;
    private FloatVideoView mFloatView;
    private CustomFloatController mFloatController;
    private boolean mIsShowing;
    private int mPlayingPosition = -1;
    private Class mActClass;


    private FloatVideoManager(Context context) {
        mQiqiPlayer = new QiqiPlayer(context);
        VideoViewManager.instance().add(mQiqiPlayer, PIP);
        mFloatController = new CustomFloatController(context);
        mFloatView = new FloatVideoView(context, 0, 0);
    }

    public static FloatVideoManager getInstance(Context context) {
        if (instance == null) {
            synchronized (FloatVideoManager.class) {
                if (instance == null) {
                    instance = new FloatVideoManager(context);
                }
            }
        }
        return instance;
    }

    public void startFloatWindow() {
        if (mIsShowing) {
            return;
        }
        PlayerUtils.removeViewFormParent(mQiqiPlayer);
        mQiqiPlayer.setController(mFloatController);
        mFloatController.setPlayState(mQiqiPlayer.getCurrentPlayState());
        mFloatController.setPlayerState(mQiqiPlayer.getCurrentPlayerState());
        mFloatView.addView(mQiqiPlayer);
        mFloatView.addToWindow();
        mIsShowing = true;
    }

    public void stopFloatWindow() {
        if (!mIsShowing) {
            return;
        }
        mFloatView.removeFromWindow();
        PlayerUtils.removeViewFormParent(mQiqiPlayer);
        mIsShowing = false;
    }

    public void setPlayingPosition(int position) {
        this.mPlayingPosition = position;
    }

    public int getPlayingPosition() {
        return mPlayingPosition;
    }

    public void pause() {
        if (mIsShowing) {
            return;
        }
        mQiqiPlayer.pause();
    }

    public void resume() {
        if (mIsShowing) {
            return;
        }
        mQiqiPlayer.resume();
    }

    public void reset() {
        if (mIsShowing){
            return;
        }
        PlayerUtils.removeViewFormParent(mQiqiPlayer);
        mQiqiPlayer.release();
        mQiqiPlayer.setController(null);
        mPlayingPosition = -1;
        mActClass = null;
    }

    public boolean onBackPress() {
        return !mIsShowing && mQiqiPlayer.onBackPressed();
    }

    public boolean isStartFloatWindow() {
        return mIsShowing;
    }

    /**
     * 显示悬浮窗
     */
    public void setFloatViewVisible() {
        if (mIsShowing) {
            mQiqiPlayer.resume();
            mFloatView.setVisibility(View.VISIBLE);
        }
    }

    public void setActClass(Class cls) {
        this.mActClass = cls;
    }

    public Class getActClass() {
        return mActClass;
    }

}
