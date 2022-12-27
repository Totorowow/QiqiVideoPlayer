
###  视频播放器通用框架
- 基础封装视频播放器player，可以在ExoPlayer、MediaPlayer，声网RTC视频播放器内核，原生MediaPlayer可以自由切换
- 对于视图状态切换和后期维护拓展，避免功能和业务出现耦合。比如需要支持播放器UI高度定制，而不是该lib库中UI代码
- 针对视频播放，音频播放，播放回放，音视频切换的功能。使用简单，代码拓展性强，封装性好，主要是和业务彻底解耦，暴露接口监听给开发者处理业务具体逻辑
- 该播放器整体架构：播放器内核(自由切换) +  视频播放器 + 边播边缓存 + 高度定制播放器UI视图层



###  播放器视图分类
- 视频常见的布局视图
    - 视频底图(用于显示初始化视频时的封面图)，视频状态视图【加载loading，播放异常，加载视频失败，播放完成等】
    - 改变亮度和声音【改变声音视图，改变亮度视图】，改变视频快进和快退，左右滑动快进和快退视图(手势滑动的快进快退提示框)
    - 顶部控制区视图(包含返回健，title等)，底部控制区视图(包含进度条，播放暂停，时间，切换全屏等)
    - 锁屏布局视图(全屏时展示，其他隐藏)，底部播放进度条视图(很多播放器都有这个)，清晰度列表视图(切换清晰度弹窗)
- 后期可能涉及的布局视图
    - 手势指导页面(有些播放器有新手指导功能)，离线下载的界面(该界面中包含下载列表, 列表的item编辑(全选, 删除))
    - 用户从wifi切换到4g网络，提示网络切换弹窗界面(当网络由wifi变为4g的时候会显示)
    - 图片广告视图(带有倒计时消失)，开始视频广告视图，非会员试看视图
    - 弹幕视图(这个很重要)，水印显示视图，倍速播放界面(用于控制倍速)，底部视频列表缩略图视图
    - 投屏视频视图界面，视频直播间刷礼物界面，老师开课界面，展示更多视图(下载，分享，切换音频等)

### 视频播放器如何使用
####  关于gradle引用说明
- 如下所示
    ```
    //视频UI层，必须要有
    io.github.totorowow:videoplayer:1.2
    //视频缓存，如果不需要则可以不依赖
    io.github.totorowow:videocache:1.2
    //视频内核层，必须有
    io.github.totorowow:videokernel:1.2
   

####  在xml中添加布局
    <com.woodpecker.video.player.QiqiPlayer
        android:id="@+id/video_player"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:layout_gravity="center"
       />


#### 最简单的视频播放器参数设定
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





