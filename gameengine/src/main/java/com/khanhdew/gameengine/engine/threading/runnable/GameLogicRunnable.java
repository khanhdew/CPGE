package com.khanhdew.gameengine.engine.threading.runnable;

import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.threading.AbstractRunnable;

import java.io.Serial;

public class GameLogicRunnable extends AbstractRunnable {
    long lastCheck = System.nanoTime(); // Dùng nanoTime để đảm bảo tính chính xác
    double deltaU = 0;
    long previousTime = System.nanoTime();
    @Serial
    private static final long serialVersionUID = 1L;

    public GameLogicRunnable(GameApp gameApp) {
        super(gameApp);
    }

    @Override
    public void run() {
        long previousTime = System.nanoTime();
        long lastCheck = System.nanoTime(); // Dùng nanoTime để đảm bảo tính chính xác
        double deltaU = 0;
        int updates = 0;
        boolean running = gameApp.getGameEngine().getState().isRunning();
        double timePerUpdate = gameApp.getConfiguration().getTimePerUpdate();
        int ups = 0;
        while (running) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;


            // Cập nhật logic game nếu đủ thời gian cho một lần update
            if (deltaU >= 1) {
                try {
                    gameApp.update();
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
            running = gameApp.getGameEngine().getState().isRunning();
        }
    }

    @Override
    public void reset() {
        deltaU = 0;
        previousTime = System.nanoTime();
        lastCheck = System.nanoTime();
    }
}
