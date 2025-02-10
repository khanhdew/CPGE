package com.khanhdew.desktop.utils;

import com.khanhdew.desktop.main.GamePane;
import com.khanhdew.gameengine.entity.movable.player.Player;
import javafx.scene.input.MouseEvent;

public class Mouse {
    private GamePane gamePane;
    private Player player;

    public Mouse(GamePane gamePane) {
        this.gamePane = gamePane;
        this.player = gamePane.getGameEngine().getPlayer();
    }

    public void handlerMouseClick(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        double angle = Math.atan2(y, x);
        player.shoot(angle);
    }

    public void handlerMousePress(MouseEvent e) {
        gamePane.getGameEngine().getPlayer().shoot(e.getX(), e.getY());
    }

}
