package com.khanhdew.homealone.desktop;


import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


import static com.khanhdew.homealone.utils.LoadSprite.GetSpriteAtlas;
import static com.khanhdew.homealone.desktop.utils.ConvertImg.Convert;

public class GameScene extends Scene {

    public GameScene(Parent parent) {
        super(parent);
        Image fxImg = Convert(GetSpriteAtlas("cursor.png"));
        setCursor(new ImageCursor(fxImg));
    }
}
