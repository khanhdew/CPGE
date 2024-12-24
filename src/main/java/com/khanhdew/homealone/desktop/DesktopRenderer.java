package com.khanhdew.homealone.desktop;

import com.khanhdew.homealone.engine.GameEngine;
import com.khanhdew.homealone.engine.GameRenderer;
import com.khanhdew.homealone.entity.BaseEntity;
import com.khanhdew.homealone.entity.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class DesktopRenderer implements GameRenderer {
    GraphicsContext gc;
    Image[][] playerAnimations;

    public DesktopRenderer(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.color(0,0,0));
    }

    @Override
    public void draw(GameEngine gameEngine) {
        for(BaseEntity entity: gameEngine.getEntities()){
            gc.fillRect(entity.getX(), entity.getY(), entity.getW(), entity.getH());
        }
        Player player = gameEngine.getPlayer();
        gc.fillRect(player.getX(), player.getY(), player.getW(), player.getH());
    }

}
