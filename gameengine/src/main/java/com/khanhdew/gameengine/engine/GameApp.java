package com.khanhdew.gameengine.engine;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.engine.threading.AbstractRunnable;
import com.khanhdew.gameengine.engine.threading.GameLogicRunnable;
import com.khanhdew.gameengine.engine.threading.GameRenderRunnable;
import com.khanhdew.gameengine.engine.threading.IoRunnable;
import com.khanhdew.gameengine.utils.AudioManager;
import com.khanhdew.gameengine.utils.InputHandler;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Data
public class GameApp {
    private GameEngine gameEngine;
    private GameRenderer renderer;
    private InputHandler inputHandler;
    private AudioManager audioManager;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private List<AbstractRunnable> threads;
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    private final double timePerFrame = configuration.getTimePerFrame();
    private final double timePerUpdate = configuration.getTimePerUpdate();
    public volatile static int fps = 0;

    public GameApp() {
    }

    public GameApp(GameEngine gameEngine, GameRenderer renderer, InputHandler inputHandler, AudioManager audioManager) {
        this.gameEngine = gameEngine;
        this.renderer = renderer;
        this.inputHandler = inputHandler;
        this.audioManager = audioManager;
        threads = Arrays.asList(
                new GameLogicRunnable(this)
                , new GameRenderRunnable(this)
                , new IoRunnable(this)
        );
        gameEngine.getState().pauseGame();
        inputHandler.handleInput();
        gameEngine.spawnEnemyPerSecond(2, 1);
    }

    public void update() {
        gameEngine.update();
//            renderer.draw();
    }

    public void start() {
        gameEngine.getState().resumeGame();
        submitThread();
    }

    private void submitThread() {
        if (!executorService.isShutdown()) {
            threads.forEach(t -> executorService.submit(t));
        }
    }

    public void resume() {
        gameEngine.getState().resumeGame();
        executorService = Executors.newCachedThreadPool();
        submitThread();
    }

    public void stop() {
        gameEngine.getState().pauseGame();
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("ExecutorService did not terminate");
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}