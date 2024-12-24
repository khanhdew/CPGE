package com.khanhdew.homealone.engine;

import com.khanhdew.homealone.utils.InputHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameApp {
    private GameEngine gameEngine;
    private GameRenderer renderer;
    private InputHandler inputHandler;

    public void update(){
        gameEngine.update();
        renderer.draw(gameEngine);
        inputHandler.handleInput();
    }
}
