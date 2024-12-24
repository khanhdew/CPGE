package com.khanhdew.homealone.desktop.utils;

import com.khanhdew.homealone.desktop.GamePane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class Keyboard implements EventHandler<KeyEvent> {
    private GamePane gamePane;
    private Set<String> pressedKeys = new HashSet<>();

    public Keyboard(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    @Override
    public void handle(KeyEvent event) {
        String code = event.getCode().toString();

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            pressedKeys.add(code);
        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            pressedKeys.remove(code);
        }

        updatePlayerMovement();
    }

    private void updatePlayerMovement() {
        int dx = 0, dy = 0;

        if (pressedKeys.contains("W")) dy -= 10;
        if (pressedKeys.contains("S")) dy += 10;
        if (pressedKeys.contains("A")) dx -= 10;
        if (pressedKeys.contains("D")) dx += 10;

        gamePane.getGameEngine().getPlayer().offset(dx, dy);
    }

    public void resetDirMovement(){
        pressedKeys.clear();
    }
}
