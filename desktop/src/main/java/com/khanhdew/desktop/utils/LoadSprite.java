package com.khanhdew.desktop.utils;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class LoadSprite {
    public static String PlayerSprite = "player.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
//        var url = LoadSprite.class.getClassLoader().getResource("com/khanhdew/homealone/assets/img/"+fileName);
//        System.out.println(url);
        InputStream is = LoadSprite.class.getClassLoader().getResourceAsStream(fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

}
