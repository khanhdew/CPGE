package com.khanhdew.gameengine.engine;


import lombok.Getter;

@Getter
public class GameState {
    public enum GameStateEnum {
        MENU,
        PLAYING,
        PAUSED,
        GAME_OVER
    }
    private static GameState instance;
    private GameStateEnum currentState;

    private GameState() {
        currentState = GameStateEnum.MENU;
    }

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }
    public void startGame() {
        currentState = GameStateEnum.PLAYING;
        System.out.println("Game started");
    }

    public void pauseGame() {
        currentState = GameStateEnum.PAUSED;
        System.out.println("Game paused");
    }

    public void resumeGame() {
        currentState = GameStateEnum.PLAYING;
        System.out.println("Game resumed");
    }
    public void gameOver() {
        currentState = GameStateEnum.GAME_OVER;
        System.out.println("Game Over");
    }
    public boolean isRunning() {
        return currentState == GameStateEnum.PLAYING;
    }
}
