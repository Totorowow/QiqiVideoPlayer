package com.woodpecker.qiqivideoplayer.ffmpeg.listener

interface PlayerCallback {

    fun onPrepare()

    fun onError(what :Int, extra :Int) :Boolean

    fun onRenderFirstFrame()

    fun onCompleteListener()

}