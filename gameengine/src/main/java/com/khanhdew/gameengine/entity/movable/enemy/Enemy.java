package com.khanhdew.gameengine.entity.movable.enemy;

import com.khanhdew.gameengine.entity.movable.Character;

public class Enemy extends Character {
    public Enemy(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    @Override
    public void update() {
//        // Update position
//        x += velocityX;
//        y += velocityY;
//
//        // Check for collision with window boundaries and reverse direction if necessary
//        if (x <= 0 || x + w >= GameConfiguration.getInstance().getWindowWidth()) {
//            velocityX = -velocityX;
//        }
//        if (y <= 0 || y + h >= GameConfiguration.getInstance().getWindowHeight()) {
//            velocityY = -velocityY;
//        }
    }
}
