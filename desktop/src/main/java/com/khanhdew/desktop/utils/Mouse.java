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

    @SuppressWarnings("not fixed yet")
    public void handlerMouseClick(MouseEvent e) {
        player.shoot((e.getX() - player.getX()), (e.getY() - player.getY()));
    }

    public void handlerMousePress(MouseEvent e) {
        gamePane.getGameEngine().getPlayer().shoot(e.getX(), e.getY());
    }

}
