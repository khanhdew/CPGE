package com.khanhdew.gameengine.entity.movable.enemy;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.movable.Character;

public class Enemy extends Character {
    public Enemy(double x, double y, double w, double h) {
        super(x, y, w, h);
        vX = 1;
        vY = 1;
    }

    @Override
    public void update() {
        // Update position
        x += vX;
        y += vY;
        // Check for collision with window boundaries and reverse direction if necessary
        if (x <= 0 || x + w >= configuration.getWindowWidth()) {
            vX = -vX;
        }
        if (y <= 0 || y + h >= configuration.getWindowHeight()) {
            vY = -vY;
        }

        if (health <= 0) {
            active = false;
        }
    }
}
