package com.woodpecker.music.inter;


public interface EventCallback<T> {
    void onEvent(T t);
}
