package com.khanhdew.homealone.engine;

import com.khanhdew.homealone.utils.InputHandler;


public class GameApp {
    private GameEngine gameEngine;
    private GameRenderer renderer;
    private InputHandler inputHandler;
    private Thread gameLogicThread;
    private Thread gameRenderThread;


    public GameApp() {
        gameEngine.getState().pauseGame();
    }

    public GameApp(GameEngine gameEngine, GameRenderer renderer, InputHandler inputHandler) {
        this.gameEngine = gameEngine;
        this.renderer = renderer;
        this.inputHandler = inputHandler;
    }

    public void update() {
        gameEngine.update();
        renderer.draw(gameEngine);
        inputHandler.handleInput();
    }

    // Bắt đầu game
    public void start() {
        gameEngine.getState().resumeGame();
        gameLogicThread = new GameLogicThread();
        gameRenderThread = new GameRenderThread();
        inputHandler.handleInput();

        gameLogicThread.start();
        gameRenderThread.start();
    }

    // Dừng game
    public void stop() {
        gameEngine.getState().pauseGame();
        try {
            gameLogicThread.join();
            gameRenderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Thread xử lý game logic (update game state)
    private class GameLogicThread extends Thread {
        @Override
        public void run() {
            while (gameEngine.getState().isRunning()) {
                // Cập nhật trạng thái game, di chuyển đối tượng, xử lý va chạm, v.v.
                updateGameLogic();

                // Chờ một khoảng thời gian (để điều chỉnh tốc độ cập nhật game)
                try {
                    Thread.sleep(16);  // 60 FPS (1000ms / 60 ≈ 16ms)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void updateGameLogic() {
            // Cập nhật tất cả các entities trong game
            gameEngine.update();
        }
    }

    // Thread xử lý rendering (vẽ lên màn hình)
    private class GameRenderThread extends Thread {
        @Override
        public void run() {
            while (gameEngine.getState().isRunning()) {
                render();

                // Chờ một khoảng thời gian (để đảm bảo game không quá nhanh)
                try {
                    Thread.sleep(16);  // 60 FPS (1000ms / 60 ≈ 16ms)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void render() {
            renderer.draw(gameEngine);
        }
    }
}
