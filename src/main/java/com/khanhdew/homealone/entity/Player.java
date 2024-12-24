package com.khanhdew.homealone.entity;

import com.khanhdew.homealone.config.GameConfiguration;

public class Player extends BaseEntity {

    public Player(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void update() {
        int dx = 1, dy = 1;
        GameConfiguration config = GameConfiguration.getInstance();
        int windowWidth = config.getWindowWidth();
        int windowHeight = config.getWindowHeight();

        // Check for collision with window borders and bounce back
        if (x < 0 || x + w > windowWidth) {
            dx *= -1; // Reverse horizontal direction
        }
        if (y < 0 || y + h > windowHeight) {
            dy *= -1; // Reverse vertical direction
        }
        if (x > windowWidth || x < 0) {
            dx *= -1;
        }
        if (y > windowHeight || y < 0) {
            dy *= -1;
        }

        // Update position
        x += dx;
        y += dy;
    }
}
