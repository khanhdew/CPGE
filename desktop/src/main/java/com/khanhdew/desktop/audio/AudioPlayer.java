package com.khanhdew.desktop.audio;

import com.khanhdew.gameengine.utils.AudioManager;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AudioPlayer implements AudioManager {
    private MediaPlayer mediaPlayer;

    public void playSound(String filePath) {
        try {
            Media sound = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading audio file: " + e.getMessage());
        }
    }

    public void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

}
