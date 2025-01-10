package com.khanhdew.gameengine.engine.threading;

import com.khanhdew.gameengine.engine.GameApp;

import java.io.Serializable;

public abstract class AbstractRunnable implements Runnable, Serializable {
    protected GameApp gameApp;

    public AbstractRunnable(GameApp gameApp) {
        this.gameApp = gameApp;
    }

    public abstract void run();

}
