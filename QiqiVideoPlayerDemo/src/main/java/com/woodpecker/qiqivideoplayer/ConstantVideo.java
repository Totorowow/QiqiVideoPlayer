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
            "一畦春韭绿，十里稻花香",
            "桃花帘外开仍旧，帘中人比桃花瘦",
            "山迢迢兮水长，照轩窗兮明月光",
            "轻烟迷曲径，冷翠滴回廊",
            "篱畔秋酣一觉清，和云伴月不分明",
            "松影一庭惟见鹤，梨花满地不闻莺",
            "入泥怜洁白，匝地惜琼瑶",
            "晓风不散愁千点，宿雨还添泪一痕",
            "枕上轻寒窗外雨，眼前春色梦中人",
    };


    public static List<VideoInfoBean> getVideoList() {
        List<VideoInfoBean> videoList = new ArrayList<>();
        videoList.add(new VideoInfoBean(VideoPlayerTitle[0],
                "https://api.likepoems.com/img/aliyun/pe",
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.flower));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[1],
                "https://api.likepoems.com/img/aliyun/mc",
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.gold_flower));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[2],
                "https://api.likepoems.com/img/aliyun/nature",
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.tulip));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[3],
                "https://api.likepoems.com/img/aliyun/mc",
                "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4"));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[4],
                "https://api.likepoems.com/img/aliyun/pe",
                "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4"));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[5],
                "https://api.likepoems.com/img/aliyun/mc",
                "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4"));


        return videoList;
    }


}
