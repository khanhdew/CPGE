package com.khanhdew.gameengine.config;

import lombok.Setter;

@Setter
public class GameConfiguration {
    private static GameConfiguration instance;
    public static int windowWidth = 1200;
    public static int windowHeight = 800;
    public static double windowScale = (double) 16 / 9;
    public static int FPS = 120;
    public static int UPS = 200;
    public static double timePerFrame;
    public static double timePerUpdate;
    public static boolean SHOW_FPS = true;

    public GameConfiguration() {
        timePerFrame = 1000000000.0 / FPS;
        timePerUpdate = 1000000000.0 / UPS;
    }

    public synchronized static GameConfiguration getInstance() {
        if (instance == null) {
            return new GameConfiguration();
        }
        return instance;
    }
}
