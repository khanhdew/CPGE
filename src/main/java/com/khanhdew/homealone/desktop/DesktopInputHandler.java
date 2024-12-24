package com.khanhdew.homealone.desktop;

import com.khanhdew.homealone.desktop.utils.Keyboard;
import com.khanhdew.homealone.desktop.utils.Mouse;
import com.khanhdew.homealone.engine.GameEngine;
import com.khanhdew.homealone.utils.InputHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

@Getter
public class DesktopInputHandler implements InputHandler {
    private final GameEngine gameEngine;
    private final GamePane gamePane;
    private final Mouse mouseEventEventHandler;
    private final Keyboard keyEventEventHandler;

    public DesktopInputHandler(GameEngine gameEngine, GamePane gamePane) {
        this.gameEngine = gameEngine;
        this.gamePane = gamePane;
        mouseEventEventHandler = new Mouse(gamePane);
        keyEventEventHandler = new Keyboard(gamePane);
    }

    @Override
    public void handleInput() {
        // handle mouse input
        gamePane.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);

        // handle key input
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED, keyEventEventHandler);
        gamePane.addEventHandler(KeyEvent.KEY_RELEASED, keyEventEventHandler);
    }

}
