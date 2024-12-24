package com.khanhdew.homealone.desktop;

import com.khanhdew.homealone.engine.GameEngine;
import com.khanhdew.homealone.engine.GameRenderer;
import com.khanhdew.homealone.entity.BaseEntity;
import com.khanhdew.homealone.entity.Player;
import com.khanhdew.homealone.utils.LoadSprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static com.khanhdew.homealone.desktop.utils.ConvertImg.Convert;
import static com.khanhdew.homealone.utils.LoadSprite.GetSpriteAtlas;

public class DesktopRenderer implements GameRenderer {
    GraphicsContext gc;
    Image[][] playerAnimations;
    Rectangle2D playerRect;
    Image playerImg;

    public DesktopRenderer(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.color(0,0,0));
        loadPlayerImage();
    }

    private void loadPlayerImage() {
        try {
            playerImg = Convert(GetSpriteAtlas(LoadSprite.PlayerSprite).getSubimage(0, 0, 192, 192));
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
            gc.fillRect(entity.getX(), entity.getY(), entity.getW(), entity.getH());
        }
        Player player = gameEngine.getPlayer();
//        if (playerImg != null) {
//            gc.drawImage(playerImg, player.getX(), player.getY());
//        }
        gc.fillRect(player.getX(), player.getY(), player.getW(), player.getH());
    }

    public void updateRect(int x, int y){

    }

}
