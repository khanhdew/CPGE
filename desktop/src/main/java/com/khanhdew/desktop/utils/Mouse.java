package com.khanhdew.desktop.utils;

import com.khanhdew.desktop.main.GamePane;
import javafx.scene.input.MouseEvent;

public class Mouse {
    private GamePane gamePane;

    public Mouse(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public void handlerMouseClick(MouseEvent e){
        gamePane.getGameEngine().getPlayer().shoot(e.getX(),e.getY());
    }

    public void handlerMousePress(MouseEvent e){
        gamePane.getGameEngine().getPlayer().shoot(e.getX(),e.getY());
    }

}
