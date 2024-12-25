package com.khanhdew.gameengine.engine;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.utils.AudioManager;
import com.khanhdew.gameengine.utils.InputHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class GameApp {
    private GameEngine gameEngine;
    private GameRenderer renderer;
    private InputHandler inputHandler;
    private AudioManager audioManager;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    private final double timePerFrame = GameConfiguration.timePerFrame;
    private final double timePerUpdate = GameConfiguration.timePerUpdate;
    private final boolean SHOW_FPS_UPS = GameConfiguration.SHOW_FPS;
    public static int fps = 0;
    public static int ups = 0;

    public GameApp() {
    }

    public GameApp(GameEngine gameEngine, GameRenderer renderer, InputHandler inputHandler, AudioManager audioManager) {
        this.gameEngine = gameEngine;
        this.renderer = renderer;
        this.inputHandler = inputHandler;
        this.audioManager = audioManager;
        gameEngine.getState().pauseGame();
        inputHandler.handleInput();
    }

    public void update() {
        gameEngine.update();
    }

    public void start() {
        gameEngine.getState().resumeGame();
        executorService.submit(new GameLogicThread());
        executorService.submit(new GameRenderThread());
    }

    public void resume() {
        gameEngine.getState().resumeGame();
        executorService = Executors.newCachedThreadPool();
        executorService.submit(new GameLogicThread());
        executorService.submit(new GameRenderThread());
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

    private class GameLogicThread extends Thread {
        @Override
        public void run() {
            long previousTime = System.nanoTime();
            int updates = 0;
            long lastCheck = System.currentTimeMillis();
            double deltaU = 0;

            while (gameEngine.getState().isRunning()) {
                long currentTime = System.nanoTime();
                deltaU += (currentTime - previousTime) / timePerUpdate;
                previousTime = currentTime;

                if (deltaU >= 1) {
                    update();
                    updates++;
                    deltaU--;
                }

                if (System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    ups = updates;
                    updates = 0;

                }
            }
        }
    }

    private class GameRenderThread extends Thread {
        @Override
        public void run() {
            long previousTime = System.nanoTime();
            int frames = 0;
            long lastCheck = System.currentTimeMillis();
            double deltaF = 0;

            while (gameEngine.getState().isRunning()) {
                long currentTime = System.nanoTime();
                deltaF += (currentTime - previousTime) / timePerFrame;
                previousTime = currentTime;

                if (deltaF >= 1) {
                    renderer.draw(gameEngine);
                    frames++;
                    deltaF--;
                }

                if (System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    fps = frames;
                    frames = 0;
                }
            }
        }
    }
}