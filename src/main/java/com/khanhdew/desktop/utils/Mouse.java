package com.khanhdew.desktop.utils;

import com.khanhdew.desktop.main.GamePane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mouse {
    private GamePane gamePane;
    private ExecutorService executorService;

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
