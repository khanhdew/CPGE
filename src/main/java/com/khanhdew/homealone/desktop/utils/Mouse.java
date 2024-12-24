package com.khanhdew.homealone.desktop.utils;

import com.khanhdew.homealone.desktop.GamePane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Mouse implements EventHandler<MouseEvent> {
    private GamePane gamePane;

    public Mouse(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    @Override
    public void handle(MouseEvent event) {
//        System.out.println(event.getX() + " " + event.getY());
    }
}
