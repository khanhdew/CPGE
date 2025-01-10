package com.khanhdew.desktop.main.processor;

import com.khanhdew.gameengine.config.GameConfiguration;
import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.GameEngine;
import com.khanhdew.gameengine.engine.GameRenderer;
import com.khanhdew.gameengine.entity.BaseEntity;
import com.khanhdew.gameengine.entity.movable.player.Player;
import com.khanhdew.gameengine.entity.movable.projectile.Projectile;
import com.khanhdew.desktop.utils.LoadSprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static com.khanhdew.desktop.utils.ConvertImg.Convert;

public class DesktopRenderer implements GameRenderer {

    GraphicsContext gc;
    Image[][] playerAnimations;
    Image playerImg;
    Player player;
    GameEngine gameEngine;

    public DesktopRenderer(Canvas canvas, GameEngine gameEngine) {
        gc = canvas.getGraphicsContext2D();
        this.gameEngine = gameEngine;
        player = gameEngine.getPlayer();
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
    public void draw() {
        drawEnemies();
        showFPS();
        drawPlayer();
        drawProjectiles();

    }

    private void drawProjectiles() {
        for (Projectile entity : player.getProjectileManager().getProjectiles()) {
            if (entity.isActive()) {
                gc.setFill(Color.BLUE); // Set the color for projectiles
                gc.fillRect(entity.getX(), entity.getY(), entity.getW(), entity.getH());
            }
        }
    }

    private void drawEnemies() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (BaseEntity entity : gameEngine.getEnemyManager().getEnemies()) {
            if (entity.isActive()) {
                gc.setFill(Color.RED);
                gc.fillRect(entity.getX(), entity.getY(), entity.getW(), entity.getH());
            }
        }
    }

    private void drawPlayer() {
//        player = gameEngine.getPlayer();
//        if (playerImg != null) {
//            gc.drawImage(playerImg, player.getX(), player.getY());
//        }
        gc.setFill(Color.BLACK);
        gc.fillRect(player.getX(), player.getY(), player.getW(), player.getH());
    }

    private void showFPS() {
        if (GameConfiguration.getInstance().isSHOW_FPS()) {
            gc.setFill(Color.BLACK);
//            gc.fillText("FPS: " + GameApp.fps + "| UPS:" + GameApp.ups, 50, 50);
            gc.fillText("FPS: " + GameApp.fps, 50, 50);
        }
    }

    public void updateRect(int x, int y) {

    }

}
