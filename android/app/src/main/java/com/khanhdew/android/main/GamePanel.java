package com.khanhdew.android.main;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.khanhdew.android.audio.AudioPlayer;
import com.khanhdew.android.main.processor.AndroidInputHandler;
import com.khanhdew.android.main.processor.AndroidRenderer;
import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private final GameApp gameApp;
    private final GameConfiguration gameConfiguration;
    private final GameEngine gameEngine;
    private final AndroidInputHandler inputHandler;

    private final AndroidRenderer renderer;

    private final AudioPlayer audioPlayer;

    private final SurfaceHolder holder;

    public GamePanel(Context context, int width, int height) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        System.out.println(width + " " + height);
        gameConfiguration = new GameConfiguration(width, height, 1);
        audioPlayer = new AudioPlayer();
        gameEngine = new GameEngine();
        inputHandler = new AndroidInputHandler(gameEngine, this);
        renderer = new AndroidRenderer(gameEngine, holder);
        gameApp = new GameApp(gameEngine, renderer, inputHandler, audioPlayer);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        inputHandler.setGamePanel(this);
        gameApp.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        gameApp.stop();
    }

    public GameApp getGameApp() {
        return gameApp;
    }

}
