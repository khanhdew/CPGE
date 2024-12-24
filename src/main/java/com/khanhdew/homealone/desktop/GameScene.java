package com.khanhdew.homealone.desktop;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

import static com.khanhdew.homealone.utils.LoadSprite.GetSpriteAtlas;
public class GameScene extends Scene {

    public GameScene(Parent parent) {
        super(parent);
        BufferedImage img = GetSpriteAtlas("cursor.png");
        Image fxImg = SwingFXUtils.toFXImage(img.getSubimage(0,0,16,16),null);
        setCursor(new ImageCursor(fxImg));
    }
}
