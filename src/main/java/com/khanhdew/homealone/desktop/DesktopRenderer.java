package com.khanhdew.homealone.desktop;

import com.khanhdew.homealone.engine.GameEngine;
import com.khanhdew.homealone.engine.GameRenderer;
import com.khanhdew.homealone.entity.BaseEntity;
import com.khanhdew.homealone.utils.InputHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DesktopRenderer implements GameRenderer, InputHandler {
    GraphicsContext gc;

    public DesktopRenderer(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
    }

    @Override
    public void draw(GameEngine gameEngine) {
        for(BaseEntity entity: gameEngine.getEntities()){
            gc.fillRect(entity.getX(), entity.getY(), entity.getW(), entity.getH());
        }
    }

    @Override
    public void handleInput() {

    }
}
