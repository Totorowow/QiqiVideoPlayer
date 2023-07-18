package com.woodpecker.qiqivideoplayer;

import com.woodpecker.video.config.VideoInfoBean;

import java.util.ArrayList;
import java.util.List;

public class ConstantVideo {


    public static String[] VideoPlayerList = {
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.badminton_highlights,
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.gold_flower,
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.tulip,
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.white_flower,
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.silent_night,
            "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.parrot,

    };

    public static String OUTPUT_PATH="output_path";


    public static String[] VideoPlayerTitle = {
            "李诗沣VS石宇奇",
            "野菊花",
            "随风摇曳的郁金香",
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
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.drawable.badminton_screenshot,
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.badminton_highlights));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[1],
                "https://api.likepoems.com/img/aliyun/mc",
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.gold_flower));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[2],
                "https://api.likepoems.com/img/aliyun/nature",
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.tulip));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[3],
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.drawable.white_flower_cover,
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.white_flower));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[4],
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.drawable.silent_night_cover,
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.silent_night));

        videoList.add(new VideoInfoBean(VideoPlayerTitle[5],
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.drawable.parrot_cover,
                "android.resource://" + QiqiApplication.getInstance().getPackageName() + "/" + R.raw.parrot));


        return videoList;
    }


}
