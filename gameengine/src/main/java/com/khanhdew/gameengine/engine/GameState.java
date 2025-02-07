package com.khanhdew.gameengine.engine;


public class GameState {
    private static GameState instance;
    private volatile boolean isRunning = false;
    public GameState() {
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void pauseGame() {
        isRunning = false;
        System.out.println("Game stopped");
    }

    public void resumeGame() {
        isRunning = true;
        System.out.println("Game resumed");
    }

    public synchronized static GameState getInstance(){
        if(instance == null)
            instance = new GameState();
        return instance;
    }
}
