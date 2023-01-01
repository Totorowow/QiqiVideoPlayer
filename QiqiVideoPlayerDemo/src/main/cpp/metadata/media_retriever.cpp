/*
 * Created by frank on 2022/2/23
 *
 * part of code from William Seemann
 */

#include <media_retriever.h>

MediaRetriever::MediaRetriever()
{
	state = nullptr;
}

MediaRetriever::~MediaRetriever()
{
	Mutex::Autolock lock(mLock);
	::release_retriever(&state);
}

int MediaRetriever::setDataSource(const char *srcUrl)
{
	Mutex::Autolock lock(mLock);
	return ::set_data_source(&state, srcUrl);
}

int MediaRetriever::setDataSource(int fd, int64_t offset, int64_t length)
{
	Mutex::Autolock lock(mLock);
    return ::set_data_source_fd(&state, fd, offset, length);
}

const char* MediaRetriever::extractMetadata(const char *key)
{
	Mutex::Autolock lock(mLock);
    return ::extract_metadata(&state, key);
}

int MediaRetriever::getFrameAtTime(int64_t timeUs, int option, AVPacket *pkt)
{
	Mutex::Autolock lock(mLock);
	return ::get_frame_at_time(&state, timeUs, option, pkt);
}

int MediaRetriever::getScaledFrameAtTime(int64_t timeUs, int option, AVPacket *pkt, int width, int height)
{
	Mutex::Autolock lock(mLock);
	return ::get_scaled_frame_at_time(&state, timeUs, option, pkt, width, height);
}

int MediaRetriever::getAudioThumbnail(AVPacket *pkt)
{
	Mutex::Autolock lock(mLock);
	return ::get_audio_thumbnail(&state, pkt);
}

int MediaRetriever::setNativeWindow(ANativeWindow* native_window)
{
	Mutex::Autolock lock(mLock);
	return ::set_native_window(&state, native_window);
}