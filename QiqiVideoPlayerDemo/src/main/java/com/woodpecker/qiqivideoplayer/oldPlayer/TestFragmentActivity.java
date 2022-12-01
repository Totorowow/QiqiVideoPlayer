package com.woodpecker.qiqivideoplayer.oldPlayer;

import com.woodpecker.qiqivideoplayer.BaseActivity;

import com.woodpecker.qiqivideoplayer.R;
import com.yc.video.old.other.VideoPlayerManager;


/**
 * @author yc
 */
public class TestFragmentActivity extends BaseActivity {

    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    public int getContentView() {
        return R.layout.activity_test_fragment;
    }

    @Override
    public void initView() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new TestFragment())
                .commit();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

}
