package com.khanhdew.homealone.engine;

import com.khanhdew.homealone.config.GameConfiguration;
import com.khanhdew.homealone.utils.InputHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameApp {
    private GameEngine gameEngine;
    private GameRenderer renderer;
    private InputHandler inputHandler;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    private final double timePerFrame = configuration.getTimePerFrame();
    private final double timePerUpdate = configuration.getTimePerUpdate();
    private boolean SHOW_FPS_UPS = true;

    public GameApp() {
    }

    public GameApp(GameEngine gameEngine, GameRenderer renderer, InputHandler inputHandler) {
        this.gameEngine = gameEngine;
        this.renderer = renderer;
        this.inputHandler = inputHandler;
        gameEngine.getState().pauseGame();
    }

    public void update() {
        gameEngine.update();
        inputHandler.handleInput();
    }

    public void start() {
        gameEngine.getState().resumeGame();
        inputHandler.handleInput();
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

                if (SHOW_FPS_UPS && System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    System.out.println("UPS: " + updates);
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

                if (SHOW_FPS_UPS && System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
        }
    }
}