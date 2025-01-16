package com.khanhdew.android.main;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.khanhdew.android.audio.AudioPlayer;
import com.khanhdew.android.main.processor.AndroidInputHandler;
import com.khanhdew.android.main.processor.AndroidRenderer;
import com.khanhdew.android.utils.input.AttackButton;
import com.khanhdew.android.utils.input.Joystick;
import com.khanhdew.android.utils.input.UIComponent;
import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;

import java.util.List;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private final GameApp gameApp;
    private final GameConfiguration gameConfiguration;
    private final GameEngine gameEngine;
    private final AndroidInputHandler inputHandler;

    private final AndroidRenderer renderer;

    private final AudioPlayer audioPlayer;

    private final SurfaceHolder holder;
    private final List<UIComponent> uiComponents;

    public GamePanel(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        gameConfiguration = GameConfiguration.getInstance();
        audioPlayer = new AudioPlayer();
        gameEngine = new GameEngine();
        inputHandler = new AndroidInputHandler(gameEngine, this);
        uiComponents = inputHandler.getUiComponents();
        renderer = new AndroidRenderer(gameEngine, holder, uiComponents);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(inputHandler.getJoystick() != null){
            return inputHandler.getJoystick().touchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    public GameApp getGameApp() {
        return gameApp;
    }

}
