package com.woodpecker.videocache.file;

/**
 * Generator for files to be used for caching.
 */
public interface FileNameGenerator {

    String generate(String url);

}
