package com.khanhdew.homealone.desktop.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;


public class ConvertImg {
    public static Image Convert(BufferedImage bufferedImage){
        return SwingFXUtils.toFXImage(bufferedImage.getSubimage(0,0,16,16),null);
    }
}
