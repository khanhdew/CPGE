package com.khanhdew.android.main.processor;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.khanhdew.android.main.GamePanel;
import com.khanhdew.android.utils.Touch;
import com.khanhdew.android.utils.input.AttackButton;
import com.khanhdew.android.utils.input.Joystick;
import com.khanhdew.gameengine.engine.GameEngine;
import com.khanhdew.gameengine.utils.InputHandler;

public class AndroidInputHandler implements InputHandler, View.OnTouchListener {
    private GamePanel gamePanel;
    private final Touch touch;
    private Joystick joystick;
    private AttackButton attackButton;

    public AndroidInputHandler(GameEngine gameEngine, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.touch = new Touch(gameEngine);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void handleInput() {
        gamePanel.setOnTouchListener(this);

    }

    @Override
    public void handleRelease() {

    }
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.joystick = new Joystick(gamePanel);
        this.attackButton = new AttackButton(gamePanel);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (joystick != null) {
            joystick.touchEvent(event);
        }
        if (attackButton != null) {
            attackButton.touchEvent(event);
        }
        touch.onTouch(v, event);
        return true;
    }
}
