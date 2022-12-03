package com.woodpecker.videocache;

import java.io.File;

/**
 * Listener for cache availability.
 *
 */
public interface CacheListener {

    void onCacheAvailable(File cacheFile, String url, int percentsAvailable);
}
