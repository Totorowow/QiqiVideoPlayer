package com.woodpecker.videocache.sourcestorage;

import com.woodpecker.videocache.SourceInfo;

/**
 * Storage for {@link SourceInfo}.
 */
public interface SourceInfoStorage {

    SourceInfo get(String url);

    void put(String url, SourceInfo sourceInfo);

    void release();
}
