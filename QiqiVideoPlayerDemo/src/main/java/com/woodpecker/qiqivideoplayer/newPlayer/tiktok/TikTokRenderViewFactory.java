package com.woodpecker.qiqivideoplayer.newPlayer.tiktok;

import android.content.Context;

import com.woodpecker.video.surface.InterSurfaceView;
import com.woodpecker.video.surface.SurfaceFactory;
import com.woodpecker.video.surface.RenderTextureView;


public class TikTokRenderViewFactory extends SurfaceFactory {

    public static TikTokRenderViewFactory create() {
        return new TikTokRenderViewFactory();
    }

    @Override
    public InterSurfaceView createRenderView(Context context) {
        return new TikTokRenderView(new RenderTextureView(context));
    }
}
