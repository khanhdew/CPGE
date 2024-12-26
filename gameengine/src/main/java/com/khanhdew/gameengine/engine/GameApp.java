package com.khanhdew.gameengine.engine;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.utils.AudioManager;
import com.khanhdew.gameengine.utils.InputHandler;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class GameApp {
    private GameEngine gameEngine;
    private GameRenderer renderer;
    private InputHandler inputHandler;
    private AudioManager audioManager;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private GameLogicThread gameLogicThread;
    private GameRenderThread gameRenderThread;
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    private final double timePerFrame = configuration.getTimePerFrame();
    private final double timePerUpdate = configuration.getTimePerUpdate();
    public volatile static int fps = 0;
    public volatile static int ups = 0;

    public GameApp() {
    }

    public GameApp(GameEngine gameEngine, GameRenderer renderer, InputHandler inputHandler, AudioManager audioManager) {
        this.gameEngine = gameEngine;
        this.renderer = renderer;
        this.inputHandler = inputHandler;
        this.audioManager = audioManager;
        gameLogicThread = new GameLogicThread();
        gameRenderThread = new GameRenderThread();
        gameEngine.getState().pauseGame();
        inputHandler.handleInput();
        gameEngine.spawnEnemyPerSecond(2, 0);
    }

    public void update() {
        gameEngine.update();
    }

    public void start() {
        gameEngine.getState().resumeGame();
        submitThread();
    }

    private void submitThread() {
        executorService.submit(gameLogicThread);
        executorService.submit(gameRenderThread);
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

    private class GameLogicThread extends Thread implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public GameLogicThread() {
            super("gameLogicThread-" + serialVersionUID);
            System.out.println("LogicThread created");
        }

        @Override
        public void run() {
            long previousTime = System.nanoTime();
            long lastCheck = System.nanoTime(); // Dùng nanoTime để đảm bảo tính chính xác
            double deltaU = 0;
            int updates = 0;

            boolean running = gameEngine.getState().isRunning();

            while (running) {
                long currentTime = System.nanoTime();
                deltaU += (currentTime - previousTime) / timePerUpdate;
                previousTime = currentTime;

                // Cập nhật logic game nếu đủ thời gian cho một lần update
                if (deltaU >= 1) {
                    try {
                        update();
                    } catch (Exception e) {
                        System.err.println("Error during update: " + e.getMessage());
                        e.printStackTrace();
                    }
                    updates++;
                    deltaU--;
                }

                // Kiểm tra UPS mỗi giây
                if (System.nanoTime() - lastCheck >= 1_000_000_000) { // 1 giây = 1 tỷ nano giây
                    lastCheck = System.nanoTime();
                    ups = updates;
                    updates = 0;
                }

                // Kiểm tra lại trạng thái running
                running = gameEngine.getState().isRunning();
            }
        }

    }

    private class GameRenderThread extends Thread implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public GameRenderThread() {
            super("gameRenderThread-" + serialVersionUID);
            System.out.println("RenderThread created");
        }

        @Override
        public void run() {
            long previousTime = System.nanoTime();
            int frames = 0;
            long lastCheck = System.nanoTime(); // Sử dụng nanoTime thay vì currentTimeMillis
            double deltaF = 0;

            try {
                boolean running = gameEngine.getState().isRunning(); // Lưu trạng thái cục bộ
                while (running) {
                    long currentTime = System.nanoTime();
                    deltaF += (currentTime - previousTime) / timePerFrame;
                    previousTime = currentTime;

                    if (deltaF >= 1) {
                        renderer.draw(); // Render frame
                        frames++;
                        deltaF--;
                    }

                    if (System.nanoTime() - lastCheck >= 1_000_000_000) { // Kiểm tra mỗi giây
                        lastCheck = System.nanoTime();
                        fps = frames;
                        frames = 0;
                    }

                    // Cập nhật trạng thái trong vòng lặp
                    running = gameEngine.getState().isRunning();
                }
            } catch (Exception e) {
                System.err.println("Render thread encountered an error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                System.out.println("Render thread stopped.");
            }
        }

        public void stopRender() {
            gameEngine.getState().pauseGame(); // Dừng trạng thái game
            try {
                join(); // Chờ thread hoàn thành
                System.out.println("RenderThread stopped.");
            } catch (InterruptedException e) {
                System.err.println("Error while stopping RenderThread: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

}