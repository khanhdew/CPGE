package com.khanhdew.gameengine.entity;

import java.util.LinkedList;
import java.util.Queue;

public abstract class ObjectPool<T> {
    private final Queue<T> pool = new LinkedList<>();

    protected abstract T create();

    public synchronized T borrow() {
        return pool.isEmpty() ? create() : pool.poll();
    }

    public synchronized void returnToPool(T object) {
        pool.offer(object);
    }

}
