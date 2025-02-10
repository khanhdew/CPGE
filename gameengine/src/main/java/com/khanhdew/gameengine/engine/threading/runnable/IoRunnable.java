package com.khanhdew.gameengine.engine.threading.runnable;

import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.threading.AbstractRunnable;

public class IoRunnable extends AbstractRunnable {
    long previousTime = System.nanoTime();
    long lastCheck = System.nanoTime(); // Dùng nanoTime để đảm bảo tính chính xác
    double deltaU = 0;
    public IoRunnable(GameApp gameApp) {
        super( gameApp);
    }

    @Override
    public void run() {

        int updates = 0;
        boolean running = gameApp.getGameEngine().getState().isRunning();
        double timePerUpdate = gameApp.getConfiguration().getTimePerUpdate() / 2;
        int ups = 0;
        while (running) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;


            // Cập nhật logic game nếu đủ thời gian cho một lần update
            if (deltaU >= 1) {
                try {
                    gameApp.getInputHandler().handleRelease();
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
