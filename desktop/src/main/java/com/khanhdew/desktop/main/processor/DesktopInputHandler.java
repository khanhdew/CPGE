package com.khanhdew.desktop.main.processor;

import com.khanhdew.desktop.main.GamePane;
import com.khanhdew.desktop.utils.Keyboard;
import com.khanhdew.desktop.utils.Mouse;
import com.khanhdew.gameengine.engine.GameEngine;
import com.khanhdew.gameengine.utils.InputHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

@Getter
public class DesktopInputHandler implements InputHandler {
    private final GameEngine gameEngine;
    private final GamePane gamePane;
    private final Mouse mouseHandler;
    private final Keyboard keyHandler;


    public DesktopInputHandler(GameEngine gameEngine, GamePane gamePane) {
        this.gameEngine = gameEngine;
        this.gamePane = gamePane;
        mouseHandler = new Mouse(gamePane);
        keyHandler = new Keyboard(gamePane);
    }

    @Override
    public void handleInput() {
        // handle mouse input
        gamePane.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler::handlerMouseClick);
//        gamePane.addEventHandler(MouseEvent.MOUSE_MOVED, mouseEventEventHandler);
        // handle key input
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler::handleKeyPress);
        gamePane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler::handleKeyRelease);
    }

    @Override
    public void handleRelease() {
        if (!keyHandler.isKeyReleased()) {
            keyHandler.updatePlayerMovement();
        }

    }


}
