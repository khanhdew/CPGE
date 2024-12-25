package com.khanhdew.desktop.utils;

import com.khanhdew.desktop.GamePane;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class Keyboard {
    private GamePane gamePane;
    private Set<String> pressedKeys = new HashSet<>();

    public Keyboard(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public void handleKeyPress(KeyEvent event) {
        String code = event.getCode().toString();
        pressedKeys.add(code);
        updatePlayerMovement();
    }

    public void handleKeyRelease(KeyEvent event) {
        String code = event.getCode().toString();
        pressedKeys.remove(code);
    }

    private void updatePlayerMovement() {
        int dx = 0, dy = 0;
        if (pressedKeys.contains("W")) dy -= 1;
        if (pressedKeys.contains("S")) dy += 1;
        if (pressedKeys.contains("A")) dx -= 1;
        if (pressedKeys.contains("D")) dx += 1;

        gamePane.getGameEngine().getPlayer().translateXY(dx, dy);
    }

    public void resetDirMovement() {
        pressedKeys.clear();
    }


}
