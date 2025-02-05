package com.khanhdew.gameengine.entity.movable.player;

import com.khanhdew.gameengine.entity.movable.Character;

public class Player extends Character {
    public Player(double x, double y, double w, double h) {
        super(x, y, w, h);
        speed = 10;
    }


}
