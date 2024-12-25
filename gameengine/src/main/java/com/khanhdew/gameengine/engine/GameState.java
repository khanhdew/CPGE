package com.khanhdew.gameengine.engine;


public class GameState {
    private static boolean isRunning = false;
    public GameState() {
        isRunning = true;
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public void pauseGame() {
        isRunning = false;
    }

    public void resumeGame() {
        isRunning = true;
    }
}
