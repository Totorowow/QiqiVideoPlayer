package com.woodpecker.qiqivideoplayer;

import com.woodpecker.video.config.VideoInfoBean;

import java.util.ArrayList;
import java.util.List;

public class ConstantVideo {


    public static String[] VideoPlayerList = {
            "android.resource://" + BaseApplication.getInstance().getPackageName() + "/" + R.raw.flower,
            "android.resource://" + BaseApplication.getInstance().getPackageName() + "/" + R.raw.gold_flower,
            "android.resource://" + BaseApplication.getInstance().getPackageName() + "/" + R.raw.tulip,
            "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4",
            "http://jzvd.nathen.cn/d2438fd1c37c4618a704513ad38d68c5/68626a9d53ca421c896ac8010f172b68-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/25a8d119cfa94b49a7a4117257d8ebd7/f733e65a22394abeab963908f3c336db-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/7512edd1ad834d40bb5b978402274b1a/9691c7f2d7b74b5e811965350a0e5772-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",
            "http://play.g3proxy.lecloud.com/vod/v2/MjUxLzE2LzgvbGV0di11dHMvMTQvdmVyXzAwXzIyLTExMDc2NDEzODctYXZjLTE5OTgxOS1hYWMtNDgwMDAtNTI2MTEwLTE3MDg3NjEzLWY1OGY2YzM1NjkwZTA2ZGFmYjg2MTVlYzc5MjEyZjU4LTE0OTg1NTc2ODY4MjMubXA0?b=259&mmsid=65565355&tm=1499247143&key=f0eadb4f30c404d49ff8ebad673d3742&platid=3&splatid=345&playid=0&tss=no&vtype=21&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"
    };


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
                "android.resource://" + BaseApplication.getInstance().getPackageName() + "/" + R.raw.flower));

        videoList.add(new VideoInfoBean("如果项目可以，可以给个star",
                "https://img-blog.csdnimg.cn/20201013092150588.png",
                "android.resource://" + BaseApplication.getInstance().getPackageName() + "/" + R.raw.gold_flower));

        videoList.add(new VideoInfoBean("把本地项目代码复制到拷贝的仓库",
                "https://img-blog.csdnimg.cn/2020101309293329.png",
                "android.resource://" + BaseApplication.getInstance().getPackageName() + "/" + R.raw.tulip));

        videoList.add(new VideoInfoBean("有bug，可以直接提出来，欢迎一起探讨",
                "https://img-blog.csdnimg.cn/20201013094115174.png",
                "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4"));

        videoList.add(new VideoInfoBean("逗比逗比把本地项目代码复制到拷贝的仓库",
                "https://img-blog.csdnimg.cn/20201013091432693.jpg",
                "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4"));

        videoList.add(new VideoInfoBean("预告片6",
                "https://img-blog.csdnimg.cn/20201013091432695.jpg",
                "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4"));

        videoList.add(new VideoInfoBean("预告片7",
                "https://img-blog.csdnimg.cn/20201013091432667.jpg",
                "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4"));

        videoList.add(new VideoInfoBean("大家好，我是潇湘剑雨逗比",
                "https://img-blog.csdnimg.cn/20201012215233584.png",
                "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4"));

        videoList.add(new VideoInfoBean("依次输入命令上传代码",
                "https://img-blog.csdnimg.cn/20201013091432667.jpg",
                "http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4"));

        videoList.add(new VideoInfoBean("依次输入命令上传代码",
                "https://img-blog.csdnimg.cn/20201013091432625.jpg",
                "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4"));

        videoList.add(new VideoInfoBean("预告片11",
                "https://img-blog.csdnimg.cn/20201013091432602.jpg",
                "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4"));

        videoList.add(new VideoInfoBean("依次输入命令上传代码",
                "https://img-blog.csdnimg.cn/20201013091432603.jpg",
                "http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4"));

        videoList.add(new VideoInfoBean("如果项目可以，可以给个star",
                "https://img-blog.csdnimg.cn/20201013091432616.jpg",
                "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4"));

        videoList.add(new VideoInfoBean("预告片14",
                "https://img-blog.csdnimg.cn/20201013091432581.jpg",
                "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312083533415853.mp4"));

        return videoList;
    }


}
