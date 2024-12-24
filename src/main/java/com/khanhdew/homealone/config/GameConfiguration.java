package com.khanhdew.homealone.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameConfiguration {
    private static GameConfiguration instance;
    private int windowWidth = 1200;
    private int windowHeight = 800;
    private double windowScale = (double) 16 /9;

    public GameConfiguration(){
    }

    public synchronized static GameConfiguration getInstance(){
        if(instance == null){
            return new GameConfiguration();
        }
        return instance;
    }
}
