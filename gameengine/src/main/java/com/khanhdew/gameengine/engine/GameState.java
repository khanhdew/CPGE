package com.khanhdew.gameengine.engine;


public class GameState {
    private static GameState instance;
    private  boolean isRunning = false;
    public GameState() {
        isRunning = true;
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

    public synchronized void pauseGame() {
        isRunning = false;
        System.out.println("Game stopped");
    }

    public synchronized void resumeGame() {
        isRunning = true;
        System.out.println("Game resumed");
    }

    public synchronized static GameState getInstance(){
        if(instance == null)
            return new GameState();
        return getInstance();
    }
}
