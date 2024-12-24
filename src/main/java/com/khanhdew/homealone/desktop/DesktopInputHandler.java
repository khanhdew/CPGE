package com.khanhdew.homealone.desktop;

import com.khanhdew.homealone.engine.GameEngine;
import com.khanhdew.homealone.utils.InputHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class DesktopInputHandler implements InputHandler {
    private GameEngine gameEngine;
    public DesktopInputHandler(GameEngine gameEngine, GamePane gamePane){
        this.gameEngine = gameEngine;
        gamePane.addEventHandler(MouseEvent.MOUSE_CLICKED,this::handleMouseClick);
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED,this::handleKeyPress);
    }
    @Override
    public void handleInput() {

    }

    public void handleMouseClick(MouseEvent event){
        System.out.println(event.getX() + " " + event.getY());
    }

    public void handleKeyPress(KeyEvent event){
        System.out.println(event.getText());
    }

}
