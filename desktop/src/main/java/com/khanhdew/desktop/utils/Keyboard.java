package com.khanhdew.desktop.utils;

import com.khanhdew.desktop.main.GamePane;
import javafx.scene.input.KeyEvent;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Keyboard {
    private GamePane gamePane;
    private Set<String> pressedKeys = new HashSet<>();
    @Getter
    private boolean keyReleased = true;

    public Keyboard(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public void handleKeyPress(KeyEvent event) {
        String code = event.getCode().toString();
        pressedKeys.add(code);
        keyReleased = false;
    }

    public void handleKeyRelease(KeyEvent event) {
        String code = event.getCode().toString();
        pressedKeys.remove(code);
        // check if all keys are released
        keyReleased = pressedKeys.isEmpty();
    }

    public void updatePlayerMovement() {
        double dx = 0, dy = 0;
        // calculate direction
        if (pressedKeys.contains("W") || pressedKeys.contains("UP")) dy -= 1;
        if (pressedKeys.contains("S") || pressedKeys.contains("DOWN")) dy += 1;
        if (pressedKeys.contains("A") || pressedKeys.contains("LEFT")) dx -= 1;
        if (pressedKeys.contains("D") || pressedKeys.contains("RIGHT")) dx += 1;
        // calculate angle
        double angle = Math.atan2(dy, dx);
        if (dx != 0 && dy != 0) {
            dx *= Math.sqrt(0.5);
            dy *= Math.sqrt(0.5);
        }

        // Normalize the movement vector to ensure smooth transitions
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        gamePane.getGameEngine().getPlayer().translateXY(dx, dy);
    }

    public void resetDirMovement() {
        pressedKeys.clear();
    }

}
