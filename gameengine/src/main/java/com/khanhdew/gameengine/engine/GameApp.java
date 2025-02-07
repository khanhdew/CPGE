package com.khanhdew.gameengine.engine;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.engine.threading.TaskQueue;
import com.khanhdew.gameengine.utils.AudioManager;
import com.khanhdew.gameengine.utils.InputHandler;

import lombok.Data;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class GameApp {
    private GameEngine gameEngine;
    private GameRenderer renderer;
    private InputHandler inputHandler;
    private AudioManager audioManager;
    private TaskQueue taskQueue;
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    private final double timePerFrame = configuration.getTimePerFrame();
    private final double timePerUpdate = configuration.getTimePerUpdate();
    public static AtomicInteger fps = new AtomicInteger(0);

    public GameApp() {
    }

    public GameApp(GameEngine gameEngine, GameRenderer renderer, InputHandler inputHandler, AudioManager audioManager) {
        this.gameEngine = gameEngine;
        this.renderer = renderer;
        this.inputHandler = inputHandler;
        this.audioManager = audioManager;
        taskQueue = new TaskQueue(this);
        gameEngine.getState().pauseGame();
        inputHandler.handleInput();
        gameEngine.spawnEnemyPerSecond(2, 1);
    }

    public void update() {
        gameEngine.update();
    }

    public void start() {
        gameEngine.getState().resumeGame();
        taskQueue.start();
    }


    public void resume() {
        gameEngine.getState().resumeGame();
        taskQueue.resume();
    }

    public void stop() {
        gameEngine.getState().pauseGame();
        taskQueue.stop();
    }

}