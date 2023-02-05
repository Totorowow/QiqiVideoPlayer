package com.woodpecker.qiqivideoplayer;

import com.woodpecker.video.config.VideoInfoBean;

import java.util.ArrayList;
import java.util.List;

public class ConstantVideo {


    public static String[] VideoPlayerList = {
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.flower,
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.gold_flower,
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.tulip,
            "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4",
            "http://jzvd.nathen.cn/d2438fd1c37c4618a704513ad38d68c5/68626a9d53ca421c896ac8010f172b68-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/25a8d119cfa94b49a7a4117257d8ebd7/f733e65a22394abeab963908f3c336db-5287d2089db37e62345123a1be272f8b.mp4",

    };

    public static String OUTPUT_PATH="output_path";


    public static String[] VideoPlayerTitle = {
            "大家好，我是潇湘剑雨",
            "大家好，我是潇湘剑雨",
            "如果项目可以，可以给个star",
            "有bug，可以直接提出来，欢迎一起探讨",
            "把本地项目代码复制到拷贝的仓库",
            "依次输入命令上传代码",
            "把本地项目代码复制到拷贝的仓库",
            "依次输入命令上传代码",
            "逗比逗比把本地项目代码复制到拷贝的仓库",
    };


    public static List<VideoInfoBean> getVideoList() {
        List<VideoInfoBean> videoList = new ArrayList<>();
        videoList.add(new VideoInfoBean("大家好，我是潇湘剑雨",
                "https://img-blog.csdnimg.cn/20201012215233584.png",
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.flower));

        videoList.add(new VideoInfoBean("如果项目可以，可以给个star",
                "https://img-blog.csdnimg.cn/20201013092150588.png",
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.gold_flower));

        videoList.add(new VideoInfoBean("把本地项目代码复制到拷贝的仓库",
                "https://img-blog.csdnimg.cn/2020101309293329.png",
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.tulip));

        videoList.add(new VideoInfoBean("有bug，可以直接提出来，欢迎一起探讨",
                "https://img-blog.csdnimg.cn/20201013094115174.png",
                "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4"));

        videoList.add(new VideoInfoBean("逗比逗比把本地项目代码复制到拷贝的仓库",
                "https://img-blog.csdnimg.cn/20201013091432693.jpg",
                "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4"));

        videoList.add(new VideoInfoBean("预告片6",
                "https://img-blog.csdnimg.cn/20201013091432695.jpg",
                "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4"));





        return videoList;
    }


}
