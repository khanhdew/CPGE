package com.khanhdew.android.utils;

import android.view.MotionEvent;
import android.view.View;

import com.khanhdew.gameengine.engine.GameEngine;

public class Touch implements View.OnTouchListener {
    private final GameEngine gameEngine;
    public Touch(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

//        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            gameEngine.getPlayer().shoot(event.getX(), event.getY());
//        }
//        if(event.getAction() == MotionEvent.ACTION_MOVE){
//            gameEngine.getPlayer().setX(event.getX());
//            gameEngine.getPlayer().setY(event.getY());
//        }
        return true;
    }

    public void updatePlayerMovement(){
//        double dx = 0;
//        double dy = 0;
//        gameEngine.getPlayer().translateXY(0, 0);
    }
}
