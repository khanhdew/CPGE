package com.khanhdew.gameengine.utils;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.entity.movable.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Camera {
    private double x, y;
    private final Player player;
    private final GameConfiguration configuration = GameConfiguration.getInstance();

    public Camera(Player player) {
        this.player = player;
        this.x = player.getX();
        this.y = player.getY();
    }

    public void update() {
        this.x = player.getX() - (double) GameConfiguration.getInstance().getWindowWidth() / 2 + player.getW() / 2;
        this.y = player.getY() - (double) GameConfiguration.getInstance().getWindowHeight() / 2 + player.getH() / 2;
    }

    public boolean isInView(float x, float y, float w, float h) {
        return x + w > this.x && x < this.x + configuration.getWindowWidth() &&
                y + h > this.y && y < this.y + configuration.getWindowHeight();
    }
}
