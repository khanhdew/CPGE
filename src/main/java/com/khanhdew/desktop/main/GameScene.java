package com.khanhdew.desktop.main;


import com.khanhdew.desktop.utils.LoadSprite;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


import static com.khanhdew.desktop.utils.ConvertImg.Convert;

public class GameScene extends Scene {

    public GameScene(Parent parent) {
        super(parent);
        Image fxImg = Convert(LoadSprite.GetSpriteAtlas("cursor.png"));
        setCursor(new ImageCursor(fxImg));
    }
}
