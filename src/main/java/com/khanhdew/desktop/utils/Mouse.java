package com.khanhdew.desktop.utils;

import com.khanhdew.desktop.main.GamePane;
import com.khanhdew.gameengine.entity.movable.player.Player;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Mouse implements EventHandler<MouseEvent> {
    private GamePane gamePane;

    public Mouse(GamePane gamePane) {
        this.gamePane = gamePane;

    }

    @Override
    public void handle(MouseEvent event) {
    }

    public void handlerMouseClick(MouseEvent e){
        gamePane.getGameEngine().getPlayer().shoot(e.getX(),e.getY());
    }
}
