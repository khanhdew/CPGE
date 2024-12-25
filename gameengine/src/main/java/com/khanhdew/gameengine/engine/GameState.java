package com.khanhdew.gameengine.engine;


public class GameState {
    private boolean isRunning = false;
    public GameState() {
        this.isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void pauseGame() {
        this.isRunning = false;
    }

    public void resumeGame() {
        this.isRunning = true;
    }
}
