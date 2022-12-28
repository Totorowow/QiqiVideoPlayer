
### 添加类库
    //视频UI层，必须要有
    io.github.totorowow:videoplayer:1.3
    //视频缓存，如果不需要则可以不依赖
    io.github.totorowow:videocache:1.2
    //视频内核层，必须有
    io.github.totorowow:videokernel:1.3
  
### 添加布局
    <com.woodpecker.video.player.QiqiPlayer
        android:id="@+id/video_player"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:layout_gravity="center"
       />

### 视频播放器参数设定
- 如下所示
    ```
    //创建基础视频播放器，一般播放器的功能
    BasisVideoController controller = new BasisVideoController(this);
    //设置控制器
    mVideoPlayer.setVideoController(controller);
    //设置视频播放链接地址
    mVideoPlayer.setUrl(url);
    //开始播放
    mVideoPlayer.start();
    ```
    
###  注意问题
- 如果是全屏播放，则需要在清单文件中设置当前activity的属性值
    - android:configChanges 保证了在全屏的时候横竖屏切换不会执行Activity的相关生命周期，打断视频的播放
    - android:screenOrientation 固定了屏幕的初始方向
    - 这两个变量控制全屏后和退出全屏的屏幕方向
        ```
            <activity android:name=".VideoActivity"
                android:configChanges="orientation|keyboardHidden|screenSize"
                android:screenOrientation="portrait"/>
        ```
- 如何一进入页面就开始播放视频，稍微延时一下即可
    - 代码如下所示，注意避免直接start()，因为有可能视频还没有初始化完成……
        ```
        mVideoPlayer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mVideoPlayer.start();
            }
        },300);
        ```

###  参考案例
- yc播放器
-   - https://github.com/yangchong211/YCVideoPlayer
- exo播放器
    - https://github.com/google/ExoPlayer
- ijk播放器
    - https://github.com/bilibili/ijkplayer
- 阿里云播放器
    - https://help.aliyun.com/document_detail/51992.html?spm=a2c4g.11186623.2.24.37131bc7j1PoVK#topic2415
- GSY播放器
    - https://github.com/CarGuo/GSYVideoPlayer
- 饺子播放器
    - https://github.com/lipangit/JiaoZiVideoPlayer
- PictureSelector
    - https://github.com/LuckSiege/PictureSelector

