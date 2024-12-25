package com.khanhdew.desktop;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;
import com.khanhdew.gameengine.engine.GameRenderer;
import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.Player;
import com.khanhdew.gameengine.utils.LoadSprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static com.khanhdew.desktop.utils.ConvertImg.Convert;

public class DesktopRenderer implements GameRenderer {
    
    GraphicsContext gc;
    Image[][] playerAnimations;
    Rectangle2D playerRect;
    Image playerImg;

    public DesktopRenderer(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
        loadPlayerImage();
    }

    private void loadPlayerImage() {
        try {
            playerImg = Convert(LoadSprite.GetSpriteAtlas(LoadSprite.PlayerSprite).getSubimage(0, 0, 192, 192));
            if (playerImg == null) {
                System.out.println("Failed to load player image.");
            } else {
                System.out.println("Player image loaded successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading player image: " + e.getMessage());
        }
    }

    @Override
    public void draw(GameEngine gameEngine) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for(BaseEntity entity: gameEngine.getEntities()){
            gc.setFill(Color.RED);
            gc.fillRect(entity.getX(), entity.getY(), entity.getW(), entity.getH());
        }
        Player player = gameEngine.getPlayer();
//        if (playerImg != null) {
//            gc.drawImage(playerImg, player.getX(), player.getY());
//        }
        gc.setFill(Color.color(0,0,0));
        gc.fillRect(player.getX(), player.getY(), player.getW(), player.getH());
        if (GameConfiguration.SHOW_FPS) {
            gc.fillText("FPS: " + GameApp.fps + "| UPS:" + GameApp.ups,50,50);
        }
    }

    public void updateRect(int x, int y){

    }

}
