package com.khanhdew.gameengine.entity;

import com.khanhdew.gameengine.config.GameConfiguration;

public class Enemy extends MovableEntity {
    public Enemy(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    private int velocityX = 1;
    private int velocityY = 1;

    @Override
    public void update() {
        // Update position
        x += velocityX;
        y += velocityY;

        // Check for collision with window boundaries and reverse direction if necessary
        if (x <= 0 || x + w >= GameConfiguration.getInstance().getWindowWidth()) {
            velocityX = -velocityX;
        }
        if (y <= 0 || y + h >= GameConfiguration.getInstance().getWindowHeight()) {
            velocityY = -velocityY;
        }
    }
}
