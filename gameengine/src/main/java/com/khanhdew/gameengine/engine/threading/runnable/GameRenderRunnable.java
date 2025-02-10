package com.khanhdew.gameengine.engine.threading.runnable;

import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;
import com.khanhdew.gameengine.engine.threading.AbstractRunnable;

import java.io.Serial;

public class GameRenderRunnable extends AbstractRunnable {
    private final GameEngine gameEngine;
    @Serial
    private static final long serialVersionUID = 1L;

    public GameRenderRunnable(GameApp gameApp) {
        super(gameApp);
        this.gameEngine = gameApp.getGameEngine();
    }

    @Override
    public void run() {
        long previousTime = System.nanoTime();
        long lastCheck = System.nanoTime(); // Sử dụng nanoTime thay vì currentTimeMillis
        double deltaF = 0;
        int frames = 0;

        try {
            boolean running = gameEngine.getState().isRunning(); // Lưu trạng thái cục bộ
            while (running) {
                long currentTime = System.nanoTime();
                deltaF += (currentTime - previousTime) / gameApp.getTimePerFrame();
                previousTime = currentTime;

                if (deltaF >= 1) {
                    gameApp.getRenderer().draw(); // Render frame
                    frames++;
                    deltaF--;
                }

                if (System.nanoTime() - lastCheck >= 1_000_000_000) { // Kiểm tra mỗi giây
                    lastCheck = System.nanoTime();
                    GameApp.fps.set(frames);
                    frames = 0;
                }

                // Cập nhật trạng thái trong vòng lặp
                running = gameEngine.getState().isRunning();
            }
        } catch (Exception e) {
            System.err.println("Render thread encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void reset() {

    }

}
